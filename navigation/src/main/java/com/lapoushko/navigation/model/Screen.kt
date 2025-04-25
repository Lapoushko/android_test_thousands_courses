package com.lapoushko.navigation.model

import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */
@Serializable
sealed class Screen {

    @Serializable
    data object Onboarding : Screen()

    @Serializable
    data object Authorization : Screen()
}