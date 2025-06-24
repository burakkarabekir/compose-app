package com.bksd.word_data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bksd.word_data.local.converter.StringListConverter
import com.bksd.word_data.local.dao.WordDao
import com.bksd.word_data.local.entity.WordEntity

@Database(entities = [WordEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}