package pl.wfranik.moviesapp.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import pl.wfranik.moviesapp.data.genre.GenresRepository
import pl.wfranik.moviesapp.data.movies.MoviesRepository
import pl.wfranik.moviesapp.domain.model.Genre
import pl.wfranik.moviesapp.domain.model.LoadingState
import pl.wfranik.moviesapp.domain.model.Movie
import timber.log.Timber
import javax.inject.Inject

class MoviesContentManager @Inject constructor(
    private val moviesRepository: MoviesRepository,
    genresRepository: GenresRepository
) {

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val itemsFlow: Flow<LoadingState<List<Movie>>> =
        combine(
            genresRepository.observeSelectedGenre(),
            refreshTrigger.onStart { emit(Unit) }
        ) { query, _ ->
            query
        }.flatMapLatest { query ->
            flow {
                emit(LoadingState.Loading)
                val items = fetchItemsFromApi(query)
                emit(LoadingState.Success(items))
            }.retry(RETRY_ATTEMPTS) { e ->
                Timber.d("Retry due to: ${e.message}")
                e is Exception // Retry only if it's an exception
            }.catch { throwable ->
                emit(LoadingState.Error(throwable))
            }
        }

    private suspend fun fetchItemsFromApi(selectedGenre: Genre): List<Movie> {
        return if (selectedGenre.id == Genre.DEFAULT.id) {
            moviesRepository.getMovies().getOrThrow()
        } else {
            moviesRepository.getFilteredMovies(selectedGenre).getOrThrow()
        }
    }

    suspend fun refresh() {
        refreshTrigger.emit(Unit)
    }

    companion object {
        private const val RETRY_ATTEMPTS = 3L
    }
}
