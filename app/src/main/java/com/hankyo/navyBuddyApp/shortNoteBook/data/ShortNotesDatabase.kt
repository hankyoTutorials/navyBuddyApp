package com.hankyo.navyBuddyApp.shortNoteBook.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShortNote::class],
    version = 1
)
abstract class ShortNotesDatabase: RoomDatabase(){
    abstract val dao: ShortNoteDao
}