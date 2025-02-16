package pl.wfranik.ui_filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.wfranik.domain_models.Genre
import pl.wfranik.domain_usecase.ClearSelectedGenreUseCase
import pl.wfranik.domain_usecase.GetSelectedGenreUseCase
import pl.wfranik.domain_usecase.LoadGenresUseCase
import pl.wfranik.domain_usecase.SaveGenreUseCase
import pl.wfranik.ui_common.extensions.EventsChannel
import pl.wfranik.ui_common.extensions.mutate
import pl.wfranik.ui_filters.FiltersViewAction.OnBackClicked
import pl.wfranik.ui_filters.FiltersViewAction.OnGenreClicked
import pl.wfranik.ui_filters.FiltersViewAction.OnRetryClicked
import pl.wfranik.ui_filters.FiltersViewEvent.NavigateBack
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
            OnBackClicked -> _event.send(NavigateBack)
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
        _event.send(NavigateBack)
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
