package com.benb.flashcards.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FLashCardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(flashCard: FlashCard)

    @Update
    suspend fun update(flashCard: FlashCard)

    @Delete
    suspend fun delete(flashCard: FlashCard)

    @Query ("SELECT * FROM `flashcard` WHERE id = :id" )
    fun getFlashCard(id: Int): Flow<FlashCard>

    @Query ("SELECT * FROM `flashcard` ORDER BY question ASC")
    fun getFlashCards(): Flow<List<FlashCard>>

}