package com.benb.flashcards.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FlashCard::class], version = 1, exportSchema = false)
abstract class CardRoomDatabase: RoomDatabase() {

    abstract fun cardDao(): FlashCardDao

    companion object{
        @Volatile
        private var INSTANCE: CardRoomDatabase? = null

        fun getDatabase(context: Context): CardRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardRoomDatabase::class.java,
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