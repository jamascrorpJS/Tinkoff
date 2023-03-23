package com.jamascrorp.tinkoff.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB
import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB
import com.jamascrorp.tinkoff.data.database.models.pay.DataConverter
import com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem
import com.jamascrorp.tinkoff.data.database.models.pay.Subs

@Database(entities = [OperationsModelDB::class, PayModelItem::class, Subs::class, BookmarksModelDB::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class TinkoffDataBase : RoomDatabase() {
    abstract fun operationsDao(): OperationsDao
    abstract fun paysDao(): PaysDao
    abstract fun subsDao(): SubsDao
    abstract fun bookmarksDao(): BookmarksDao
}