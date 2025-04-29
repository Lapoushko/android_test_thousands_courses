package com.lapoushko.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.lapoushko.domain.manager.FirstLaunchManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @author Lapoushko
 */
class FirstLaunchManagerImpl(
    private val context: Context
): FirstLaunchManager {
    override fun getFirstLaunch(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[PreferencesKeys.QUERIES] ?: true
        }
    }

    override suspend fun setFirstLaunch(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.QUERIES] = value
        }
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(ConstantsPreferences.PREFERENCES_NAME)
}

private object PreferencesKeys {
    val QUERIES = booleanPreferencesKey(ConstantsPreferences.QUERY)
}

private object ConstantsPreferences{
    const val PREFERENCES_NAME = "first_launch"
    const val QUERY = "last_query"
}