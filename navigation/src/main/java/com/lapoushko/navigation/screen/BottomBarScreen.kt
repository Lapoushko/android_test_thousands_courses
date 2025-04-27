package com.lapoushko.navigation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lapoushko.navigation.graph.BottomNavigationGraph
import com.lapoushko.navigation.model.ScreenBar
import com.lapoushko.ui.theme.Green
import com.lapoushko.ui.theme.Typography
import com.lapoushko.ui.theme.White

/**
 * @author Lapoushko
 */

@Composable
fun BottomBarScreen(
    navController: NavHostController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screens = listOf(
        ScreenBar.Main.route,
        ScreenBar.Favourite.route,
        ScreenBar.Profile.route
    )

    val showBottomBar = currentDestination?.route in screens

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .height(1000.dp),
        bottomBar = {
            if (showBottomBar) {
                BottomBar(
                    navController = navController,
                )
            }
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BottomNavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val items = listOf(
        ScreenBar.Main,
        ScreenBar.Favourite,
        ScreenBar.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { screen ->
            AddItem(
                screen = screen,
                destination = destination,
                navController = navController,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomBarScreen(
        navController = rememberNavController(),
    )
}

@Composable
private fun RowScope.AddItem(
    screen: ScreenBar,
    destination: NavDestination?,
    navController: NavHostController,
) {
    val isDestination = destination?.hierarchy?.any { it.route == screen.route } == true
    val color = if (isDestination) Green else White
    NavigationBarItem(
        modifier = Modifier.padding(top = 12.dp, bottom = 16.dp),
        selected = isDestination,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            CustomNavIcon(
                id = screen.setIcon,
                title = screen.title,
                tint = color
            )
        },
        label = {
            Text(
                text = screen.title,
                style = Typography.labelMedium,
                color = color
            )
        }
    )
}

@Composable
private fun CustomNavIcon(
    id: Int,
    title: String,
    tint: Color = Green
) {
    Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource(id),
        contentDescription = title,
        tint = tint
    )
}