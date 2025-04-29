package com.lapoushko.navigation.model

import com.lapoushko.navigation.R
import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */
@Serializable
sealed class ScreenBar(
    val route: String,
    val title: String,
    val setIcon: Int,
) {
    @Serializable
    data object Main : ScreenBar(
        route = MAIN_ROUTE,
        title = MAIN_TITLE,
        setIcon = MAIN_SET_ICON,
    )

    @Serializable
    data object Favourite : ScreenBar(
        route = FAVOURITE_ROUTE,
        title = FAVOURITE_TITLE,
        setIcon = FAVOURITE_SET_ICON,
    )

    @Serializable
    data object Profile : ScreenBar(
        route = PROFILE_ROUTE,
        title = PROFILE_TITLE,
        setIcon = PROFILE_SET_ICON,
    )

    companion object {
        private const val MAIN_ROUTE = "SEARCH"
        private const val MAIN_TITLE = "Поиск"
        private val MAIN_SET_ICON = R.drawable.main

        private const val FAVOURITE_ROUTE = "favourite"
        private const val FAVOURITE_TITLE = "Избранное"
        private val FAVOURITE_SET_ICON = R.drawable.favourite

        private const val PROFILE_ROUTE = "profile"
        private const val PROFILE_TITLE = "Профиль"
        private val PROFILE_SET_ICON = R.drawable.profile
    }
}