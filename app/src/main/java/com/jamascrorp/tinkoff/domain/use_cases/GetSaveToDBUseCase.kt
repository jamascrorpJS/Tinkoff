package com.jamascrorp.tinkoff.domain.use_cases

import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB
import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB
import com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem
import com.jamascrorp.tinkoff.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class GetSaveToDBUseCase @Inject constructor(private val repository: LocalDataSourceRepository) {

    suspend fun saveBookmarksToDB(bookmarksModelDB: BookmarksModelDB) {
        repository.saveBookmarksToDB(bookmarksModelDB)
    }
}