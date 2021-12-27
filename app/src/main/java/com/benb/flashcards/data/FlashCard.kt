package com.benb.flashcards.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "flashcard")
data class FlashCard(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo (name = "question")
    val question: String = "0",
    @ColumnInfo (name = "answer")
    val answer: String = ""
)

