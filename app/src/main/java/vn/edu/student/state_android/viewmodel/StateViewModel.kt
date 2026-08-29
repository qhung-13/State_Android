package vn.edu.student.state_android.viewmodel

import android.os.Process
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "STATE_LAB"

class StateViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_SH_NOTE = "saved_state_handle_note"
        private const val KEY_SH_COUNTER = "saved_state_handle_counter"
        private const val KEY_SH_CHOICE = "saved_state_handle_choice"
    }

    val instanceId: Int = System.identityHashCode(this)

    val processId: Int
        get() = Process.myPid()

    var vmNote by mutableStateOf("")
        private set

    var vmCounter by mutableIntStateOf(0)
        private set

    var vmChoice by mutableStateOf("A")
        private set

    var shNote by mutableStateOf(
        savedStateHandle[KEY_SH_NOTE] ?: ""
    )
        private set

    var shCounter by mutableIntStateOf(
        savedStateHandle[KEY_SH_COUNTER] ?: 0
    )
        private set

    var shChoice by mutableStateOf(
        savedStateHandle[KEY_SH_CHOICE] ?: "A"
    )
        private set

    init {
        Log.d(
            TAG,
            "VIEW_MODEL | CREATED | " +
                    "instanceId=$instanceId | " +
                    "pid=$processId"
        )

        Log.d(
            TAG,
            "SAVED_STATE_HANDLE | RESTORED | " +
                    "note=$shNote | " +
                    "counter=$shCounter | " +
                    "choice=$shChoice"
        )
    }

    fun updateVmNote(value: String) {
        vmNote = value

        Log.d(
            TAG,
            "ViewModel | note changed to \"$value\""
        )
    }

    fun incrementVmCounter() {
        vmCounter++

        Log.d(
            TAG,
            "ViewModel | counter changed to $vmCounter"
        )
    }

    fun decrementVmCounter() {
        vmCounter = (vmCounter - 1).coerceAtLeast(0)

        Log.d(
            TAG,
            "ViewModel | counter changed to $vmCounter"
        )
    }

    fun updateVmChoice(value: String) {
        vmChoice = value

        Log.d(
            TAG,
            "ViewModel | choice changed to $value"
        )
    }

    fun resetVmState() {
        vmNote = ""
        vmCounter = 0
        vmChoice = "A"

        Log.d(
            TAG,
            "ViewModel | state reset"
        )
    }

    fun updateShNote(value: String) {
        shNote = value
        savedStateHandle[KEY_SH_NOTE] = value

        Log.d(
            TAG,
            "SavedStateHandle | note changed to \"$value\""
        )
    }

    fun incrementShCounter() {
        shCounter++

        savedStateHandle[KEY_SH_COUNTER] = shCounter

        Log.d(
            TAG,
            "SavedStateHandle | counter changed to $shCounter"
        )
    }

    fun decrementShCounter() {
        shCounter = (shCounter - 1).coerceAtLeast(0)

        savedStateHandle[KEY_SH_COUNTER] = shCounter

        Log.d(
            TAG,
            "SavedStateHandle | counter changed to $shCounter"
        )
    }

    fun updateShChoice(value: String) {
        shChoice = value
        savedStateHandle[KEY_SH_CHOICE] = value

        Log.d(
            TAG,
            "SavedStateHandle | choice changed to $value"
        )
    }

    fun resetShState() {
        shNote = ""
        shCounter = 0
        shChoice = "A"

        savedStateHandle[KEY_SH_NOTE] = ""
        savedStateHandle[KEY_SH_COUNTER] = 0
        savedStateHandle[KEY_SH_CHOICE] = "A"

        Log.d(
            TAG,
            "SavedStateHandle | state reset"
        )
    }

    override fun onCleared() {
        Log.d(
            TAG,
            "VIEW_MODEL | CLEARED | " +
                    "instanceId=$instanceId | " +
                    "pid=$processId"
        )

        super.onCleared()
    }
}