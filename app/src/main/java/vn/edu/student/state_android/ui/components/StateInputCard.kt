package vn.edu.student.state_android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateInputCard(
    note: String,
    counter: Int,
    choice: String,
    onNoteChange: (String) -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onChoiceChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceContainer
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "STATE INPUT",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            /*
             * =====================================================
             * NOTE
             * =====================================================
             */

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Note",
                    style = MaterialTheme.typography.labelLarge
                )

                OutlinedTextField(
                    value = note,
                    onValueChange = onNoteChange,
                    placeholder = {
                        Text("Enter test note...")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            /*
             * =====================================================
             * COUNTER
             * =====================================================
             */

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Counter",
                    style = MaterialTheme.typography.labelLarge
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    OutlinedButton(
                        onClick = onDecrement
                    ) {
                        Text("-")
                    }

                    Text(
                        text = counter.toString(),
                        style =
                            MaterialTheme.typography.headlineSmall
                    )

                    OutlinedButton(
                        onClick = onIncrement
                    ) {
                        Text("+")
                    }
                }
            }

            /*
             * =====================================================
             * CHOICE
             * =====================================================
             */

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Selected Option",
                    style = MaterialTheme.typography.labelLarge
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceEvenly
                ) {

                    ChoiceItem(
                        text = "A",
                        selected = choice == "A",
                        onClick = {
                            onChoiceChange("A")
                        }
                    )

                    ChoiceItem(
                        text = "B",
                        selected = choice == "B",
                        onClick = {
                            onChoiceChange("B")
                        }
                    )

                    ChoiceItem(
                        text = "C",
                        selected = choice == "C",
                        onClick = {
                            onChoiceChange("C")
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChoiceItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Row(
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Text(
            text = "Option $text",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}