package pl.wfranik.moviesapp.ui.filters

import pl.wfranik.moviesapp.ui.common.utils.TextLabel

sealed interface FiltersViewEvent {
    data object OpenFiltersScreen : FiltersViewEvent
    data class OpenMovieDetails(val movieId: Int) : FiltersViewEvent
    data class ShowError(val textLabel: TextLabel) : FiltersViewEvent
}
