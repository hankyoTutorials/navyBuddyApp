package com.hankyo.navyBuddyApp.shortNoteBook.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShortNote(
    val title: String,
    val currentDateTime: String,
    val dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
