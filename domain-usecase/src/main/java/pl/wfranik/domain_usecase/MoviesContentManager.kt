package pl.wfranik.domain_usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import pl.wfranik.data_contract.GenresRepository
import pl.wfranik.domain_models.LoadingState
import pl.wfranik.domain_models.MovieWithDetails
import timber.log.Timber
import javax.inject.Inject

class MoviesContentManager @Inject constructor(
    private val loadMoviesWithDetailsUseCase: LoadMoviesWithDetailsUseCase,
    genresRepository: GenresRepository
) {

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val itemsFlow: Flow<LoadingState<List<MovieWithDetails>>> =
        combine(
            genresRepository.observeSelectedGenre(),
            refreshTrigger.onStart { emit(Unit) }
        ) { selectedGenre, _ ->
            selectedGenre
        }.flatMapLatest { selectedGenre ->
            flow {
                emit(LoadingState.Loading)
                val movies: List<MovieWithDetails> = loadMoviesWithDetailsUseCase(selectedGenre).getOrThrow()
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
