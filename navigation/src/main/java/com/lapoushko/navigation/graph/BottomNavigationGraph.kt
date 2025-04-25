package com.lapoushko.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lapoushko.auth.AuthScreen
import com.lapoushko.navigation.model.Screen
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
                    println("YESS")
                }
            )
        }
    }
}