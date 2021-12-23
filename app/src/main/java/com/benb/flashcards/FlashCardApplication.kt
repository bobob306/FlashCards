package com.benb.flashcards

import android.app.Application
import com.benb.flashcards.data.FlashCardRoomDatabase

class FlashCardApplication: Application() {

    val database: FlashCardRoomDatabase by lazy { FlashCardRoomDatabase.getDatabase(this) }
}