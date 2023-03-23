package com.jamascrorp.tinkoff.data.repository

import com.jamascrorp.tinkoff.data.database.BookmarksDao
import com.jamascrorp.tinkoff.data.database.OperationsDao
import com.jamascrorp.tinkoff.data.database.PaysDao
import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB
import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB
import com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem
import com.jamascrorp.tinkoff.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val operationsDao: OperationsDao,
    private val paysDao: PaysDao,
    private val bookmarksDao: BookmarksDao,
) :
    LocalDataSourceRepository {

    override suspend fun saveOperationsToDB(operationsModelItem: List<OperationsModelDB>) {
        operationsDao.saveOperations(operationsModelItem)
    }

    override suspend fun delOperationsFromDB() {
        operationsDao.deleteAllOperations()
    }

    override suspend fun getOperationsFromDB(): List<OperationsModelDB> {
        return operationsDao.getAllOperations()
    }

    override suspend fun savePaysToDB(payModelItem: List<PayModelItem>) {
        paysDao.savePay(payModelItem)
    }

    override suspend fun delPaysFromDB() {
        paysDao.deleteAllPays()
    }

    override suspend fun getPaysFromDB(): List<PayModelItem> {
        return paysDao.getAllPays()
    }

    override suspend fun saveBookmarksToDB(bookmarks: BookmarksModelDB) {
        bookmarksDao.saveBookmarks(bookmarks)
    }

    override suspend fun delBookmarksFromDB() {
        bookmarksDao.deleteAllBookmarks()
    }

    override suspend fun getBookmarksFromDB(): List<BookmarksModelDB> {
        return bookmarksDao.getAllBookmarks()
    }
}