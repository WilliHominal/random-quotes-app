package com.warh.viewmodel_practice01.repository

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.warh.viewmodel_practice01.datasource.QuoteApiDataSource
import com.warh.viewmodel_practice01.datasource.QuoteDatabase
import com.warh.viewmodel_practice01.model.QuoteModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface QuoteRepository {
    suspend fun getQuote(source: Int, context: Context? = null): QuoteModel
}

class QuoteRepositoryImpl: QuoteRepository {

    companion object {
        private var database: QuoteDatabase? = null

        val retrofit: QuoteApiDataSource = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.quotable.io")
            .build()
            .create(QuoteApiDataSource::class.java)

        fun getDatabase(context: Context): QuoteDatabase{
            return database ?:
            run{
                database = Room.databaseBuilder(context, QuoteDatabase::class.java, "quotes_database")
                .fallbackToDestructiveMigration()
                .build()
                database!!
            }
        }
    }

    override suspend fun getQuote(source: Int, context: Context?): QuoteModel {
        return when(source) {
            1 -> retrofit.getQuote()
            2 -> {
                context?.let{
                    val quotesCount = getDatabase(context).QuoteDatabaseDataSource().getQuotes().size
                    return if (quotesCount > 0){
                        getDatabase(context).QuoteDatabaseDataSource().getQuotes()[(0 until quotesCount).random()]
                    } else {
                        QuoteModel(0, "No favorite quotes saved in your database", "Oops... :(")
                    }
                }
                throw Exception("No context passed")
            }
            else -> throw Exception("Undefined datasource")
        }
    }

    suspend fun addQuote(quote: QuoteModel, context: Context) {
        getDatabase(context).QuoteDatabaseDataSource().addQuote(quote)
    }
}