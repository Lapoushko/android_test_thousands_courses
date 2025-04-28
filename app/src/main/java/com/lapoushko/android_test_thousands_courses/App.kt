package com.lapoushko.android_test_thousands_courses

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.lapoushko.android_test_thousands_courses.di.mapperModule
import com.lapoushko.android_test_thousands_courses.di.repositoryModule
import com.lapoushko.android_test_thousands_courses.di.serviceModule
import com.lapoushko.android_test_thousands_courses.di.viewModelModule
import com.lapoushko.navigation.screen.BottomBarScreen
import com.lapoushko.ui.theme.Android_test_thousands_coursesTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author Lapoushko
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                viewModelModule,
                repositoryModule,
                mapperModule,
                serviceModule,
            )
        }
    }

    @Composable
    fun Host() {
        Android_test_thousands_coursesTheme {
            val navController = rememberNavController()
            BottomBarScreen(navController)
        }
    }
}