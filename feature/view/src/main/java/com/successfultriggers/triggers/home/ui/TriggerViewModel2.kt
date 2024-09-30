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
class TriggerViewModel2 @Inject constructor(
    private val repository: BaseProRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState2>(HomeUiState2.Loading)
    val uiState: StateFlow<HomeUiState2> = _uiState

    init {
        onEvent(TriggerEvent2.LoadData)
    }

    fun onEvent(event: TriggerEvent2) {
        when (event) {
            is TriggerEvent2.LoadData -> {
                loadData()
            }
            is TriggerEvent2.AddItem -> {
                addItem(event.name)
            }
            is TriggerEvent2.DeleteItem -> {
                deleteItem(event.itemId)
            }
            is TriggerEvent2.DeleteAll -> {
                deleteAll()
            }
            is TriggerEvent2.OnRetry -> {
                onEvent(TriggerEvent2.LoadData)
            }
            is TriggerEvent2.OnItemClicked -> {
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
                _uiState.value = HomeUiState2.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.value = HomeUiState2.Loading
                repository.allGetBasePros().collect { data ->
                    _uiState.value = HomeUiState2.Success(data = data)
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState2.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun addItem(trigger: String) {
        viewModelScope.launch {
            try {
                repository.insert(BasePro(trigger = trigger))
                onEvent(TriggerEvent2.LoadData)  // Refresh the data after adding
            } catch (e: Exception) {
                _uiState.value = HomeUiState2.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun deleteItem(itemId: Int) {
        viewModelScope.launch {
            try {
                repository.delete(itemId)
               // repository.getBaseProById(itemId)
                onEvent(TriggerEvent2.LoadData)  // Refresh the data after deleting
            } catch (e: Exception) {
                _uiState.value = HomeUiState2.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
