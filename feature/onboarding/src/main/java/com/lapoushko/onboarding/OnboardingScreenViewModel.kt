package com.lapoushko.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lapoushko.domain.manager.FirstLaunchManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * @author Lapoushko
 */
class OnboardingScreenViewModel(): ViewModel() {
    private var _state = MutableOnboardingScreenState()
    val state = _state as OnboardingScreenState

    private class MutableOnboardingScreenState: OnboardingScreenState{
        override val items: List<String> by mutableStateOf(
            listOf(
                "Трафик","RabbitMQ","1С Администрирование","Контент маркетинг",
                "B2B маркетинг","Google Аналитика","Big Data","Веб-аналитика",
                "UX исследователь","Геймдизайн","Веб-дизайн","Cinema 4D","Промпт инженеринг",
                "Python-разработка","Парсинг", "Three.js","Webflow"
            )
        )
    }
}

interface OnboardingScreenState{
    val items: List<String>
}