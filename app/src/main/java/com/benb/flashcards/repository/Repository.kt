package com.benb.flashcards.repository

import com.benb.flashcards.data.FLashCardDao
import com.benb.flashcards.data.FlashCard

class FlashCardRepository constructor(private val fLashCardDao: FLashCardDao) {

    fun getFlashCards() = fLashCardDao.getFlashCards()
}