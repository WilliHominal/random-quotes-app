package com.warh.viewmodel_practice01.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warh.viewmodel_practice01.ui.theme.ViewModelPractice01Theme

@Composable
fun QuoteScreen(quoteText: String,
                quoteAuthor: String,
                onClickAction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onClickAction()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
        ){
            item {
                Text(quoteText,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )
            }
        }
        Text(quoteAuthor,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteScreenPreview(){
    ViewModelPractice01Theme {
        QuoteScreen("Quote Text", "Author"){}
    }
}