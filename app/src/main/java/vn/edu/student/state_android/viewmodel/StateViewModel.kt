package vn.edu.student.state_android.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "STATE_LAB"

/**
 * Holds TWO independent state sets so both mechanisms can be compared fairly
 * inside the same screen without contaminating each other (Isolation Rule):
 *
 *  1) vm***     -> plain in-memory ViewModel state (NOT backed by SavedStateHandle).
 *                  Survives configuration change (rotation) because the ViewModelStore
 *                  is retained across Activity recreation, but is lost on process death
 *                  because it only ever lives in RAM.
 *
 *  2) sh***     -> backed by SavedStateHandle, which is written into the same Bundle
 *                  used by onSaveInstanceState(). It survives configuration change AND
 *                  (when the system chooses to restore saved state) process death.
 */
class StateViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    init {
        Log.d(TAG, "StateViewModel created")
    }

    // ---------- 1) Pure ViewModel state (in-memory only) ----------
    var vmNote by mutableStateOf("")
        private set
    var vmCounter by mutableStateOf(0)
        private set
    var vmChoice by mutableStateOf("A")
        private set

    fun setVmNote(value: String) {
        vmNote = value
        Log.d(TAG, "Mechanism = ViewModel | note changed to $value")
    }

    fun incrementVmCounter() {
        vmCounter++
        Log.d(TAG, "Mechanism = ViewModel | counter changed to $vmCounter")
    }

    fun decrementVmCounter() {
        vmCounter = (vmCounter - 1).coerceAtLeast(0)
        Log.d(TAG, "Mechanism = ViewModel | counter changed to $vmCounter")
    }

    fun setVmChoice(value: String) {
        vmChoice = value
        Log.d(TAG, "Mechanism = ViewModel | choice changed to $value")
    }

    // ---------- 2) SavedStateHandle-backed state (can survive process death) ----------
    companion object {
        private const val KEY_SH_NOTE = "sh_note"
        private const val KEY_SH_COUNTER = "sh_counter"
        private const val KEY_SH_CHOICE = "sh_choice"
    }

    var shNote by mutableStateOf(savedStateHandle.get<String>(KEY_SH_NOTE) ?: "")
        private set
    var shCounter by mutableStateOf(savedStateHandle.get<Int>(KEY_SH_COUNTER) ?: 0)
        private set
    var shChoice by mutableStateOf(savedStateHandle.get<String>(KEY_SH_CHOICE) ?: "A")
        private set

    fun setShNote(value: String) {
        shNote = value
        savedStateHandle[KEY_SH_NOTE] = value
        Log.d(TAG, "Mechanism = SavedStateHandle | note changed to $value")
    }

    fun incrementShCounter() {
        shCounter++
        savedStateHandle[KEY_SH_COUNTER] = shCounter
        Log.d(TAG, "Mechanism = SavedStateHandle | counter changed to $shCounter")
    }

    fun decrementShCounter() {
        shCounter = (shCounter - 1).coerceAtLeast(0)
        savedStateHandle[KEY_SH_COUNTER] = shCounter
        Log.d(TAG, "Mechanism = SavedStateHandle | counter changed to $shCounter")
    }

    fun setShChoice(value: String) {
        shChoice = value
        savedStateHandle[KEY_SH_CHOICE] = value
        Log.d(TAG, "Mechanism = SavedStateHandle | choice changed to $value")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "StateViewModel onCleared")
    }
}