package com.successfultriggers.triggers.home.ui

sealed class HomeEvent {
    object LoadData : HomeEvent()
    data class AddItem(val name: String) : HomeEvent()
    data class DeleteItem(val itemId: Int) : HomeEvent()
    data class OnItemClicked(val itemId: Int) : HomeEvent()
    object DeleteAll: HomeEvent()
    object OnRetry : HomeEvent()
}
