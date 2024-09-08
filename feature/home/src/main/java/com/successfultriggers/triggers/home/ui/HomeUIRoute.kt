package com.successfultriggers.triggers.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.successfultriggers.triggers.home.ui.components.HomeCompose
import com.successfultriggers.triggers.home.ui.components.ErrorScreen
import com.successfultriggers.triggers.home.ui.components.LoadingScreen

@Composable
fun HomeUIRoute(
    modifier: Modifier = Modifier,
    navTo: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
   val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is CamUIState.Loading -> {
            LoadingScreen()
        }
        is CamUIState.Error -> {
            ErrorScreen(errorMessage = uiState.message) {
                viewModel.onEvent(HomeEvent.OnRetry)
            }
        }
        is CamUIState.Success -> {
            HomeCompose(
                modifier = modifier,
                data = uiState.data,
                onEvent = { event -> viewModel.onEvent(event) }
            )
        }
    }
}
