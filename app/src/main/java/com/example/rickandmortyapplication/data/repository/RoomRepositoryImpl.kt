package com.example.rickandmortyapplication.data.repository

import com.example.rickandmortyapplication.data.local.CharacterDao
import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val dao: CharacterDao
) : RoomRepository {

    override fun getAllCharacters(): List<CharacterEntity> {
        return dao.getAllCharacters()
    }

    override suspend fun insertAllCharacters(characters : List<CharacterEntity>) {
        dao.insertAllCharacters(characters)
    }

    override suspend fun clearAllCharacters() {
        dao.clearAllCharacters()
    }

}