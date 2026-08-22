package vn.edu.student.state_android.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import vn.edu.student.state_android.model.StateMechanism

@Composable
fun MechanismSelector(
    selected: StateMechanism,
    onSelected: (StateMechanism) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mechanism: ${selected.label}")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            StateMechanism.entries.forEach { mechanism ->
                DropdownMenuItem(
                    text = { Text(mechanism.label) },
                    onClick = {
                        onSelected(mechanism)
                        expanded = false
                    }
                )
            }
        }
    }
}