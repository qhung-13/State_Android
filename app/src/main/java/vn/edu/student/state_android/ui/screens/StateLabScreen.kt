package vn.edu.student.state_android.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vn.edu.student.state_android.data.DataStoreManager
import vn.edu.student.state_android.model.LabState
import vn.edu.student.state_android.model.StateMechanism
import vn.edu.student.state_android.ui.components.CurrentStateCard
import vn.edu.student.state_android.ui.components.MechanismSelector
import vn.edu.student.state_android.ui.components.StateInputCard
import vn.edu.student.state_android.ui.components.TestActionsCard
import vn.edu.student.state_android.viewmodel.StateViewModel
import kotlinx.coroutines.launch

private const val TAG = "STATE_LAB"

@Composable
fun StateLabScreen(onNavigateToSecondScreen: () -> Unit) {

    // Which mechanism is currently selected for viewing/editing.
    // The selector itself only needs to survive recomposition, so `remember` is enough.
    var mechanism by remember { mutableStateOf(StateMechanism.VIEW_MODEL) }

    // ---------- 1) remember ----------
    var rememberNote by remember { mutableStateOf("") }
    var rememberCounter by remember { mutableStateOf(0) }
    var rememberChoice by remember { mutableStateOf("A") }

    // ---------- 2) rememberSaveable ----------
    var saveableNote by rememberSaveable { mutableStateOf("") }
    var saveableCounter by rememberSaveable { mutableStateOf(0) }
    var saveableChoice by rememberSaveable { mutableStateOf("A") }

    // ---------- 3 & 4) ViewModel + SavedStateHandle ----------
    // viewModel() resolves to the current NavBackStackEntry's ViewModelStoreOwner,
    // so this instance is scoped to the "state_lab" destination in the back stack.
    val stateViewModel: StateViewModel = viewModel()

    // ---------- 5) DataStore ----------
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    val scope = rememberCoroutineScope()
    val dataStoreState by dataStoreManager.state.collectAsState(initial = LabState())

    // Recomposition trigger — deliberately NOT tied to any of the 5 mechanisms above,
    // so it can force a recomposition without disturbing the state under test.
    var recompositionCount by remember { mutableStateOf(0) }

    val (currentNote, currentCounter, currentChoice) = when (mechanism) {
        StateMechanism.REMEMBER -> Triple(rememberNote, rememberCounter, rememberChoice)
        StateMechanism.REMEMBER_SAVEABLE -> Triple(saveableNote, saveableCounter, saveableChoice)
        StateMechanism.VIEW_MODEL -> Triple(stateViewModel.vmNote, stateViewModel.vmCounter, stateViewModel.vmChoice)
        StateMechanism.SAVED_STATE_HANDLE -> Triple(stateViewModel.shNote, stateViewModel.shCounter, stateViewModel.shChoice)
        StateMechanism.DATA_STORE -> Triple(dataStoreState.note, dataStoreState.counter, dataStoreState.choice)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column {
            Text("State Survival Lab", style = MaterialTheme.typography.headlineSmall)
            Text("Test Android State Lifecycle", style = MaterialTheme.typography.bodyMedium)
        }

        Column {
            Text("MECHANISM", style = MaterialTheme.typography.labelMedium)
            MechanismSelector(
                selected = mechanism,
                onSelected = {
                    mechanism = it
                    Log.d(TAG, "Mechanism = ${it.label}")
                }
            )
            Text("Testing: ${mechanism.label}", style = MaterialTheme.typography.bodyMedium)
        }

        StateInputCard(
            note = currentNote,
            onNoteChange = { newValue ->
                when (mechanism) {
                    StateMechanism.REMEMBER -> rememberNote = newValue
                    StateMechanism.REMEMBER_SAVEABLE -> saveableNote = newValue
                    StateMechanism.VIEW_MODEL -> stateViewModel.setVmNote(newValue)
                    StateMechanism.SAVED_STATE_HANDLE -> stateViewModel.setShNote(newValue)
                    StateMechanism.DATA_STORE -> scope.launch { dataStoreManager.setNote(newValue) }
                }
            },
            counter = currentCounter,
            onIncrement = {
                when (mechanism) {
                    StateMechanism.REMEMBER -> {
                        rememberCounter++
                        Log.d(TAG, "Mechanism = remember | counter changed to $rememberCounter")
                    }
                    StateMechanism.REMEMBER_SAVEABLE -> {
                        saveableCounter++
                        Log.d(TAG, "Mechanism = rememberSaveable | counter changed to $saveableCounter")
                    }
                    StateMechanism.VIEW_MODEL -> stateViewModel.incrementVmCounter()
                    StateMechanism.SAVED_STATE_HANDLE -> stateViewModel.incrementShCounter()
                    StateMechanism.DATA_STORE -> scope.launch {
                        dataStoreManager.setCounter(dataStoreState.counter + 1)
                    }
                }
            },
            onDecrement = {
                when (mechanism) {
                    StateMechanism.REMEMBER -> rememberCounter = (rememberCounter - 1).coerceAtLeast(0)
                    StateMechanism.REMEMBER_SAVEABLE -> saveableCounter = (saveableCounter - 1).coerceAtLeast(0)
                    StateMechanism.VIEW_MODEL -> stateViewModel.decrementVmCounter()
                    StateMechanism.SAVED_STATE_HANDLE -> stateViewModel.decrementShCounter()
                    StateMechanism.DATA_STORE -> scope.launch {
                        dataStoreManager.setCounter((dataStoreState.counter - 1).coerceAtLeast(0))
                    }
                }
            },
            choice = currentChoice,
            onChoiceChange = { newValue ->
                when (mechanism) {
                    StateMechanism.REMEMBER -> rememberChoice = newValue
                    StateMechanism.REMEMBER_SAVEABLE -> saveableChoice = newValue
                    StateMechanism.VIEW_MODEL -> stateViewModel.setVmChoice(newValue)
                    StateMechanism.SAVED_STATE_HANDLE -> stateViewModel.setShChoice(newValue)
                    StateMechanism.DATA_STORE -> scope.launch { dataStoreManager.setChoice(newValue) }
                }
            }
        )

        CurrentStateCard(
            mechanism = mechanism,
            note = currentNote,
            counter = currentCounter,
            choice = currentChoice
        )

        TestActionsCard(
            recompositionCount = recompositionCount,
            onTriggerRecomposition = {
                recompositionCount++
                Log.d(TAG, "Recomposition triggered, count = $recompositionCount")
            },
            onGoToSecondScreen = onNavigateToSecondScreen
        )
    }
}