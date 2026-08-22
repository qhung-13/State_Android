package vn.edu.student.state_android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vn.edu.student.state_android.model.StateMechanism
import vn.edu.student.state_android.ui.theme.SurfaceVariant

@Composable
fun CurrentStateCard(
    mechanism: StateMechanism,
    note: String,
    counter: Int,
    choice: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("CURRENT STATE", style = MaterialTheme.typography.titleMedium)
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text("Mechanism: ${mechanism.label}")
                Text("Note: $note")
                Text("Counter: $counter")
                Text("Choice: Option $choice")
            }
        }
    }
}