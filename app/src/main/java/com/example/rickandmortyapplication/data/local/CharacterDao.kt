package com.example.rickandmortyapplication.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters() : PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllCharacters(characters : List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAllCharacters()


}