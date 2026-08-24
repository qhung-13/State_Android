package vn.edu.student.state_android.data

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import vn.edu.student.state_android.model.LabState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "state_lab_prefs")

/**
 * DataStore is the only mechanism in this lab that is real persistence:
 * it survives recomposition, rotation, navigation, Activity recreation,
 * AND process death, because it is written to disk, not to memory or a Bundle.
 */
class DataStoreManager(private val context: Context) {

    private object Keys {
        val NOTE = stringPreferencesKey("ds_note")
        val COUNTER = intPreferencesKey("ds_counter")
        val CHOICE = stringPreferencesKey("ds_choice")
    }

    val state: Flow<LabState> = context.dataStore.data.map { prefs ->
        LabState(
            note = prefs[Keys.NOTE] ?: "",
            counter = prefs[Keys.COUNTER] ?: 0,
            choice = prefs[Keys.CHOICE] ?: "A"
        )
    }

    suspend fun setNote(value: String) {
        context.dataStore.edit { it[Keys.NOTE] = value }
        Log.d(TAG, "Mechanism = DataStore | note changed to $value")
    }

    suspend fun setCounter(value: Int) {
        context.dataStore.edit { it[Keys.COUNTER] = value }
        Log.d(TAG, "Mechanism = DataStore | counter changed to $value")
    }

    suspend fun setChoice(value: String) {
        context.dataStore.edit { it[Keys.CHOICE] = value }
        Log.d(TAG, "Mechanism = DataStore | choice changed to $value")
    }
}