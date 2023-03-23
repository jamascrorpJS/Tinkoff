package com.jamascrorp.tinkoff.domain.use_cases

import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB
import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB
import com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem
import com.jamascrorp.tinkoff.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class GetFromDBUseCase @Inject constructor(private val repository: LocalDataSourceRepository) {

    suspend fun getBookmarks(): List<BookmarksModelDB> {
        return repository.getBookmarksFromDB()
    }
}