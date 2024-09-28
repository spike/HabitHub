package com.successfultriggers.triggers.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.successfultriggers.triggers.data.BaseProEntity
import com.successfultriggers.triggers.home.ui.TriggerEvent2

@Composable
fun HomeCompose2(
    modifier: Modifier = Modifier,
    data: List<BaseProEntity>,
    onEvent: (TriggerEvent2) -> Unit,
    navController: NavHostController,
    triggerId : Int
) {

    var newItemName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Triggers View databaseId:${data[triggerId].todoId}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
           // Text(text = data[triggerId].todoId.toString())
           // Text(text = (triggerId+1).toString())
            Text(text = data[triggerId].title)
            Text(text = data[triggerId].description)
            Button(
                onClick = {
                    // onEvent(TriggerEvent2.DeleteItem(data[triggerId].todoId))
                    navController?.navigate(com.successfultriggers.triggers.core.ui.Screen.HomeScreen.route)
                }
            ) {
                Text("Delete", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}

// These will be moved to a common directory.
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
