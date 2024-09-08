package com.successfultriggers.triggers.add.ui

sealed class AddEvent {
    object LoadAdd : AddEvent()
    data class UpdateSetting(val settingKey: String, val settingValue: String) : AddEvent()
    object DeleteAllEntries : AddEvent()  // New event to delete all entries
}

