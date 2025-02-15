package pl.wfranik.moviesapp.ui.home

import pl.wfranik.moviesapp.domain.model.Movie

data class HomeViewState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
