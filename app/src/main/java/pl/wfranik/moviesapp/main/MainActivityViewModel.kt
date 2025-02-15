package pl.wfranik.moviesapp.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.wfranik.moviesapp.ui.common.utils.SetupState
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _setupState = MutableStateFlow(SetupState.STARTING)
    val setupState = _setupState.asStateFlow()

    fun onSetupFinished() {
        _setupState.value = SetupState.FINISHED
    }
}
