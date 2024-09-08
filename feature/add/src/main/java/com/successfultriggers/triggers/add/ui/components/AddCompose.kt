package com.successfultriggers.triggers.add.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.successfultriggers.triggers.add.ui.TriggerEvent
import com.successfultriggers.triggers.core.ui.Screen



@Composable
fun AddCompose(
    modifier: Modifier = Modifier,
    settings: Map<String, String>,
    onEvent: (TriggerEvent) -> Unit,
    navController: NavHostController
) {
    var newItemName by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {

//        Button(onClick = { onEvent(TriggerEvent.DeleteAllEntries) }) {
//            Text("Delete All Entries")
//        }
        Row {
            TextField(
                value = newItemName,
                onValueChange = { newItemName = it },
                label = { Text("New Item") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newItemName.isNotBlank()) {
                        onEvent( TriggerEvent.AddItem(newItemName))
                        newItemName = ""
                        navController.navigate(Screen.HomeScreen.route)
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }
    }
}

// These will be move to a common directory.
@Composable
fun LoadingScreen() {
    Text(text = "Loading...", modifier = Modifier.fillMaxSize())
}

@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Error: $errorMessage",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Retry",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .clickable { onRetry() }
                .padding(vertical = 8.dp)
        )
    }
}
