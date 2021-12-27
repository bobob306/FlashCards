package com.benb.flashcards

import androidx.lifecycle.*
import com.benb.flashcards.data.FLashCardDao
import com.benb.flashcards.data.FlashCard
import com.benb.flashcards.databinding.ListViewItemBinding
import com.benb.flashcards.recyclerView.FlashCardAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FlashCardViewModel (private val fLashCardDao: FLashCardDao) : ViewModel() {

    /**
     * Adding flash cards to database
     */

    private fun insertFlashCard(flashCard: FlashCard){
        viewModelScope.launch {
            fLashCardDao.insert(flashCard)
        }
    }

    fun addNewFlashCard(question: String, answer: String) {
        val newFLashCard = getNewFlashCard(question, answer)
        insertFlashCard(newFLashCard)
    }

    private fun getNewFlashCard(question: String, answer: String): FlashCard {
        return FlashCard(question = question, answer = answer)
    }

    fun isEntryValid(question: String, answer: String): Boolean {
        if (question.isBlank() || answer.isBlank()) {
            return false }
        return true
    }

    val allFlashCards: LiveData<List<FlashCard>> = fLashCardDao.getFlashCards().asLiveData()


    /***
     ** End of adding flash cards to database
     ***/

    /**
     * Updating flash cards in database
     */

    fun updateFlashCard(id: Int, question: String, answer: String) {
        val updatedFlashCard = getUpdatedFlashCard(id, question, answer)
        updateFlashCard(updatedFlashCard)
    }

    fun getUpdatedFlashCard(id: Int, question: String, answer: String): FlashCard {
        return FlashCard(id, question, answer)
    }

    private fun updateFlashCard(flashCard: FlashCard) {
        viewModelScope.launch {
            fLashCardDao.update(flashCard)
        }
    }

    /***
     ** End of updating flash cards in database
     ***/

    fun retrieveFlashCard(id: Int): LiveData<FlashCard> {
        return fLashCardDao.getFlashCard(id).asLiveData()
    }

}

class FlashCardViewModelFactory(private val fLashCardDao: FLashCardDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlashCardViewModel::class.java)) {
            @Suppress ("UNCHECKED_CAST")
            return FlashCardViewModel(fLashCardDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}