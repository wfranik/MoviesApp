package pl.wfranik.moviesapp.ui.common.utils

import android.os.Bundle

import androidx.navigation.NavController
import androidx.navigation.NavDestination

class OnNavControllerInitializedListener(
    navControllerInitializedCallback: () -> Unit,
) : NavController.OnDestinationChangedListener {

    private var onNavControllerInitialized: (() -> Unit)? = null

    init {
        onNavControllerInitialized = navControllerInitializedCallback
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        if (controller.currentDestination != null) {
            onNavControllerInitialized?.invoke()
            onNavControllerInitialized = null
        }
    }
}
