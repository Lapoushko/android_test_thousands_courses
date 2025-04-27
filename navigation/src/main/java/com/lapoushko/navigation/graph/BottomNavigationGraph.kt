package com.lapoushko.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lapoushko.auth.AuthScreen
import com.lapoushko.main.MainScreen
import com.lapoushko.navigation.model.Screen
import com.lapoushko.navigation.model.ScreenBar
import com.lapoushko.onboarding.OnboardingScreen


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
        composable(route = ScreenBar.Main.route){
            MainScreen()
        }
    }
}