package com.successfultriggers.triggers.data.repository

import androidx.annotation.WorkerThread
import com.successfultriggers.triggers.data.BaseProDao
import com.successfultriggers.triggers.data.BaseProRepo
import com.successfultriggers.triggers.data.mapper.BasePro
import com.successfultriggers.triggers.data.mapper.toBasePro
import com.successfultriggers.triggers.data.mapper.toBaseProEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Custom Provision: Since you provided the BaseProDao and BaseProRepoImpl via the @Provides
 * methods in the DatabaseModule, Hilt doesn't need to rely on @Inject constructors for those classes.
 * It uses the methods in the module to resolve and inject dependencies.
 */
class BaseProRepoImpl @Inject constructor (  // NOTE: constructor injection is not needed
    private val BaseProDao: BaseProDao,
) : BaseProRepo {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    /**
     * this is where the conversion happens
     * Type mismatch.
     * Required: Flow<List<BasePro>>
     *     Found:
     *     Flow<List<BaseProEntity>>
     */
    @WorkerThread
    override fun allGetBasePros(): Flow<List<BasePro>> {
        return BaseProDao.getAllBasePros()
        /*return flow {
            val td = todoDao.getTask().map { it.toPhoto() }
            if(td.isNotEmpty())
                Log.d(TAG, "this is the last in flow in Repo Imp ${td.last().title}")
            emit(td)
        }*/
    }


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    override suspend fun insert(basepro: BasePro) {
        val baseproEnt = basepro.toBaseProEntity()
        BaseProDao.insert(baseproEnt)
    }

    @WorkerThread
    override suspend fun delete(basepro: Int) {
        BaseProDao.deleteById(basepro)
    }

    @WorkerThread
    override suspend fun getBaseProById(baseproId: Int): BasePro? {
        return BaseProDao.findByPhotoTodoId(baseproId)?.toBasePro()
    }

    @WorkerThread
    override suspend fun deleteAll() {
        BaseProDao.deleteAll()
    }

}