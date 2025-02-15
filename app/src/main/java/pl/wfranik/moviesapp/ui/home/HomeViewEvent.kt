package pl.wfranik.moviesapp.ui.home

import pl.wfranik.moviesapp.ui.common.utils.TextLabel

sealed interface HomeViewEvent {
    data object OpenFiltersScreen : HomeViewEvent
    data class OpenMovieDetails(val movieId: Int) : HomeViewEvent
    data class ShowError(val textLabel: TextLabel) : HomeViewEvent
}
