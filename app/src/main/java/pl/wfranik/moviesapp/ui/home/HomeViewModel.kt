package pl.wfranik.moviesapp.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.wfranik.moviesapp.domain.LoadMoviesUseCase
import pl.wfranik.moviesapp.extensions.EventsChannel
import pl.wfranik.moviesapp.extensions.mutate
import pl.wfranik.moviesapp.ui.home.HomeViewAction.OnChangeFiltersClicked
import pl.wfranik.moviesapp.ui.home.HomeViewAction.OnMovieClicked
import pl.wfranik.moviesapp.ui.home.HomeViewAction.OnRetryClicked
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    savedStateHandle: SavedStateHandle,
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
            OnRetryClicked -> {
                loadMovies()
            }
        }
    }

    private fun loadMovies() = viewModelScope.launch {
        _state.mutate {
            copy(
                isLoading = true,
                isError = false,
            )
        }
        loadMoviesUseCase().onSuccess { movies ->
            _state.mutate {
                copy(
                    isLoading = false,
                    movies = movies
                )
            }
        }.onFailure { error ->
            _state.mutate {
                copy(
                    isLoading = false,
                    isError = true,
                )
            }
            Timber.e(error, "Could not load movies")
        }
    }
}
