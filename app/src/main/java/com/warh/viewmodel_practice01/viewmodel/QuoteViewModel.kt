package com.warh.viewmodel_practice01.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.warh.viewmodel_practice01.model.QuoteModel
import com.warh.viewmodel_practice01.repository.QuoteProvider

class QuoteViewModel: ViewModel() {
    val quote = MutableLiveData<QuoteModel>()

    fun randomQuote(){
        val currentQuote = QuoteProvider.getRandomQuote()
        quote.postValue(currentQuote)
    }
}