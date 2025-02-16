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
import pl.wfranik.moviesapp.domain.model.LoadingState
import pl.wfranik.moviesapp.ui.home.model.MovieListItem
import pl.wfranik.moviesapp.ui.home.model.MovieListItemMapper
import timber.log.Timber
import javax.inject.Inject

class MoviesContentManager @Inject constructor(
    private val loadMoviesWithDetailsUseCase: LoadMoviesWithDetailsUseCase,
    private val movieListItemMapper: MovieListItemMapper,
    genresRepository: GenresRepository
) {

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val itemsFlow: Flow<LoadingState<List<MovieListItem>>> =
        combine(
            genresRepository.observeSelectedGenre(),
            refreshTrigger.onStart { emit(Unit) }
        ) { selectedGenre, _ ->
            selectedGenre
        }.flatMapLatest { selectedGenre ->
            flow {
                emit(LoadingState.Loading)
                val movies: List<MovieListItem> = loadMoviesWithDetailsUseCase(selectedGenre)
                    .map { movieListItemMapper(it) }
                emit(LoadingState.Success(movies))
            }.retry(RETRY_ATTEMPTS) { e ->
                Timber.d("Retry due to: ${e.message}")
                e is Exception // Retry only if it's an exception
            }.catch { throwable ->
                emit(LoadingState.Error(throwable))
            }
        }

    suspend fun refresh() {
        refreshTrigger.emit(Unit)
    }

    companion object {
        private const val RETRY_ATTEMPTS = 3L
    }
}
