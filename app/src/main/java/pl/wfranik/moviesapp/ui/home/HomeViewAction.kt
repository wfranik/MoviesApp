package pl.wfranik.moviesapp.ui.home

import pl.wfranik.moviesapp.domain.model.Movie

sealed interface HomeViewAction {
    data object OnChangeFiltersClicked : HomeViewAction
    data class OnMovieClicked(val movie: Movie) : HomeViewAction
    data object OnRetryClicked : HomeViewAction
}
