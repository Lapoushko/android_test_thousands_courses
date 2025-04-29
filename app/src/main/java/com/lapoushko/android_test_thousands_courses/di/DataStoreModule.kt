package com.lapoushko.android_test_thousands_courses.di

import com.lapoushko.data.manager.FirstLaunchManagerImpl
import com.lapoushko.domain.manager.FirstLaunchManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Lapoushko
 */
val dataStoreModule = module{
    single<FirstLaunchManager> { FirstLaunchManagerImpl(androidContext()) }
}