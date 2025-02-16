package pl.wfranik.ui_home

import pl.wfranik.ui_common.utils.TextLabel
import pl.wfranik.ui_home.model.MovieListItem

sealed interface HomeViewEvent {
    data object OpenFiltersScreen : HomeViewEvent
    data class OpenMovieDetails(val movieListItem: MovieListItem) : HomeViewEvent
    data class ShowError(val textLabel: TextLabel) : HomeViewEvent
}
