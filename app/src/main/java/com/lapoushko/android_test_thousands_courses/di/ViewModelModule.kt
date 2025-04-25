package com.lapoushko.android_test_thousands_courses.di

import com.lapoushko.auth.AuthScreenViewModel
import com.lapoushko.main.MainScreenViewModel
import com.lapoushko.onboarding.OnboardingScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Lapoushko
 */
val viewModelModule = module {
    viewModel { OnboardingScreenViewModel() }
    viewModel { AuthScreenViewModel() }
    viewModel { MainScreenViewModel() }
}