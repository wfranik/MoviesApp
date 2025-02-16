package pl.wfranik.moviesapp.ui.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data object Filters : Screen
}
