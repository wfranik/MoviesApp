package pl.wfranik.moviesapp.domain.model

sealed class LoadingState<out T> {
    data class Success<T>(val data: T) : LoadingState<T>()
    data class Error(val throwable: Throwable) : LoadingState<Nothing>()
    data object Loading : LoadingState<Nothing>()
}
