package com.hankyo.navyBuddyApp.shortNoteBook.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.hankyo.navyBuddyApp.shortNoteBook.data.ShortNote

data class ShortNoteState(

    val shortNotes: List<ShortNote> = emptyList(),
    //val shortNotes: List<ShortNote> = shortNotesWithoutReverse.reversed(), //added this line to reverse the list //still testing
    val title: MutableState<String> = mutableStateOf(""),
    val currentDateTime: MutableState<String> = mutableStateOf("")
)