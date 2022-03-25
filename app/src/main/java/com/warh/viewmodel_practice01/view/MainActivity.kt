package com.warh.viewmodel_practice01.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import com.warh.viewmodel_practice01.ui.theme.ViewModelPractice01Theme
import com.warh.viewmodel_practice01.viewmodel.QuoteViewModel

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
    val menuItems = listOf("Companion object", "Api REST", "Room DB")

    var title by remember { mutableStateOf("Random Quotes App") }

    quoteViewModel?.quote?.observe(LocalLifecycleOwner.current) {
        quoteText = it.content
        quoteAuthor = it.author
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title) },
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
            Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopEnd)) {
                DropdownMenu(
                    expanded = expandedSettings,
                    onDismissRequest = { expandedSettings = false },
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    menuItems.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            title = "Quotes from $s"
                            expandedSettings = false
                        }) {
                            Text(s)
                        }
                    }
                }
            }
            QuoteScreen(
                quoteText,
                quoteAuthor,
                onClickAction = {
                    quoteViewModel?.randomQuote()
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