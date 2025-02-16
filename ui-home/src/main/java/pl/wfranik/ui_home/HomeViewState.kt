package pl.wfranik.ui_home

import pl.wfranik.ui_home.model.MovieListItem

data class HomeViewState(
    val movies: List<MovieListItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
