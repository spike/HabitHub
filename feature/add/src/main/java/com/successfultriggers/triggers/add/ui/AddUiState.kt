package com.successfultriggers.triggers.add.ui

sealed interface AddUiState {
    object Loading : AddUiState
    data class Error(val message: String) : AddUiState
    data class Success(
        val settings: Map<String, String>
    ) : AddUiState

}
