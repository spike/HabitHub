package com.successfultriggers.triggers.add.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.successfultriggers.triggers.add.ui.components.ErrorScreen
import com.successfultriggers.triggers.add.ui.components.LoadingScreen
import com.successfultriggers.triggers.add.ui.components.AddCompose

@Composable
fun AddUiRoute(
    modifier: Modifier = Modifier,
    navTo: (String) -> Unit,
    viewModel: AddViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is AddUiState.Loading -> {
            LoadingScreen()
        }
        is AddUiState.Error -> {
            ErrorScreen(errorMessage = uiState.message) {
                viewModel.onEvent(TriggerEvent.LoadTrigger)
            }
        }
        is AddUiState.Success -> {
            AddCompose(
                modifier = modifier,
                settings = uiState.settings,
                onEvent = { event -> viewModel.onEvent(event) }
            )
        }
    }
}
