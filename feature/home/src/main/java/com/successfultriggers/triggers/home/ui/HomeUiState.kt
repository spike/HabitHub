package com.successfultriggers.triggers.home.ui

import com.successfultriggers.triggers.data.mapper.BasePro

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Error(val message: String) : HomeUiState
    data class Success(
        val data: List<BasePro> = emptyList(),
    ) : HomeUiState
}

