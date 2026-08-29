package vn.edu.student.state_android.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vn.edu.student.state_android.ui.DestinationLifecycleLogger

private const val TAG = "STATE_LAB"

@Composable
fun SecondScreen(
    onBack: () -> Unit
) {

    DestinationLifecycleLogger(
        screenName = "SecondScreen"
    )

    LaunchedEffect(Unit) {
        Log.d(
            TAG,
            "NAVIGATION | Entered SecondScreen"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Navigation Test",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Second Screen",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surfaceContainer
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "StateLabScreen is currently not visible.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Return to the previous screen to check whether note, counter and choice survived.",
                    style = MaterialTheme.typography.bodyMedium,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Important: this screen is used only to test Navigation and NavBackStackEntry behavior.",
                    style = MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Button(
            onClick = {

                Log.d(
                    TAG,
                    "NAVIGATION | Back from SecondScreen to StateLabScreen"
                )

                onBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to State Lab")
        }
    }
}