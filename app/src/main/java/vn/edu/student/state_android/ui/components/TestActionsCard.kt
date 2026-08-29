package vn.edu.student.state_android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TestActionsCard(
    recompositionCount: Int,
    onTriggerRecomposition: () -> Unit,
    onGoToSecondScreen: () -> Unit,
    onResetState: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "TEST ACTIONS",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                onClick = onTriggerRecomposition,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Trigger Recomposition")
            }

            Text(
                text = "Recomposition trigger count: $recompositionCount",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
                onClick = onGoToSecondScreen,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Second Screen")
            }

            OutlinedButton(
                onClick = onResetState,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reset Current State")
            }

            Text(
                text = "Rotation, Activity recreation and Process death are tested from the Android system / emulator.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}