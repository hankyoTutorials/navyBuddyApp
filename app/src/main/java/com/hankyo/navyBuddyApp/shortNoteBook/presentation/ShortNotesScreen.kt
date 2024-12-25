package com.hankyo.navyBuddyApp.shortNoteBook.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.NoteAlt
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ShortNotesScreen(
    state: ShortNoteState,
    onEvent: (ShortNotesEvent) -> Unit
) {




    Scaffold(
        floatingActionButton = {
                TextField(
                    modifier = Modifier
                        .padding(start = 40.dp, end = 50.dp, top = 70.dp)
                        .fillMaxWidth(),

                    value = state.title.value,
                    onValueChange = {
                        state.title.value = it
                    },
                    textStyle = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    ),
                    placeholder = {
                        Text(text = "ඉස්සරහට කරන්න තියෙන හෝ \n" +
                                "අමතක වෙන දේවල් type කරන්න")
                    }
                )

//            TextField(
//               modifier = Modifier
//                   .fillMaxWidth()
//                   .padding(16.dp),
//               value = state.currentDateTime.value,
//               onValueChange = {
//                   state.currentDateTime.value = it
//               },
//               placeholder = {
//                   Text(text = "currentDateTime")
//               }
//           )
            Row(
                modifier = Modifier
                    .padding(top = 60.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
               // Spacer(modifier = Modifier.weight(1f))
                FloatingActionButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(start = 50.dp),
                    onClick = {
                        if (state.title.value.isNotEmpty()) { //prevent from adding blank notes
                            onEvent(
                                ShortNotesEvent.SaveShortNote(
                                    title = state.title.value,
                                    currentDateTime = state.currentDateTime.value,
                                )
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = "Add the note to shortNoteBook"
                    )
                }
               // complete the rest in tutorial

            }
//            FloatingActionButton(onClick = {
//                onEvent(
//                    ShortNotesEvent.SaveShortNote(
//                        title = state.title.value,
//                        currentDateTime = state.currentDateTime.value
//                    )
//                )
//                state.title.value = ""
//                state.currentDateTime.value = ""
//                navController.navigate("AddShortNotesScreen")
//            }) {
//                Icon(imageVector = Icons.Rounded.Add, contentDescription = "add a new note")
//            }
//           FloatingActionButton(onClick = {
//               state.title.value = ""
//               state.currentDateTime.value = ""
//               navController.navigate("AddShortNotesScreen")
//           }) {
//               Icon(imageVector = Icons.Rounded.Add, contentDescription = "add a new note")
//           }
        },
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "ShortNotesBook - සටහන් පොත",
                    modifier = Modifier.weight(1f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                    )

                Text(
                    text = "Sort:",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary

                )
                IconButton(
                    onClick = {
                        onEvent(ShortNotesEvent.SortShortNotes)},
                ) {
                    Icon(
                    imageVector = Icons.Rounded.NoteAlt,
                    contentDescription = "sort short notes!..",
                    modifier = Modifier
                        .size(35.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }
        },
//       floatingActionButton = {
//           TextField(
//               modifier = Modifier
//                   .fillMaxWidth()
//                   .padding(16.dp),
//               value = state.title.value,
//               onValueChange = {
//                   state.title.value = it
//               },
//               textStyle = TextStyle(
//                   fontWeight = FontWeight.SemiBold,
//                   fontSize = 17.sp
//               ),
//               placeholder = {
//                   Text(text = "Title")
//               }
//           )
//
//           TextField(
//               modifier = Modifier
//                   .fillMaxWidth()
//                   .padding(16.dp),
//               value = state.currentDateTime.value,
//               onValueChange = {
//                   state.currentDateTime.value = it
//               },
//               placeholder = {
//                   Text(text = "currentDateTime")
//               }
//           )
//
//           FloatingActionButton(onClick = {
//               onEvent(
//                   ShortNotesEvent.SaveShortNote(
//                       title = state.title.value,
//                       currentDateTime = state.currentDateTime.value
//                   )
//               )
//               state.title.value = ""
//               state.currentDateTime.value = ""
//               navController.navigate("AddShortNotesScreen")
//           }) {
//                Icon(imageVector = Icons.Rounded.Add, contentDescription = "add a new note")
//           }
////           FloatingActionButton(onClick = {
////               state.title.value = ""
////               state.currentDateTime.value = ""
////               navController.navigate("AddShortNotesScreen")
////           }) {
////               Icon(imageVector = Icons.Rounded.Add, contentDescription = "add a new note")
////           }
//       }
    ) { paddingValues ->


        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 87.dp)
                .padding(8.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.shortNotes.size) { index ->
                ShortNoteItem(
                    state = state,
                    index = index,
                    onEvent = onEvent
                )
            }

        }

    }
}

@Composable
fun ShortNoteItem(
    state: ShortNoteState,
    index: Int,
    onEvent: (ShortNotesEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp)

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            SelectionContainer {
                Text(
                    text = state.shortNotes[index].title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.shortNotes[index].currentDateTime,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

        IconButton(
            onClick = {
                onEvent(ShortNotesEvent.DeleteShortNote(state.shortNotes[index]))
            }
        ) {

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete ShortNote",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            
        }

    }
}

@Preview //this shows an error, but its working
@Composable
private fun PreviewShortNotesScreen(
    //@PreviewParameter(FeatureScreenPreviewParamProvider::class) state: ShortNoteState
) {
    ShortNotesScreen(state = ShortNoteState()) {
           //ShortNoteItem(state = ShortNoteState(), index = state.shortNotes.size) {
    }
}
//abstract class FeatureScreenPreviewParamProvider : PreviewParameterProvider<ViewModelProvider>

//@Composable
//fun customButton(
//    title: String,
//    isOutline: Boolean,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
//    ) {
//    if(isOutline){
//        OutlinedButton(
//            onClick = onClick,
//            shape = RectangleShape,
//            contentPadding = PaddingValues(16.dp),
//            modifier = modifier,
//            border = BorderStroke(
//                width = 1.dp,
//                color = MaterialTheme.colorScheme.onPrimaryContainer
//            )
//        ) {
//            Text(text = title)
//        }
//    } else {
//        Button(
//            onClick = onClick,
//            shape = RectangleShape,
//            contentPadding = PaddingValues(16.dp),
//            modifier = Modifier
//        ) {
//            Text(text = title)
//        }
//    }
//
//}
//
//
//@Preview
//@Composable
//private fun customButtonpPreview() {
//    customButton(title = "previewTest",
//        isOutline = true,
//        onClick = { }
//    )
//}

//@Composable
//fun ConstraintLayoutScreen() {
//    ConstraintLayout(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.primary)
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//    ) {
//
//    }
//}
