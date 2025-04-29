package com.lapoushko.domain.manager

import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
interface FirstLaunchManager{
    fun getFirstLaunch(): Flow<Boolean>

    suspend fun setFirstLaunch(value: Boolean)
}