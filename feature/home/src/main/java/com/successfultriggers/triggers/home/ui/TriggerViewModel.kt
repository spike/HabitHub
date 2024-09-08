package com.successfultriggers.triggers.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.successfultriggers.triggers.data.BaseProRepo
import com.successfultriggers.triggers.data.mapper.BasePro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriggerViewModel @Inject constructor(
    private val repository: BaseProRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        onEvent(TriggerEvent.LoadData)
    }

    fun onEvent(event: TriggerEvent) {
        when (event) {
            is TriggerEvent.LoadData -> {
                loadData()
            }
            is TriggerEvent.AddItem -> {
                addItem(event.name)
            }
            is TriggerEvent.DeleteItem -> {
                deleteItem(event.itemId)
            }
            is TriggerEvent.DeleteAll -> {
                deleteAll()
            }
            is TriggerEvent.OnRetry -> {
                onEvent(TriggerEvent.LoadData)
            }
            is TriggerEvent.OnItemClicked -> {
                // Handle item click if needed
            }
        }
    }

    private fun deleteAll() {
        viewModelScope.launch {
            try {
                repository.deleteAll()
                //onEvent(CamEvent.LoadData)  // Refresh the data after deleting
            } catch (e: Exception) {
                _uiState.value = HomeUIState.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.value = HomeUIState.Loading
                repository.allGetBasePros().collect { data ->
                    _uiState.value = HomeUIState.Success(data = data)
                }
            } catch (e: Exception) {
                _uiState.value = HomeUIState.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun addItem(name: String) {
        viewModelScope.launch {
            try {
                repository.insert(BasePro(title = name))
                onEvent(TriggerEvent.LoadData)  // Refresh the data after adding
            } catch (e: Exception) {
                _uiState.value = HomeUIState.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun deleteItem(itemId: Int) {
        viewModelScope.launch {
            try {
                repository.getBaseProById(itemId)
                onEvent(TriggerEvent.LoadData)  // Refresh the data after deleting
            } catch (e: Exception) {
                _uiState.value = HomeUIState.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
