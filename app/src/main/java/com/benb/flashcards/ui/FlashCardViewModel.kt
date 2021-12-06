package com.benb.flashcards.ui

import androidx.lifecycle.*
import com.benb.flashcards.data.FlashCard
import com.benb.flashcards.data.FlashCardDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FlashCardViewModel(private val flashCardDao: FlashCardDao) : ViewModel() {

    fun allCards(): Flow<List<FlashCard>> = flashCardDao.getCards()

    val allFlashCards: LiveData<List<FlashCard>> = flashCardDao.getCards().asLiveData()

    private fun insertCard(flashCard: FlashCard) {
        viewModelScope.launch {
            flashCardDao.insert(flashCard)
        }
    }

    fun retrieveCard(id: Int): LiveData<FlashCard> {
        return flashCardDao.getCard(id).asLiveData()
    }

    fun addNewCard(question: String, answer: String) {
        val newCard = getNewCardEntry(question, answer)
        insertCard(newCard)
    }

    private fun getNewCardEntry(question: String, answer: String): FlashCard {
        return FlashCard(
            question = question,
            answer = answer
        )
    }

}

class FlashCardViewModelFactory(
    private val flashCardDao: FlashCardDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlashCardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlashCardViewModel(flashCardDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}