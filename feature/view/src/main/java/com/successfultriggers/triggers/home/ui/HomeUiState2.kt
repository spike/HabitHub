package com.successfultriggers.triggers.home.ui

import com.successfultriggers.triggers.data.mapper.BasePro

sealed interface HomeUiState2 {
    object Loading : HomeUiState2
    data class Error(val message: String) : HomeUiState2
    data class Success(
        val data: List<BasePro> = emptyList(),
    ) : HomeUiState2
}

