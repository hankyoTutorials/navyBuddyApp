package com.hankyo.navyBuddyApp.shortNoteBook.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hankyo.navyBuddyApp.shortNoteBook.data.ShortNote
import com.hankyo.navyBuddyApp.shortNoteBook.data.ShortNoteDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


class ShortNotesViewModel(
    private val dao: ShortNoteDao
): ViewModel() {

    private val isSortedByDateAdded = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var shortNotes =
        isSortedByDateAdded.flatMapLatest {sort ->
            if (sort) {
                dao.getShortNotesOrderByDateAdded()
            } else {
                dao.getShortNotesOrderByTitle()
            }

        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ShortNoteState())
    val state =
        combine(_state , shortNotes) { state , shortNotes ->
            //combine(_state, isSortedByDateAdded, shortNotes) { state, isSortedByDateAdded, shortNotes ->
            state.copy(
                shortNotes = shortNotes
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ShortNoteState())

    @SuppressLint("SimpleDateFormat")
    fun onEvent(event: ShortNotesEvent) {
        when (event) {
            is ShortNotesEvent.DeleteShortNote -> {
                viewModelScope.launch {
                    dao.deleteShortNote(event.shortNote)
                }
            }
            is ShortNotesEvent.SaveShortNote -> {
                val sdf = SimpleDateFormat("yyyy/M/dd hh:mm:ss")
                val currentDate = sdf.format(Date())
                val shortNote = ShortNote(
                        title = state.value.title.value,
                        //description = state.value.description.value,
                        currentDateTime = currentDate,
                        dateAdded = System.currentTimeMillis()
                )

                viewModelScope.launch {
                    dao.upsertShortNote(shortNote)
                }

                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        currentDateTime = mutableStateOf("")
                    )
                }
            }
            ShortNotesEvent.SortShortNotes -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
        }
    }

}