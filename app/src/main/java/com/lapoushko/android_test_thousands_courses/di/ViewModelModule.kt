package com.lapoushko.android_test_thousands_courses.di

import com.lapoushko.auth.AuthScreenViewModel
import com.lapoushko.favourite.FavouriteScreenViewModel
import com.lapoushko.main.MainScreenViewModel
import com.lapoushko.navigation.graph.BottomNavigationGraphViewModel
import com.lapoushko.onboarding.OnboardingScreenViewModel
import com.lapoushko.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * @author Lapoushko
 */
val viewModelModule = module {
    viewModelOf( ::OnboardingScreenViewModel )
    viewModelOf( ::AuthScreenViewModel )
    viewModelOf( ::MainScreenViewModel )
    viewModelOf( ::FavouriteScreenViewModel )
    viewModelOf( ::ProfileViewModel )
    viewModelOf( ::BottomNavigationGraphViewModel )
}