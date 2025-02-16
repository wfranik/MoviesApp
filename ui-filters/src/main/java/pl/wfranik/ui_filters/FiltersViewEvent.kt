package pl.wfranik.ui_filters

import pl.wfranik.ui_common.utils.TextLabel

sealed interface FiltersViewEvent {
    data object NavigateBack : FiltersViewEvent
    data class ShowError(val textLabel: TextLabel) : FiltersViewEvent
}
