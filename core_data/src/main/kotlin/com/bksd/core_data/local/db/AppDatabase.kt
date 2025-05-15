package com.bksd.core_data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bksd.core_data.local.dao.WordDao
import com.bksd.core_data.local.entity.WordEntity

@Database(entities = [WordEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}