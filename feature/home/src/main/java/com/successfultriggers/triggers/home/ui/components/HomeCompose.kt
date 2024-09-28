package com.successfultriggers.triggers.home.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.successfultriggers.triggers.home.ui.TriggerEvent
import com.successfultriggers.triggers.data.BaseProEntity

@Composable
fun HomeCompose(
    modifier: Modifier = Modifier,
    data: List<BaseProEntity>,
    onEvent: (TriggerEvent) -> Unit,
    navController: NavHostController
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
                text = "Triggers",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Add space between items
        ) {
            items(data.size) { index ->
                val item = data[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onEvent(TriggerEvent.OnItemClicked(item.todoId)) },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    CompoundButton(
                        primaryText = item.title,
                        secondaryText = "1 DAY AGO",
                        onClick = {
                           // Log.d("HomeCompose", "Item clicked: ${index}")
                        },
                        navController = navController,
                       // triggerId = item.todoId
                        triggerId = index
                    )
                }
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
