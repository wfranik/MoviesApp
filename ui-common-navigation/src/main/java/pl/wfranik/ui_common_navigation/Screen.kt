package pl.wfranik.ui_common_navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data object Filters : Screen
}
