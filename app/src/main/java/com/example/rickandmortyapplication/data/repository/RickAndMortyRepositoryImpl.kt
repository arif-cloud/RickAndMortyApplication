package com.example.rickandmortyapplication.data.repository

import com.example.rickandmortyapplication.data.remote.RickAndMortyApi
import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.data.remote.response.CharacterResponse
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val api : RickAndMortyApi
) : RickAndMortyRepository {
    override suspend fun getAllCharacters(): CharacterResponse {
        return api.getAllCharacters()
    }

    override suspend fun getCharacter(characterId: Int): CharacterDto {
        return api.getCharacter(characterId)
    }
}