package com.example.rickandmortyapplication.domain.repository

import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.data.remote.response.CharacterResponse

interface RickAndMortyRepository {
    suspend fun getAllCharacters(page : Int) : CharacterResponse

    suspend fun getCharacter(characterId : Int) : CharacterDto

}