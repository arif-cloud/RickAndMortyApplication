package com.example.rickandmortyapplication.domain.repository

import com.example.rickandmortyapplication.data.local.CharacterEntity

interface RoomRepository {

    fun getAllCharacters() : List<CharacterEntity>

    suspend fun insertAllCharacters(characters : List<CharacterEntity>)

    suspend fun clearAllCharacters()
}