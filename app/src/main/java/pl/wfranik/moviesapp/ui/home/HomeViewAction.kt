package pl.wfranik.moviesapp.ui.home

import pl.wfranik.moviesapp.ui.home.model.MovieListItem

sealed interface HomeViewAction {
    data object OnChangeFiltersClicked : HomeViewAction
    data class OnMovieClicked(val movieListItem: MovieListItem) : HomeViewAction
    data object OnRetryClicked : HomeViewAction
}
