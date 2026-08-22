package vn.edu.student.state_android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(onBack: () -> Unit) {
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
        Button(onClick = onBack) {
            Text("Back to State Lab")
        }
    }
}
