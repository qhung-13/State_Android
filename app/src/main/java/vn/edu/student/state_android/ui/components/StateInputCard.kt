package vn.edu.student.state_android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateInputCard(
    note: String,
    onNoteChange: (String) -> Unit,
    counter: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    choice: String,
    onChoiceChange: (String) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("State Input", style = MaterialTheme.typography.titleMedium)

            Column(modifier = Modifier.padding(top = 12.dp)) {
                Text("Note")
                OutlinedTextField(
                    value = note,
                    onValueChange = onNoteChange,
                    placeholder = { Text("Enter a note...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("Counter")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = onDecrement) {
                        Icon(Icons.Filled.Remove, contentDescription = "Decrease")
                    }
                    Text(text = counter.toString(), style = MaterialTheme.typography.headlineSmall)
                    IconButton(onClick = onIncrement) {
                        Icon(Icons.Filled.Add, contentDescription = "Increase")
                    }
                }
            }

            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("Selected Option")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    listOf("A", "B", "C").forEach { option ->
                        Row(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .selectable(
                                    selected = choice == option,
                                    onClick = { onChoiceChange(option) }
                                )
                        ) {
                            RadioButton(selected = choice == option, onClick = { onChoiceChange(option) })
                            Text("Option $option", modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }
            }
        }
    }
}