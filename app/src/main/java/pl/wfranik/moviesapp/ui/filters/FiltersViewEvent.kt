package pl.wfranik.moviesapp.ui.filters

import pl.wfranik.ui_common.TextLabel

sealed interface FiltersViewEvent {
    data object NavigateBack : FiltersViewEvent
    data class ShowError(val textLabel: TextLabel) : FiltersViewEvent
}
