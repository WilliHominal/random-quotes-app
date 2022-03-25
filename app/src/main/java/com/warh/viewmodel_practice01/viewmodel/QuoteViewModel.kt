package com.warh.viewmodel_practice01.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.warh.viewmodel_practice01.model.QuoteModel
import com.warh.viewmodel_practice01.repository.QuoteProvider
import com.warh.viewmodel_practice01.repository.QuoteRepositoryImpl
import kotlinx.coroutines.runBlocking

class QuoteViewModel: ViewModel() {
    val quote = MutableLiveData<QuoteModel>()

    fun randomQuote(option: Int){
        val currentQuote: QuoteModel = when (option){
            0 -> QuoteProvider.getRandomQuote()
            1 -> runBlocking { QuoteRepositoryImpl().getQuote() }
            2 -> QuoteProvider.getRandomQuote() //TODO: implementar getQuote de DB
            else -> throw Exception("Datasource undefined")
        }

        quote.postValue(currentQuote)
    }
}