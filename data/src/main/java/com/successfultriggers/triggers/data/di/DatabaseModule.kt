package com.successfultriggers.triggers.data.di

import android.content.Context
import androidx.room.Room
import com.successfultriggers.triggers.data.BaseProDB
import com.successfultriggers.triggers.data.BaseProDao
import com.successfultriggers.triggers.data.BaseProRepo
import com.successfultriggers.triggers.data.repository.BaseProRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): BaseProDB {
        return Room.databaseBuilder(
            appContext,
            BaseProDB::class.java,
            BaseProDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBaseProDao(BaseProDB: BaseProDB): BaseProDao {
        return BaseProDB.baseproDao
    }

    @Provides
    @Singleton
    fun provideBaseProRepository(BaseProDao: BaseProDao): BaseProRepo {
        return BaseProRepoImpl(BaseProDao)
    }




}