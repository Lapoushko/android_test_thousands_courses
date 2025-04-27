package com.lapoushko.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lapoushko.auth.AuthScreen
import com.lapoushko.detail.DetailScreen
import com.lapoushko.favourite.FavouriteScreen
import com.lapoushko.main.MainScreen
import com.lapoushko.navigation.model.Screen
import com.lapoushko.navigation.model.ScreenBar
import com.lapoushko.onboarding.OnboardingScreen
import com.lapoushko.profile.ProfileScreen


/**
 * @author Lapoushko
 */

@Composable
fun BottomNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding
    ) {
        composable<Screen.Onboarding> {
            OnboardingScreen(
                onClick = {
                    navController.navigate(
                        Screen.Authorization
                    )
                }
            )
        }
        composable<Screen.Authorization> {
            AuthScreen(
                onClick = {
                    navController.navigate(
                        ScreenBar.Main.route
                    )
                }
            )
        }
        composable<Screen.Detail> { navBackStackEntry ->
            val id = navBackStackEntry.toRoute<Screen.Detail>().id
            DetailScreen(id = id)
        }
        composable(route = ScreenBar.Main.route) {
            MainScreen(onToDetail = { navController.navigate(Screen.Detail(it)) })
        }
        composable(route = ScreenBar.Favourite.route) {
            FavouriteScreen(onToDetail = { navController.navigate(Screen.Detail(it)) })
        }
        composable(route = ScreenBar.Profile.route) {
            ProfileScreen()
        }
    }
}