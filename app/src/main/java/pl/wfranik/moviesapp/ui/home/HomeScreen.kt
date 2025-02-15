package pl.wfranik.moviesapp.ui.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import pl.wfranik.moviesapp.extensions.observeAsEvents
import pl.wfranik.moviesapp.ui.home.HomeViewEvent.OpenFiltersScreen
import pl.wfranik.moviesapp.ui.home.HomeViewEvent.OpenMovieDetails
import pl.wfranik.moviesapp.ui.home.HomeViewEvent.ShowError

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.event.observeAsEvents { event ->
        when (event) {
            is OpenFiltersScreen -> {}
            is OpenMovieDetails -> {}
            is ShowError -> coroutineScope.launch { snackbarHostState.showSnackbar(event.textLabel.invoke(context)) }
        }
    }

    HomeScreenContent(
        state = state,
        onViewAction = viewModel::onViewAction,
        snackbarHostState = snackbarHostState
    )
}
