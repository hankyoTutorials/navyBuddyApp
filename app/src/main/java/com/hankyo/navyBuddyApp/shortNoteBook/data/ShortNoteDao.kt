package com.hankyo.navyBuddyApp.shortNoteBook.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ShortNoteDao {

    @Upsert
    suspend fun upsertShortNote(shortNote: ShortNote)

    @Delete
    suspend fun deleteShortNote(shortNote: ShortNote)

    @Query("SELECT * FROM shortNote ORDER BY dateAdded DESC") //DESC is reversing the database output
    fun getShortNotesOrderByDateAdded(): Flow<List<ShortNote>>

    @Query("SELECT * FROM shortNote ORDER BY title ASC")
    fun getShortNotesOrderByTitle(): Flow<List<ShortNote>>


}