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
class HomeViewModel @Inject constructor(
    private val repository: BaseProRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow<CamUIState>(CamUIState.Loading)
    val uiState: StateFlow<CamUIState> = _uiState

    init {
        onEvent(HomeEvent.LoadData)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadData -> {
                loadData()
            }
            is HomeEvent.AddItem -> {
                addItem(event.name)
            }
            is HomeEvent.DeleteItem -> {
                deleteItem(event.itemId)
            }
            is HomeEvent.DeleteAll -> {
                deleteAll()
            }
            is HomeEvent.OnRetry -> {
                onEvent(HomeEvent.LoadData)
            }
            is HomeEvent.OnItemClicked -> {
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
                _uiState.value = CamUIState.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.value = CamUIState.Loading
                repository.allGetBasePros().collect { data ->
                    _uiState.value = CamUIState.Success(data = data)
                }
            } catch (e: Exception) {
                _uiState.value = CamUIState.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun addItem(name: String) {
        viewModelScope.launch {
            try {
                repository.insert(BasePro(title = name))
                onEvent(HomeEvent.LoadData)  // Refresh the data after adding
            } catch (e: Exception) {
                _uiState.value = CamUIState.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun deleteItem(itemId: Int) {
        viewModelScope.launch {
            try {
                repository.getBaseProById(itemId)
                onEvent(HomeEvent.LoadData)  // Refresh the data after deleting
            } catch (e: Exception) {
                _uiState.value = CamUIState.Error(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
