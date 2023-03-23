package com.jamascrorp.tinkoff.di.modules

import android.content.Context
import androidx.room.Room
import com.jamascrorp.tinkoff.data.database.BookmarksDao
import com.jamascrorp.tinkoff.data.database.OperationsDao
import com.jamascrorp.tinkoff.data.database.PaysDao
import com.jamascrorp.tinkoff.data.database.TinkoffDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TinkoffDataBase {
        return Room.databaseBuilder(context, TinkoffDataBase::class.java, "database")
            .build()
    }

    @Singleton
    @Provides
    fun provideOperationsDao(database: TinkoffDataBase): OperationsDao {
        return database.operationsDao()
    }

    @Singleton
    @Provides
    fun providePaysDao(database: TinkoffDataBase): PaysDao {
        return database.paysDao()
    }

    @Singleton
    @Provides
    fun provideBookmarksDao(database: TinkoffDataBase): BookmarksDao {
        return database.bookmarksDao()
    }
}