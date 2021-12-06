package com.benb.flashcards

import android.app.Application
import com.benb.flashcards.data.CardRoomDatabase

class FlashCardsApplication: Application() {

    val database: CardRoomDatabase by lazy { CardRoomDatabase.getDatabase(this) }

}