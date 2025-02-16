package pl.wfranik.ui_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.wfranik.domain_models.LoadingState
import pl.wfranik.domain_usecase.MoviesContentManager
import pl.wfranik.ui_common.extensions.EventsChannel
import pl.wfranik.ui_common.extensions.mutate
import pl.wfranik.ui_home.HomeViewAction.OnChangeFiltersClicked
import pl.wfranik.ui_home.HomeViewAction.OnMovieClicked
import pl.wfranik.ui_home.HomeViewAction.OnRetryClicked
import pl.wfranik.ui_home.model.MovieListItem
import pl.wfranik.ui_home.HomeViewEvent.OpenFiltersScreen
import pl.wfranik.ui_home.HomeViewEvent.OpenMovieDetails
import pl.wfranik.ui_home.model.MovieListItemMapper
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesContentManager: MoviesContentManager,
    private val movieListItemMapper: MovieListItemMapper
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
            OnChangeFiltersClicked -> _event.send(OpenFiltersScreen)
            is OnMovieClicked -> _event.send(OpenMovieDetails(viewEvent.movieListItem))
            OnRetryClicked -> moviesContentManager.refresh()
        }
    }

    private fun loadMovies() = viewModelScope.launch {
        moviesContentManager.itemsFlow.collect { state ->
            when (state) {
                is LoadingState.Loading -> loadingState()
                is LoadingState.Success -> successState(state.data.map { movieListItemMapper(it) })
                is LoadingState.Error -> errorState(state.throwable)
            }
        }
    }

    private fun successState(movies: List<MovieListItem>) {
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
