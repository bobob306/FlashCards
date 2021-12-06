package com.benb.flashcards.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "cards")
data class FlashCard(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo (name = "question")
    val question: String,
    @ColumnInfo (name = "answer")
    val answer: String
)
