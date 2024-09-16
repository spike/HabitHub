package com.successfultriggers.triggers.home.ui

sealed class TriggerEvent2 {
    object LoadData : TriggerEvent2()
    data class AddItem(val name: String) : TriggerEvent2()
    data class DeleteItem(val itemId: Int) : TriggerEvent2()
    data class OnItemClicked(val itemId: Int) : TriggerEvent2()
    object DeleteAll: TriggerEvent2()
    object OnRetry : TriggerEvent2()
}
