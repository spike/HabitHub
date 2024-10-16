package com.successfultriggers.triggers.data

import com.successfultriggers.triggers.data.mapper.BasePro
import kotlinx.coroutines.flow.Flow

interface BaseProRepo {
    // Not sure where to put these ...
    fun allGetBasePros(): Flow<List<BasePro>> // NOTE: wrap in Flow<Resource<<>>>

    suspend fun insert(basepro: BasePro)

    //suspend fun addTodoPhoto(BasePro: BasePro)
    suspend fun delete(basepro: Int)
    // suspend fun delete(basepro: BasePro)
    suspend fun getBaseProById(baseproId: Int): BasePro? // NOTE: wrap in Flow<Resource<<>>>
    suspend fun deleteAll()
    //abstract fun insert(BasePro: BasePro)


}

class InvalidTodoPhotoException(message: String) : Exception(message)