package com.successfultriggers.triggers.add.ui

sealed class AddEvent {
    object LoadAdd : AddEvent()
    data class UpdateSetting(val settingKey: String, val settingValue: String) : AddEvent()
    object DeleteAllEntries : AddEvent()  // New event to delete all entries
    data class AddItem(val name: String) : AddEvent()
    data class DeleteItem(val itemId: Int) : AddEvent()
    data class OnItemClicked(val itemId: Int) : AddEvent()
    object OnRetry : AddEvent()
}

