package com.warh.viewmodel_practice01.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class QuoteModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "author") val author: String
)
