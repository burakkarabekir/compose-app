package com.bksd.word_data.local.converter

import androidx.room.TypeConverter

class StringListConverter {
    private val separator = "|"  // Choose a delimiter that won't appear in your strings

    @TypeConverter
    fun fromStringList(list: List<String>?): String? = list?.joinToString(separator)

    @TypeConverter
    fun toStringList(data: String?): List<String>? = data?.split(separator)
        ?.filter { it.isNotBlank() }?.map { it.trim() }
}