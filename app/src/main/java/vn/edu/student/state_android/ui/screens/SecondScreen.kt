package vn.edu.student.state_android.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.edu.student.state_android.data.DataStoreManager
import vn.edu.student.state_android.model.LabState

private const val TAG = "STATE_LAB"

/**
 * SecondScreen reads DataStore directly — it's the one mechanism whose data
 * is guaranteed to be readable here regardless of which mechanism the user
 * had selected on StateLabScreen, since DataStore lives outside Compose/
 * ViewModel memory entirely. This keeps SecondScreen decoupled from
 * StateLabScreen's internals (no shared callback signature to maintain).
 */
@Composable
fun SecondScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    val dataStoreState by dataStoreManager.state.collectAsState(initial = LabState())

    LaunchedEffect(Unit) {
        Log.d(
            TAG,
            "Navigation | SecondScreen shown | DataStore snapshot -> " +
                    "note=${dataStoreState.note} counter=${dataStoreState.counter} choice=${dataStoreState.choice}"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Second Screen", style = MaterialTheme.typography.headlineSmall)
        Text(
            "You left StateLabScreen.\n\nReturn to check whether the state survived.",
            style = MaterialTheme.typography.bodyLarge
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("DATASTORE SNAPSHOT", style = MaterialTheme.typography.titleMedium)
                Text("Note: ${dataStoreState.note}", modifier = Modifier.padding(top = 8.dp))
                Text("Counter: ${dataStoreState.counter}")
                Text("Choice: ${dataStoreState.choice}")
            }
        }

        Button(onClick = onBack) {
            Text("Back to State Lab")
        }
    }
}