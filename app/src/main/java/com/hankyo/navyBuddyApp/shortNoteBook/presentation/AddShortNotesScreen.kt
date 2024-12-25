package com.hankyo.navyBuddyApp.shortNoteBook.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
//import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShortNotesScreen(
    state: ShortNoteState,
    navController: NavController,
    onEvent: (ShortNotesEvent) -> Unit
) {

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(
                    ShortNotesEvent.SaveShortNote(
                        title = state.title.value,
                        currentDateTime = state.currentDateTime.value
                    )
                )
                navController.popBackStack()
            }) {

                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Save shortNote"
                )

            }
        }
    ) { paddingValues ->

        Column (
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){

           TextField(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
               value = state.title.value,
               onValueChange = {
                   state.title.value = it
               },
               textStyle = TextStyle(
                   fontWeight = FontWeight.SemiBold,
                   fontSize = 17.sp
               ),
               placeholder = {
                   Text(text = "Title")
               }
           )


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.currentDateTime.value,
                onValueChange = {
                    state.currentDateTime.value = it
                },
                placeholder = {
                    Text(text = "currentDateTime")
                }
            )

        }

    }
}

//
//@Preview
//@Composable
//fun viewAddShortNotesScreen() {
//    AddShortNotesScreen(ShortNoteState().title.value, onEvent = ShortNotesEvent.)
//}