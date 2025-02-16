package pl.wfranik.ui_filters

import pl.wfranik.domain_models.Genre

sealed interface FiltersViewAction {
    data object OnBackClicked : FiltersViewAction
    data class OnGenreClicked(val genre: Genre) : FiltersViewAction
    data object OnRetryClicked : FiltersViewAction
}
