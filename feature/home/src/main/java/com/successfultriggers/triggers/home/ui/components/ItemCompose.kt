package com.successfultriggers.triggers.home.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController

@Composable
fun CompoundButton(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null, // For preview, set as nullable
    primaryText: String,      // First text parameter (big and bold)
    secondaryText: String,    // Second text parameter (smaller and in caps)
    triggerId: Int,
    onClick: () -> Unit,
    cornerRadius: Dp = 12.dp, // Add a parameter for corner radius
    color: Long
) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(color.toULong()), // Pastel color background
                shape = RoundedCornerShape(cornerRadius) // Rounded corners
            )

            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Text section with two text parameters
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    Log.d("CompoundButton", "Colum clicked: $triggerId")
                    navController?.navigate(com.successfultriggers.triggers.core.ui.Screen.ViewScreen.route.plus("/$triggerId"))
                }
        ) {
            Text(
                text = primaryText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Text in white
            )
            Text(
                text = secondaryText.uppercase(),
                fontSize = 16.sp,
                color = Color.White // Text in white
            )
        }

        // Icon button that toggles between rotate and checkmark
        IconButton(
            onClick = {
                isChecked = !isChecked
               // Log.d("CompoundButton", "Icon clicked: $primaryText")
                      },
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = if (isChecked) Icons.Filled.Check else Icons.Filled.Refresh,
                contentDescription = null,
                tint = Color.White // Icon in white
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompoundButtonPreview() {
    // Preview composable without NavController
    CompoundButton(
        primaryText = "Shoes in front of door",
        secondaryText = "1 DAY AGO",
        onClick = { /* Handle preview click */ },
        triggerId = 0,
        color = 0
    )
}
