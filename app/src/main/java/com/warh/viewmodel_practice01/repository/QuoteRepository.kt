package com.warh.viewmodel_practice01.repository

import com.warh.viewmodel_practice01.datasource.QuoteApiDataSource
import com.warh.viewmodel_practice01.model.QuoteModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface QuoteRepository {
    suspend fun getQuote(): QuoteModel
}

class QuoteRepositoryImpl: QuoteRepository {
    companion object {
        val retrofit: QuoteApiDataSource = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.quotable.io")
            .build()
            .create(QuoteApiDataSource::class.java)
    }

    override suspend fun getQuote(): QuoteModel {
        return retrofit.getQuote()
    }
}