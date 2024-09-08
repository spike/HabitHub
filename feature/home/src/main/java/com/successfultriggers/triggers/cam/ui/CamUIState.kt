package com.successfultriggers.triggers.cam.ui

import com.successfultriggers.triggers.data.mapper.BasePro

sealed interface CamUIState {
    object Loading : CamUIState
    data class Error(val message: String) : CamUIState
    data class Success(
        val data: List<BasePro> = emptyList(),
    ) : CamUIState
}

