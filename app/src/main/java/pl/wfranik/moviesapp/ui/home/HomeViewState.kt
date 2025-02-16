package pl.wfranik.moviesapp.ui.home

import pl.wfranik.moviesapp.ui.home.model.MovieListItem

data class HomeViewState(
    val movies: List<MovieListItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
