package com.example.rickandmortyapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(LocationConverter::class)
@Database(entities = [CharacterEntity::class] , version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract val characterDao : CharacterDao

}