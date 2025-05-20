package com.bksd.word_data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bksd.word_data.local.dao.WordDao
import com.bksd.word_data.local.entity.WordEntity

@Database(entities = [WordEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}