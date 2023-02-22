package com.mathias8dev.edt.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name=Strings.defaultPrefsName)

class PrefsManager(prefs: DataStore<Preferences>) {
    private val WEEKS_NUMBER = intPreferencesKey("WEEKS_NUMBER")
    private val WEEK_END_DAYS_ENABLE = booleanPreferencesKey("WEEK_END_DAYS_ENABLE")
    private val REMINDER_ENABLE = booleanPreferencesKey("REMINDER_ENABLE")

    var weekName: Flow<String> = prefs.data.map { preferences ->
        val number = preferences[WEEKS_NUMBER] ?: 1
        weekNameFromNumber(number)
    }

    var weekendEnable: Flow<Boolean> = prefs.data.map {preferences ->
        preferences[WEEK_END_DAYS_ENABLE] ?: false
    }

    var reminderEnable: Flow<Boolean> = prefs.data.map { preferences ->
        preferences[REMINDER_ENABLE] ?: false
    }


    suspend fun changeWeeksCountParam(count: Int, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[WEEKS_NUMBER] = count
        }

        this.weekName = flowOf(weekNameFromNumber(count))
    }

    suspend fun changeWeekEndDaysParam(weekendEnable: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[WEEK_END_DAYS_ENABLE] = weekendEnable
        }
        this.weekendEnable = flowOf(weekendEnable)
    }

    suspend fun changeReminderParam(reminderEnable: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[REMINDER_ENABLE] = reminderEnable
        }
        this.reminderEnable = flowOf(reminderEnable)
    }


    companion object Helper {
        fun weekNameFromNumber(count: Int): String = when(count) {
            1 -> "Un"
            2 -> "Deux"
            3 -> "Trois"
            else -> "Quatre"
        }
    }

}