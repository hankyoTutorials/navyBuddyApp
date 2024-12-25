package com.hankyo.navyBuddyApp.shortNoteBook.presentation

import com.hankyo.navyBuddyApp.shortNoteBook.data.ShortNote

sealed interface ShortNotesEvent {
    data object SortShortNotes: ShortNotesEvent //changed "object" to "data object"

    data class DeleteShortNote(val shortNote: ShortNote): ShortNotesEvent

    data class SaveShortNote(
        val title: String,
        val currentDateTime: String,
    ): ShortNotesEvent
}