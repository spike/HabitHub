package com.successfultriggers.triggers.add.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.successfultriggers.triggers.add.ui.TriggerEvent
import com.successfultriggers.triggers.core.ui.Screen
import com.successfultriggers.triggers.settings.R

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
    var minimalAction by remember { mutableStateOf("") }
    var identity by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.Blue) } // default color

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

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
            trailingIcon = {
                IconButton(onClick = {

                }) {
                    val icon: Painter = painterResource(id = R.drawable.baseline_square_24)
                    Icon(
                        painter = icon,
                        contentDescription = "Square Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
//            trailingIcon = {
//                IconButton(onClick = { /* Handle microphone button click */ }) {
//                    Icon(
//                      //  imageVector = Icons.Filled.AccountBox,
//                        imageVector = Icons.Filled.AccountBox,
//                        contentDescription = "Microphone Icon",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                }
//            },
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
            value = minimalAction,
            onValueChange = { minimalAction = it },
            label = { Text("Minimal Action") },
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
            Text("Add", color = Color.White, fontSize = 20.sp)
        }
    }
}