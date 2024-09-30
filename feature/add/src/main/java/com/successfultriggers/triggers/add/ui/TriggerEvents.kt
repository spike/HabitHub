package com.successfultriggers.triggers.add.ui

sealed class TriggerEvent {
    object LoadData : TriggerEvent()
    data class UpdateSetting(val settingKey: String, val settingValue: String) : TriggerEvent()
    object DeleteAllEntries : TriggerEvent()  // New event to delete all entries
    data class AddItem(val trigger: String) : TriggerEvent()
    data class AddTrigger(val trigger: String,
                          val desiredHabit: String,
                          val minimalAction: String,
                          val color: androidx.compose.ui.graphics.Color) : TriggerEvent()


    data class DeleteItem(val itemId: Int) : TriggerEvent()
    data class OnItemClicked(val itemId: Int) : TriggerEvent()
    object OnRetry : TriggerEvent()
}

