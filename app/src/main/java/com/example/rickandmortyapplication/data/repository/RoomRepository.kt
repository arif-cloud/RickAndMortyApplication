package com.example.rickandmortyapplication.data.repository

import com.example.rickandmortyapplication.data.local.CharacterDao
import com.example.rickandmortyapplication.data.local.CharacterEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val dao: CharacterDao
) {

    val getAllCharacters : List<CharacterEntity> = dao.getAllCharacters()

    suspend fun insertAllCharacters(characters : List<CharacterEntity>) {
        dao.insertAllCharacters(characters)
    }

    suspend fun clearAllCharacters() {
        dao.clearAllCharacters()
    }

}