package vn.edu.student.state_android.ui.screens

import android.os.Process
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import vn.edu.student.state_android.data.DataStoreManager
import vn.edu.student.state_android.model.LabState
import vn.edu.student.state_android.model.StateMechanism
import vn.edu.student.state_android.ui.DestinationLifecycleLogger
import vn.edu.student.state_android.ui.components.CurrentStateCard
import vn.edu.student.state_android.ui.components.MechanismSelector
import vn.edu.student.state_android.ui.components.StateInputCard
import vn.edu.student.state_android.ui.components.TestActionsCard
import vn.edu.student.state_android.viewmodel.StateViewModel

private const val TAG = "STATE_LAB"

@Composable
fun StateLabScreen(
    onGoToSecondScreen: () -> Unit,
    stateViewModel: StateViewModel = viewModel()
) {

    DestinationLifecycleLogger(
        screenName = "StateLabScreen"
    )

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var mechanismName by rememberSaveable {
        mutableStateOf(StateMechanism.REMEMBER.name)
    }

    val mechanism = StateMechanism.valueOf(
        mechanismName
    )

    var rememberNote by remember {
        mutableStateOf("")
    }

    var rememberCounter by remember {
        mutableIntStateOf(0)
    }

    var rememberChoice by remember {
        mutableStateOf("A")
    }

    var saveableNote by rememberSaveable {
        mutableStateOf("")
    }

    var saveableCounter by rememberSaveable {
        mutableIntStateOf(0)
    }

    var saveableChoice by rememberSaveable {
        mutableStateOf("A")
    }

    val dataStoreManager = remember(
        context.applicationContext
    ) {
        DataStoreManager(
            context.applicationContext
        )
    }

    val dataStoreState by dataStoreManager.state.collectAsState(
        initial = LabState()
    )

    var recompositionTriggerCount by remember {
        mutableIntStateOf(0)
    }

    val compositionPassCounter = remember {
        intArrayOf(0)
    }

    SideEffect {

        compositionPassCounter[0]++

        Log.d(
            TAG,
            "COMPOSITION | StateLabScreen completed | " +
                    "pass=${compositionPassCounter[0]} | " +
                    "mechanism=${mechanism.label}"
        )
    }

    val currentNote: String
    val currentCounter: Int
    val currentChoice: String

    when (mechanism) {

        StateMechanism.REMEMBER -> {
            currentNote = rememberNote
            currentCounter = rememberCounter
            currentChoice = rememberChoice
        }

        StateMechanism.REMEMBER_SAVEABLE -> {
            currentNote = saveableNote
            currentCounter = saveableCounter
            currentChoice = saveableChoice
        }

        StateMechanism.VIEW_MODEL -> {
            currentNote = stateViewModel.vmNote
            currentCounter = stateViewModel.vmCounter
            currentChoice = stateViewModel.vmChoice
        }

        StateMechanism.SAVED_STATE_HANDLE -> {
            currentNote = stateViewModel.shNote
            currentCounter = stateViewModel.shCounter
            currentChoice = stateViewModel.shChoice
        }

        StateMechanism.DATA_STORE -> {
            currentNote = dataStoreState.note
            currentCounter = dataStoreState.counter
            currentChoice = dataStoreState.choice
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        )
    ) {

        Text(
            text = "State Survival Lab",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "How long does Android state survive?",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        MechanismSelector(
            selected = mechanism,
            onSelected = { selectedMechanism ->

                mechanismName =
                    selectedMechanism.name

                Log.d(
                    TAG,
                    "MECHANISM | selected=${selectedMechanism.label}"
                )
            }
        )

        StateInputCard(
            note = currentNote,
            counter = currentCounter,
            choice = currentChoice,

            onNoteChange = { newValue ->

                when (mechanism) {

                    StateMechanism.REMEMBER -> {

                        rememberNote = newValue

                        Log.d(
                            TAG,
                            "remember | note=\"$newValue\""
                        )
                    }

                    StateMechanism.REMEMBER_SAVEABLE -> {

                        saveableNote = newValue

                        Log.d(
                            TAG,
                            "rememberSaveable | note=\"$newValue\""
                        )
                    }

                    StateMechanism.VIEW_MODEL -> {
                        stateViewModel.updateVmNote(
                            newValue
                        )
                    }

                    StateMechanism.SAVED_STATE_HANDLE -> {
                        stateViewModel.updateShNote(
                            newValue
                        )
                    }

                    StateMechanism.DATA_STORE -> {

                        scope.launch {
                            dataStoreManager.setNote(
                                newValue
                            )
                        }
                    }
                }
            },

            onIncrement = {

                when (mechanism) {

                    StateMechanism.REMEMBER -> {

                        rememberCounter++

                        Log.d(
                            TAG,
                            "remember | counter=$rememberCounter"
                        )
                    }

                    StateMechanism.REMEMBER_SAVEABLE -> {

                        saveableCounter++

                        Log.d(
                            TAG,
                            "rememberSaveable | counter=$saveableCounter"
                        )
                    }

                    StateMechanism.VIEW_MODEL -> {
                        stateViewModel.incrementVmCounter()
                    }

                    StateMechanism.SAVED_STATE_HANDLE -> {
                        stateViewModel.incrementShCounter()
                    }

                    StateMechanism.DATA_STORE -> {

                        scope.launch {
                            dataStoreManager.incrementCounter()
                        }
                    }
                }
            },

            onDecrement = {

                when (mechanism) {

                    StateMechanism.REMEMBER -> {

                        rememberCounter =
                            (rememberCounter - 1)
                                .coerceAtLeast(0)

                        Log.d(
                            TAG,
                            "remember | counter=$rememberCounter"
                        )
                    }

                    StateMechanism.REMEMBER_SAVEABLE -> {

                        saveableCounter =
                            (saveableCounter - 1)
                                .coerceAtLeast(0)

                        Log.d(
                            TAG,
                            "rememberSaveable | counter=$saveableCounter"
                        )
                    }

                    StateMechanism.VIEW_MODEL -> {
                        stateViewModel.decrementVmCounter()
                    }

                    StateMechanism.SAVED_STATE_HANDLE -> {
                        stateViewModel.decrementShCounter()
                    }

                    StateMechanism.DATA_STORE -> {

                        scope.launch {
                            dataStoreManager.decrementCounter()
                        }
                    }
                }
            },

            onChoiceChange = { newChoice ->

                when (mechanism) {

                    StateMechanism.REMEMBER -> {

                        rememberChoice = newChoice

                        Log.d(
                            TAG,
                            "remember | choice=$newChoice"
                        )
                    }

                    StateMechanism.REMEMBER_SAVEABLE -> {

                        saveableChoice = newChoice

                        Log.d(
                            TAG,
                            "rememberSaveable | choice=$newChoice"
                        )
                    }

                    StateMechanism.VIEW_MODEL -> {
                        stateViewModel.updateVmChoice(
                            newChoice
                        )
                    }

                    StateMechanism.SAVED_STATE_HANDLE -> {
                        stateViewModel.updateShChoice(
                            newChoice
                        )
                    }

                    StateMechanism.DATA_STORE -> {

                        scope.launch {
                            dataStoreManager.setChoice(
                                newChoice
                            )
                        }
                    }
                }
            }
        )

        CurrentStateCard(
            mechanism = mechanism,
            note = currentNote,
            counter = currentCounter,
            choice = currentChoice
        )

        Text(
            text =
                "Debug: PID=${Process.myPid()} | " +
                        "ViewModel ID=${stateViewModel.instanceId}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        TestActionsCard(
            recompositionCount =
                recompositionTriggerCount,

            onTriggerRecomposition = {

                recompositionTriggerCount++

                Log.d(
                    TAG,
                    "TEST | Trigger Recomposition | " +
                            "count=$recompositionTriggerCount | " +
                            "mechanism=${mechanism.label}"
                )
            },

            onGoToSecondScreen = {

                Log.d(
                    TAG,
                    "NAVIGATION | Leaving StateLabScreen | " +
                            "mechanism=${mechanism.label}"
                )

                onGoToSecondScreen()
            },

            onResetState = {

                when (mechanism) {

                    StateMechanism.REMEMBER -> {

                        rememberNote = ""
                        rememberCounter = 0
                        rememberChoice = "A"
                    }

                    StateMechanism.REMEMBER_SAVEABLE -> {

                        saveableNote = ""
                        saveableCounter = 0
                        saveableChoice = "A"
                    }

                    StateMechanism.VIEW_MODEL -> {
                        stateViewModel.resetVmState()
                    }

                    StateMechanism.SAVED_STATE_HANDLE -> {
                        stateViewModel.resetShState()
                    }

                    StateMechanism.DATA_STORE -> {

                        scope.launch {
                            dataStoreManager.reset()
                        }
                    }
                }

                Log.d(
                    TAG,
                    "TEST | Reset state | " +
                            "mechanism=${mechanism.label}"
                )
            }
        )
    }
}