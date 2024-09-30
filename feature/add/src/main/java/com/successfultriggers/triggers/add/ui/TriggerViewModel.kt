package com.successfultriggers.triggers.add.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.successfultriggers.triggers.data.BaseProRepo  // Import your repository
import com.successfultriggers.triggers.data.mapper.BasePro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: BaseProRepo  // Inject the repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddUiState>(AddUiState.Loading)
    val uiState: StateFlow<AddUiState> = _uiState

    init {
        onEvent(TriggerEvent.LoadData)
    }

    fun onEvent(event: TriggerEvent) {
        when (event) {
            is TriggerEvent.LoadData -> {
                loadSettings()
            }
            is TriggerEvent.UpdateSetting -> {
                updateSetting(event.settingKey, event.settingValue)
            }
            is TriggerEvent.DeleteAllEntries -> {
                deleteAllEntries()
            }
            is TriggerEvent.AddItem -> {
                addItem(event.trigger)
            }
            is TriggerEvent.AddTrigger -> {
                addTrigger(event.trigger, event.desiredHabit, event.minimalAction, event.color)
            }
            is TriggerEvent.DeleteItem -> TODO()
            is TriggerEvent.OnItemClicked -> TODO()
            TriggerEvent.OnRetry -> TODO()
        }
    }

    private fun loadSettings() {
        viewModelScope.launch {
            // Simulate loading settings data
            _uiState.value = AddUiState.Success(
                settings = mapOf("Theme" to "Dark", "Notifications" to "Enabled")
            )
        }
    }

    private fun updateSetting(key: String, value: String) {
        viewModelScope.launch {
            // Handle setting updates
            val currentSettings = (_uiState.value as? AddUiState.Success)?.settings ?: emptyMap()
            val updatedSettings = currentSettings.toMutableMap().apply {
                this[key] = value
            }
            _uiState.value = AddUiState.Success(settings = updatedSettings)
        }
    }

    private fun deleteAllEntries() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
    private fun addItem(trigger: String) {
        viewModelScope.launch {
            try {
                repository.insert(BasePro(trigger = trigger))
                onEvent(TriggerEvent.LoadData)  // Refresh the data after adding
            } catch (e: Exception) {
                _uiState.value = AddUiState.Error(message = e.localizedMessage ?: "addItem error")
            }
        }
    }
    private fun addTrigger(trigger: String,
                           desiredHabit: String,
                           minimalAction: String,
                           color: androidx.compose.ui.graphics.Color) {
        viewModelScope.launch {
            try {
                repository.insert(BasePro(trigger = trigger,
                    desiredHabit = desiredHabit,
                    minimalAction = minimalAction,
                    color = color.value.toLong()))
                onEvent(TriggerEvent.LoadData)  // Refresh the data after adding
            } catch (e: Exception) {
                _uiState.value = AddUiState.Error(message = e.localizedMessage ?: "addTrigger error")
            }
        }
    }
}
