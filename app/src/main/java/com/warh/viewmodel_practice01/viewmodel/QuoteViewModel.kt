package com.warh.viewmodel_practice01.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.warh.viewmodel_practice01.model.QuoteModel
import com.warh.viewmodel_practice01.repository.QuoteProvider
import com.warh.viewmodel_practice01.repository.QuoteRepositoryImpl
import kotlinx.coroutines.runBlocking

class QuoteViewModel(application: Application): AndroidViewModel(application) {
    val quote = MutableLiveData<QuoteModel>()

    fun randomQuote(option: Int){
        val currentQuote: QuoteModel = when (option){
            0 -> QuoteProvider.getRandomQuote()
            1 -> runBlocking { QuoteRepositoryImpl().getQuote(1) }
            2 -> runBlocking { QuoteRepositoryImpl().getQuote(2, getApplication<Application>().applicationContext) }
            else -> throw Exception("Datasource undefined")
        }

        quote.postValue(currentQuote)
    }

    fun addFavoriteQuote(quote: QuoteModel){
        runBlocking { QuoteRepositoryImpl().addQuote(quote, getApplication<Application>().applicationContext) }
    }
}