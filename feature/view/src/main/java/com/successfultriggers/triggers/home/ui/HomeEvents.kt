package com.successfultriggers.triggers.home.ui

sealed class TriggerEvent {
    object LoadData : TriggerEvent()
    data class AddItem(val name: String) : TriggerEvent()
    data class DeleteItem(val itemId: Int) : TriggerEvent()
    data class OnItemClicked(val itemId: Int) : TriggerEvent()
    object DeleteAll: TriggerEvent()
    object OnRetry : TriggerEvent()
}
