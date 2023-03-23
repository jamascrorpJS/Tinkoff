package com.jamascrorp.tinkoff.domain.repository

import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB
import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB
import com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem

interface LocalDataSourceRepository {
    suspend fun saveOperationsToDB(operationsModelItem: List<OperationsModelDB>)
    suspend fun delOperationsFromDB()
    suspend fun getOperationsFromDB(): List<OperationsModelDB>

    suspend fun savePaysToDB(payModelDBItem: List<PayModelItem>)
    suspend fun delPaysFromDB()
    suspend fun getPaysFromDB(): List<PayModelItem>

    suspend fun saveBookmarksToDB(bookmarks: BookmarksModelDB)
    suspend fun delBookmarksFromDB()
    suspend fun getBookmarksFromDB(): List<BookmarksModelDB>
}