package vn.edu.student.state_android.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vn.edu.student.state_android.model.LabState

private const val TAG = "STATE_LAB"

private val Context.dataStore by preferencesDataStore(
    name = "state_survival_lab_preferences"
)

class DataStoreManager(
    private val context: Context
) {

    private object Keys {
        val NOTE = stringPreferencesKey("note")
        val COUNTER = intPreferencesKey("counter")
        val CHOICE = stringPreferencesKey("choice")
    }

    val state: Flow<LabState> = context.dataStore.data.map { preferences ->

        LabState(
            note = preferences[Keys.NOTE] ?: "",
            counter = preferences[Keys.COUNTER] ?: 0,
            choice = preferences[Keys.CHOICE] ?: "A"
        )
    }

    suspend fun setNote(value: String) {

        context.dataStore.edit { preferences ->
            preferences[Keys.NOTE] = value
        }

        Log.d(
            TAG,
            "DataStore | note changed to \"$value\""
        )
    }

    suspend fun incrementCounter() {

        var newValue = 0

        context.dataStore.edit { preferences ->

            val currentValue =
                preferences[Keys.COUNTER] ?: 0

            newValue = currentValue + 1

            preferences[Keys.COUNTER] = newValue
        }

        Log.d(
            TAG,
            "DataStore | counter changed to $newValue"
        )
    }

    suspend fun decrementCounter() {

        var newValue = 0

        context.dataStore.edit { preferences ->

            val currentValue =
                preferences[Keys.COUNTER] ?: 0

            newValue =
                (currentValue - 1).coerceAtLeast(0)

            preferences[Keys.COUNTER] = newValue
        }

        Log.d(
            TAG,
            "DataStore | counter changed to $newValue"
        )
    }

    suspend fun setChoice(value: String) {

        context.dataStore.edit { preferences ->
            preferences[Keys.CHOICE] = value
        }

        Log.d(
            TAG,
            "DataStore | choice changed to $value"
        )
    }

    suspend fun reset() {

        context.dataStore.edit { preferences ->

            preferences[Keys.NOTE] = ""
            preferences[Keys.COUNTER] = 0
            preferences[Keys.CHOICE] = "A"
        }

        Log.d(
            TAG,
            "DataStore | state reset"
        )
    }
}