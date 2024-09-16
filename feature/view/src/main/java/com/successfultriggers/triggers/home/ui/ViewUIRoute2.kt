package com.successfultriggers.triggers.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.successfultriggers.triggers.home.ui.components.HomeCompose2
import com.successfultriggers.triggers.home.ui.components.ErrorScreen
import com.successfultriggers.triggers.home.ui.components.LoadingScreen

@Composable
fun ViewUIRoute2(
    modifier: Modifier = Modifier,
    navTo: (String) -> Unit,
    viewModel: TriggerViewModel2 = hiltViewModel(),
    navController: NavHostController,
    triggerId: Int
) {
   val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is HomeUiState2.Loading -> {
            LoadingScreen()
        }
        is HomeUiState2.Error -> {
            ErrorScreen(errorMessage = uiState.message) {
                viewModel.onEvent(TriggerEvent2.OnRetry)
            }
        }
        is HomeUiState2.Success -> {
            HomeCompose2(
                modifier = modifier,
                data = uiState.data,
                onEvent = { event -> viewModel.onEvent(event) },
                navController = navController,
                triggerId = triggerId
            )
        }
    }
}
