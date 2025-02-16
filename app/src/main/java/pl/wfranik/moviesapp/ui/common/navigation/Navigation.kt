package pl.wfranik.moviesapp.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.wfranik.ui_filters.FiltersScreen
import pl.wfranik.moviesapp.ui.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController, startDestination: Screen) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Home> {
            HomeScreen(navController = navController)
        }

        composable<Screen.Filters> {
            FiltersScreen(navController = navController)
        }
    }
}
