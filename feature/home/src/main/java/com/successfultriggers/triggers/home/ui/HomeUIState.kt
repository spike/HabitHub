package com.successfultriggers.triggers.home.ui

import com.successfultriggers.triggers.data.mapper.BasePro

sealed interface HomeUIState {
    object Loading : HomeUIState
    data class Error(val message: String) : HomeUIState
    data class Success(
        val data: List<BasePro> = emptyList(),
    ) : HomeUIState
}

