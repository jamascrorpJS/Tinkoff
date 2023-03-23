package com.jamascrorp.tinkoff.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB

@Dao
interface BookmarksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBookmarks(bookmarks: BookmarksModelDB)

    @Query("DELETE FROM bookmarks")
    suspend fun deleteAllBookmarks()

    @Query("SELECT * FROM bookmarks")
    suspend fun getAllBookmarks(): List<BookmarksModelDB>
}