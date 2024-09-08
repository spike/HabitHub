package com.successfultriggers.triggers.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.successfultriggers.triggers.home.ui.components.HomeCompose
import com.successfultriggers.triggers.home.ui.components.ErrorScreen
import com.successfultriggers.triggers.home.ui.components.LoadingScreen

@Composable
fun HomeUIRoute(
    modifier: Modifier = Modifier,
    navTo: (String) -> Unit,
    viewModel: TriggerViewModel = hiltViewModel(),
    navController: NavHostController
) {
   val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is HomeUiState.Loading -> {
            LoadingScreen()
        }
        is HomeUiState.Error -> {
            ErrorScreen(errorMessage = uiState.message) {
                viewModel.onEvent(TriggerEvent.OnRetry)
            }
        }
        is HomeUiState.Success -> {
            HomeCompose(
                modifier = modifier,
                data = uiState.data,
                onEvent = { event -> viewModel.onEvent(event) },
                navController = navController
            )
        }
    }
}
