package pl.wfranik.moviesapp.ui.home

import pl.wfranik.moviesapp.ui.utils.TextLabel

sealed interface HomeViewEvent {
    data object OpenFiltersScreen : HomeViewEvent
    data class OpenMovieDetails(val movieId: String) : HomeViewEvent
    data class ShowError(val textLabel: TextLabel) : HomeViewEvent
}
