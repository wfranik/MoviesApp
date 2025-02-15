package pl.wfranik.moviesapp.ui.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.wfranik.moviesapp.domain.ClearSelectedGenreUseCase
import pl.wfranik.moviesapp.domain.GetSelectedGenreUseCase
import pl.wfranik.moviesapp.domain.LoadGenresUseCase
import pl.wfranik.moviesapp.domain.SaveGenreUseCase
import pl.wfranik.moviesapp.domain.model.Genre
import pl.wfranik.moviesapp.extensions.EventsChannel
import pl.wfranik.moviesapp.extensions.mutate
import pl.wfranik.moviesapp.ui.filters.FiltersViewAction.OnBackClicked
import pl.wfranik.moviesapp.ui.filters.FiltersViewAction.OnGenreClicked
import pl.wfranik.moviesapp.ui.filters.FiltersViewAction.OnRetryClicked
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    getSelectedGenreUseCase: GetSelectedGenreUseCase,
    private val loadGenresUseCase: LoadGenresUseCase,
    private val saveGenreUseCase: SaveGenreUseCase,
    private val clearSelectedGenreUseCase: ClearSelectedGenreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        FiltersViewState(
            selectedGenre = getSelectedGenreUseCase(),
        )
    )
    val state = _state.asStateFlow()

    private val _event = EventsChannel<FiltersViewEvent>()
    val event: Flow<FiltersViewEvent> = _event.receiveAsFlow()

    init {
        loadMovies()
    }

    fun onViewAction(viewEvent: FiltersViewAction) = viewModelScope.launch {
        when (viewEvent) {
            OnBackClicked -> _event.send(FiltersViewEvent.NavigateBack)
            is OnGenreClicked -> onGenreClicked(viewEvent.genre)
            OnRetryClicked -> {
                loadMovies()
            }
        }
    }

    private fun onGenreClicked(genre: Genre) = viewModelScope.launch {
        if (genre.id == Genre.DEFAULT.id) {
            clearSelectedGenreUseCase()
        } else {
            saveGenreUseCase(genre)
        }
        _event.send(FiltersViewEvent.NavigateBack)
    }

    private fun loadMovies() = viewModelScope.launch {
        _state.mutate {
            copy(
                isLoading = true,
                isError = false,
            )
        }
        loadGenresUseCase().onSuccess { genres ->
            _state.mutate {
                copy(
                    isLoading = false,
                    genres = genres
                )
            }
        }.onFailure { error ->
            _state.mutate {
                copy(
                    isLoading = false,
                    isError = true,
                )
            }
            Timber.e(error, "Could not load genres")
        }
    }
}
