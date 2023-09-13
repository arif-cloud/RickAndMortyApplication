package com.example.rickandmortyapplication.domain.repository

import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.data.remote.response.CharacterResponse

interface RickAndMortyRepository {
    suspend fun getAllCharacters() : CharacterResponse

    suspend fun getCharacter(characterId : Int) : CharacterDto

}