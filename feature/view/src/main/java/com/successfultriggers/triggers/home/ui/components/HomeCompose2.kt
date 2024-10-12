package com.successfultriggers.triggers.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hd.charts.BarChartView
import com.hd.charts.common.model.ChartDataSet
import com.hd.charts.style.BarChartDefaults
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Text(text = data[triggerId].todoId.toString())
            // Text(text = (triggerId+1).toString())
            Text(text = "trigger:${data[triggerId].trigger}")
            Text(text = "desiredHabit:${data[triggerId].desiredHabit}")
            Text(text = "minimalAction:${data[triggerId].minimalAction}")

            CustomBarChart(color = Color(data[triggerId].color.toULong()))

            // Text(text = "debug color:${data[triggerId].color.toString()}")
            // Color(color.toULong())
//            Button(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .background(
//                        color = Color(data[triggerId].color.toULong()), // Pastel color background
//                        shape = RoundedCornerShape(12.dp) // Rounded corners
//                    ),
//                onClick = {
//                    // onEvent(TriggerEvent2.DeleteItem(data[triggerId].todoId))
//                    navController?.navigate(com.successfultriggers.triggers.core.ui.Screen.HomeScreen.route)
//                }
//            ) {
//                Text("Delete", color = MaterialTheme.colorScheme.onError)
//            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(data[triggerId].color.toULong()), // Pastel color background
                        shape = RoundedCornerShape(12.dp) // Rounded corners
                    )
                    .clickable {
                        // onEvent(TriggerEvent2.DeleteItem(data[triggerId].todoId))
                        navController?.navigate(com.successfultriggers.triggers.core.ui.Screen.HomeScreen.route)

                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Text section with two text parameters
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Delete",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // Text in white
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomBarChart(color: Color = Color.Red) {
    val style = BarChartDefaults.style(
        barColor = color,
        space = 12.dp,
    )

    BarChartView(
        dataSet = ChartDataSet(
            items = listOf(1f, 2f, 1f, 3f, 2f, 0f, 0f),
            title = "  M     T      W      T       F      S      S"
        ),
        style = style
    )
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
