package pl.wfranik.moviesapp.ui.home

import pl.wfranik.ui_common.utils.TextLabel
import pl.wfranik.moviesapp.ui.home.model.MovieListItem

sealed interface HomeViewEvent {
    data object OpenFiltersScreen : HomeViewEvent
    data class OpenMovieDetails(val movieListItem: MovieListItem) : HomeViewEvent
    data class ShowError(val textLabel: TextLabel) : HomeViewEvent
}
