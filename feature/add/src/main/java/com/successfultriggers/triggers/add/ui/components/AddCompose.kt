package com.successfultriggers.triggers.add.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.toArgb
// import com.github.skydoves.colorpicker.compose.*
import androidx.navigation.NavHostController
import com.successfultriggers.triggers.add.ui.TriggerEvent
import com.successfultriggers.triggers.core.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCompose(
    modifier: Modifier = Modifier,
    settings: Map<String, String>,
    onEvent: (TriggerEvent) -> Unit,
    navController: NavHostController
) {
    var newItemName by remember { mutableStateOf("") }
    var desiredHabit by remember { mutableStateOf("") }
    var minimalHabit by remember { mutableStateOf("") }
    var identity by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.Blue) } // default color

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Trigger name input field
        TextField(
            value = newItemName,
            onValueChange = { newItemName = it },
            label = { Text("Trigger Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Desired Habit input field
        TextField(
            value = desiredHabit,
            onValueChange = { desiredHabit = it },
            label = { Text("Desired Habit") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Minimal Habit input field
        TextField(
            value = minimalHabit,
            onValueChange = { minimalHabit = it },
            label = { Text("Minimal Habit") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Identity input field
        TextField(
            value = identity,
            onValueChange = { identity = it },
            label = { Text("Identity") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Color picker button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pick Color", fontSize = 18.sp)
            Button(
                onClick = {
                    // Add color picker functionality
                },
                colors = ButtonDefaults.buttonColors(containerColor = selectedColor)
            ) {
                Text("Select", color = Color.White)
            }
        }

        // Add Trigger button
        Button(
            onClick = {
                if (newItemName.isNotBlank()) {
                    onEvent(TriggerEvent.AddItem(newItemName))
                    newItemName = ""
                    navController.navigate(Screen.HomeScreen.route)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Add Trigger", color = Color.White)
        }
    }
}

// Common components

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error: $errorMessage",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.error),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}
