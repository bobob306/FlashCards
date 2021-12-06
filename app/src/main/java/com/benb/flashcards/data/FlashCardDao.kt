package com.benb.flashcards.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashCardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(flashCard: FlashCard)

    @Update
    suspend fun update(flashCard: FlashCard)

    @Delete
    suspend fun delete(flashCard: FlashCard)

    @Query ("SELECT * FROM cards WHERE id = :id")
    fun getCard(id: Int): Flow<FlashCard>

    @Query ("SELECT * FROM cards ORDER BY id ASC")
    fun getCards() : Flow<List<FlashCard>>

}