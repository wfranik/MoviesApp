package pl.wfranik.moviesapp.ui.filters

import pl.wfranik.moviesapp.domain.model.Genre

data class FiltersViewState(
    val genres: List<Genre> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
