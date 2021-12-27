package com.benb.flashcards.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FlashCard::class], version = 4)

abstract class FlashCardRoomDatabase : RoomDatabase() {

    abstract fun flashCardDao(): FLashCardDao

    companion object {
        @Volatile
        private var INSTANCE: FlashCardRoomDatabase? = null

        fun getDatabase(context: Context): FlashCardRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashCardRoomDatabase::class.java,
                    "flash_card_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
