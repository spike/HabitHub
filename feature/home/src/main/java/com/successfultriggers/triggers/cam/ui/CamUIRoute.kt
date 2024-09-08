package com.successfultriggers.triggers.cam.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.successfultriggers.triggers.cam.ui.components.CamCompose
import com.successfultriggers.triggers.cam.ui.components.ErrorScreen
import com.successfultriggers.triggers.cam.ui.components.LoadingScreen

@Composable
fun CamUIRoute(
    modifier: Modifier = Modifier,
    navTo: (String) -> Unit,
    viewModel: CamViewModel = hiltViewModel()
) {
   val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is CamUIState.Loading -> {
            LoadingScreen()
        }
        is CamUIState.Error -> {
            ErrorScreen(errorMessage = uiState.message) {
                viewModel.onEvent(CamEvent.OnRetry)
            }
        }
        is CamUIState.Success -> {
            CamCompose(
                modifier = modifier,
                data = uiState.data,
                onEvent = { event -> viewModel.onEvent(event) }
            )
        }
    }
}
