package pl.wfranik.moviesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.wfranik.moviesapp.domain.MoviesContentManager
import pl.wfranik.moviesapp.domain.model.LoadingState
import pl.wfranik.moviesapp.domain.model.Movie
import pl.wfranik.moviesapp.extensions.EventsChannel
import pl.wfranik.moviesapp.extensions.mutate
import pl.wfranik.moviesapp.ui.home.HomeViewAction.OnChangeFiltersClicked
import pl.wfranik.moviesapp.ui.home.HomeViewAction.OnMovieClicked
import pl.wfranik.moviesapp.ui.home.HomeViewAction.OnRetryClicked
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesContentManager: MoviesContentManager,
) : ViewModel() {

    private val _state = MutableStateFlow(
        HomeViewState()
    )
    val state = _state.asStateFlow()

    private val _event = EventsChannel<HomeViewEvent>()
    val event: Flow<HomeViewEvent> = _event.receiveAsFlow()

    init {
        loadMovies()
    }

    fun onViewAction(viewEvent: HomeViewAction) = viewModelScope.launch {
        when (viewEvent) {
            OnChangeFiltersClicked -> _event.send(HomeViewEvent.OpenFiltersScreen)
            is OnMovieClicked -> _event.send(HomeViewEvent.OpenMovieDetails(viewEvent.movie.id))
            OnRetryClicked -> moviesContentManager.refresh()
        }
    }

    private fun loadMovies() = viewModelScope.launch {
        moviesContentManager.itemsFlow.collect { state ->
            when (state) {
                is LoadingState.Loading -> loadingState()
                is LoadingState.Success -> successState(state.data)
                is LoadingState.Error -> errorState(state.throwable)
            }
        }
    }

    private fun successState(movies: List<Movie>) {
        _state.mutate {
            copy(
                isLoading = false,
                isError = false,
                movies = movies
            )
        }
    }

    private fun loadingState() {
        _state.mutate {
            copy(
                isLoading = true,
                isError = false,
            )
        }
    }

    private fun errorState(throwable: Throwable) {
        _state.mutate {
            copy(
                isLoading = false,
                isError = true,
            )
        }
        Timber.e(throwable, "Could not load movies")
    }
}
