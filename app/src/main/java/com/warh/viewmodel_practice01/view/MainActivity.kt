package com.warh.viewmodel_practice01.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warh.viewmodel_practice01.ui.theme.ViewModelPractice01Theme
import com.warh.viewmodel_practice01.viewmodel.QuoteViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ViewModelPractice01Theme {
                MainScreen(quoteViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(quoteViewModel: QuoteViewModel?){
    var quoteText by remember { mutableStateOf("Tap to get random quotes") }
    var quoteAuthor by remember { mutableStateOf("Made with love by Willi ♥") }

    var expandedSettings by remember { mutableStateOf(false) }
    val menuItems = listOf("Static DS", "Api REST", "Room DB")

    var datasourceSelected by remember { mutableStateOf(0) }
    val title = "Random Quotes"

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember{ SnackbarHostState() }

    quoteViewModel?.quote?.observe(LocalLifecycleOwner.current) {
        quoteText = it.content
        quoteAuthor = it.author
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Quote added to favs ♥",
                                    duration = SnackbarDuration.Short
                                )
                            }

                            quoteViewModel?.addFavoriteQuote(quoteViewModel.quote.value!!)
                        }) {
                            Icon(Icons.Filled.Favorite, "Favorite")
                        }},
                    title = { Text("$title (${menuItems[datasourceSelected]})") },
                    actions = {
                        IconButton(onClick = {
                            expandedSettings = !expandedSettings
                        }) {
                            Icon(Icons.Filled.Settings, "Settings")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopEnd)) {
                DropdownMenu(
                    expanded = expandedSettings,
                    onDismissRequest = { expandedSettings = false },
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    menuItems.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            datasourceSelected = index
                            expandedSettings = false
                        }) {
                            Text(s)
                        }
                    }
                }
            }

            CustomSnackbar(snackbarHostState = snackbarHostState) {}

            QuoteScreen(
                quoteText,
                quoteAuthor,
                onClickAction = {
                    when(datasourceSelected){
                        0 -> quoteViewModel?.randomQuote(0)
                        1 -> quoteViewModel?.randomQuote(1)
                        2 -> quoteViewModel?.randomQuote(2)
                        else -> throw Exception("Datasource undefined")
                    }

                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelPractice01Theme {
        MainScreen(null)
    }
}

@Composable
fun CustomSnackbar(
    snackbarHostState: SnackbarHostState,
    onHideSnackbar: () -> Unit
) {
    Box(
        Modifier.fillMaxWidth()
    ){
        SnackbarHost(
            modifier = Modifier.padding(8.dp),
            hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        ){
                            Text(
                                text = snackbarHostState.currentSnackbarData?.actionLabel ?: "",
                                color = Color.White
                            )

                        }
                    }
                ) {
                    Text(snackbarHostState.currentSnackbarData?.message ?: "")
                }
            }
        )
    }
}