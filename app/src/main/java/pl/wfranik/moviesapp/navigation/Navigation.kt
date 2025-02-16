package pl.wfranik.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.wfranik.ui_common_navigation.Screen
import pl.wfranik.ui_common_navigation.Screen.Filters
import pl.wfranik.ui_common_navigation.Screen.Home
import pl.wfranik.ui_filters.FiltersScreen
import pl.wfranik.ui_home.HomeScreen

@Composable
fun Navigation(navController: NavHostController, startDestination: Screen) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Home> {
            HomeScreen(navController = navController)
        }

        composable<Filters> {
            FiltersScreen(navController = navController)
        }
    }
}
