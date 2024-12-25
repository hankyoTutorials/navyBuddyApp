package com.hankyo.navyBuddyApp.shortNoteBook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.hankyo.navyBuddyApp.shortNoteBook.data.ShortNotesDatabase
import com.hankyo.navyBuddyApp.shortNoteBook.presentation.ShortNotesScreen
import com.hankyo.navyBuddyApp.shortNoteBook.presentation.ShortNotesViewModel
import com.hankyo.navyBuddyApp.shortNoteBook.ui.theme.NavyBuddyAppTheme

class ShortNoteBookActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            ShortNotesDatabase::class.java,
            "ShortNotes.db"
        ).build()
    }

    private val viewModel by viewModels<ShortNotesViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<T: ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return ShortNotesViewModel(database.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavyBuddyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "ShortNotesScreen") {
                        composable("ShortNotesScreen") {
                            ShortNotesScreen(
                                state = state,
                                onEvent = viewModel::onEvent
                            )

                        }
//                        composable("AddShortNotesScreen") {
//                            AddShortNotesScreen(
//                                state = state,
//                                navController = navController,
//                                onEvent = viewModel::onEvent
//                            )
//                        }
                    }
                }
            }


        }

       // setContentView(R.layout.activity_short_note_book)
    }
}