package com.lapoushko.navigation.graph

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lapoushko.domain.manager.FirstLaunchManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * @author Lapoushko
 */
class BottomNavigationGraphViewModel(
    private val manager: FirstLaunchManager
): ViewModel() {
    private var _state = MutableBottomNavigationGraphState()
    val state = _state as BottomNavigationGraphState

    init {
        checkIsFirstLaunch()
    }

    private fun checkIsFirstLaunch(){
        manager.getFirstLaunch().onEach {
            _state.isFirstLaunch = it
            _state.isLoading = false
        }.launchIn(viewModelScope)
    }

    fun setFirstLaunch(value: Boolean){
        viewModelScope.launch {
            manager.setFirstLaunch(value)
        }
    }

    private class MutableBottomNavigationGraphState: BottomNavigationGraphState{
        override var isFirstLaunch: Boolean by mutableStateOf(true)
        override var isLoading: Boolean by mutableStateOf(true)
    }
}

interface BottomNavigationGraphState{
    val isFirstLaunch: Boolean
    val isLoading: Boolean
}