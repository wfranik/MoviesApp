package pl.wfranik.ui_home

import pl.wfranik.ui_home.model.MovieListItem

sealed interface HomeViewAction {
    data object OnChangeFiltersClicked : HomeViewAction
    data class OnMovieClicked(val movieListItem: MovieListItem) : HomeViewAction
    data object OnRetryClicked : HomeViewAction
}
