package pl.wfranik.ui_filters

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
import pl.wfranik.ui_filters.FiltersViewEvent.NavigateBack
import pl.wfranik.ui_filters.FiltersViewEvent.ShowError

@Composable
fun FiltersScreen(
    navController: NavController,
    viewModel: FiltersViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.event.observeAsEvents { event ->
        when (event) {
            is NavigateBack -> {
                navController.navigateUp()
            }

            is ShowError -> coroutineScope.launch { snackbarHostState.showSnackbar(event.textLabel.invoke(context)) }
        }
    }

    FiltersScreenContent(
        state = state,
        onViewAction = viewModel::onViewAction,
        snackbarHostState = snackbarHostState
    )
}
