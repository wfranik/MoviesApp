package pl.wfranik.moviesapp.ui.filters

import pl.wfranik.domain_models.Genre

data class FiltersViewState(
    val genres: List<Genre> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val selectedGenre: Genre = Genre.DEFAULT
)
