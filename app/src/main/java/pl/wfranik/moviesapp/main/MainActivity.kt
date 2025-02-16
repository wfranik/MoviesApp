package pl.wfranik.moviesapp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.wfranik.moviesapp.ui.common.navigation.Navigation
import pl.wfranik.moviesapp.ui.common.navigation.Screen.Home
import pl.wfranik.ui_common.theme.MoviesAppTheme
import pl.wfranik.ui_common.utils.OnNavControllerInitializedListener
import pl.wfranik.ui_common.utils.SetupState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        setContent {
            splashScreen.setKeepOnScreenCondition {
                viewModel.setupState.value == SetupState.STARTING
            }

            MainScreen(
                onNavControllerInitialized = viewModel::onSetupFinished
            )
        }
    }
}

@Composable
fun MainScreen(
    onNavControllerInitialized: () -> Unit,
) {

    val navController = rememberNavController()

    DisposableEffect(navController) {
        val listener = OnNavControllerInitializedListener(
            navControllerInitializedCallback = onNavControllerInitialized
        )
        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    MoviesAppTheme {
        Navigation(
            navController = navController,
            startDestination = Home
        )
    }
}

