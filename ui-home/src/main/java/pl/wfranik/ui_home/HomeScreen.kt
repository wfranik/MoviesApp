package pl.wfranik.ui_home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pl.wfranik.ui_common.extensions.observeAsEvents
import pl.wfranik.ui_common_navigation.Screen
import pl.wfranik.ui_home.HomeViewEvent.OpenFiltersScreen
import pl.wfranik.ui_home.HomeViewEvent.OpenMovieDetails
import pl.wfranik.ui_home.HomeViewEvent.ShowError

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.event.observeAsEvents { event ->
        when (event) {
            is OpenFiltersScreen -> {
                navController.navigate(Screen.Filters)
            }

            is OpenMovieDetails -> {
                coroutineScope.launch { snackbarHostState.showSnackbar("Movie: ${event.movieListItem.title}") }
            }

            is ShowError -> coroutineScope.launch { snackbarHostState.showSnackbar(event.textLabel.invoke(context)) }
        }
    }

    HomeScreenContent(
        state = state,
        onViewAction = viewModel::onViewAction,
        snackbarHostState = snackbarHostState
    )
}
