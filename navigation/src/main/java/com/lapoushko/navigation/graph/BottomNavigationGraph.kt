package com.lapoushko.navigation.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext


/**
 * @author Lapoushko
 */

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    viewModel: BottomNavigationGraphViewModel = koinViewModel()
) {
    val state = viewModel.state
    val startDestination = if (state.isFirstLaunch) Screen.Onboarding else Screen.Authorization

    if (state.isLoading){
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else{
        KoinContext{
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable<Screen.Onboarding> {
                    OnboardingScreen(
                        onClick = {
                            viewModel.setFirstLaunch(false)
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
    }
}