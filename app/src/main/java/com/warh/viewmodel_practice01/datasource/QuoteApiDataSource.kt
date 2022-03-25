package com.warh.viewmodel_practice01.datasource

import com.warh.viewmodel_practice01.model.QuoteModel
import retrofit2.http.GET

interface QuoteApiDataSource {

    @GET("/random")
    suspend fun getQuote(): QuoteModel
}