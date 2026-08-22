package vn.edu.student.state_android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
    onGoToSecondScreen: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("TEST ACTIONS", style = MaterialTheme.typography.titleMedium)

            Button(
                onClick = onTriggerRecomposition,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text("Trigger Recomposition")
            }
            Text(
                "Recomposition trigger: $recompositionCount",
                modifier = Modifier.padding(top = 4.dp)
            )

            OutlinedButton(
                onClick = onGoToSecondScreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Go to Second Screen")
            }
        }
    }
}