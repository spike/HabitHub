package com.successfultriggers.triggers.home.ui.components

import android.text.format.DateUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

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
                text = "Habit Hub",
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
                    val zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(data[index].lastUpdated), ZoneId.systemDefault())

                    CompoundButton(
                        primaryText = item.trigger,
                        secondaryText = formatTimeAgo(zonedDateTime),
                        // secondaryText = "1 DAY AGO",
                        onClick = {
                            // onEvent(TriggerEvent.OnTriggerClicked(item))
                        },
                        navController = navController,
                        // triggerId = item.todoId
                        triggerId = index,
                        color = item.color
                    )
                }
            }
        }
    }
}


fun formatTimeAgo(zonedDateTime: ZonedDateTime): String {
    val now = ZonedDateTime.now(ZoneId.systemDefault()) // Get current time in system default zone
    val timeDiff = now.toInstant().toEpochMilli() - zonedDateTime.toInstant().toEpochMilli()

    return when {
        timeDiff < DateUtils.MINUTE_IN_MILLIS -> "JUST NOW"
        timeDiff < DateUtils.HOUR_IN_MILLIS -> {
            val minutes = (timeDiff / DateUtils.MINUTE_IN_MILLIS).toInt()
            "$minutes ${if (minutes == 1) "MINUTE" else "MINUTES"} AGO"
        }
        timeDiff < DateUtils.DAY_IN_MILLIS -> {
            val hours = (timeDiff / DateUtils.HOUR_IN_MILLIS).toInt()
            "$hours ${if (hours == 1) "HOUR" else "HOURS"} AGO"
        }
        else -> {
            val days = (timeDiff / DateUtils.DAY_IN_MILLIS).toInt()
            "$days ${if (days == 1) "DAY" else "DAYS"} AGO"
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
