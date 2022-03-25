package com.warh.viewmodel_practice01.datasource

import androidx.room.*
import com.warh.viewmodel_practice01.model.QuoteModel

@Database(entities = [QuoteModel::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun QuoteDatabaseDataSource(): QuoteDatabaseDataSource
}

@Dao
interface QuoteDatabaseDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuote(quote: QuoteModel)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes() : List<QuoteModel>
}