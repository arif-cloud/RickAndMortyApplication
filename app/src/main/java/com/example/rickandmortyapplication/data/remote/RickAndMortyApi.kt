package com.example.rickandmortyapplication.data.remote

import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.data.remote.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters() : CharacterResponse

    @GET("character/{character_id}")
    suspend fun getCharacter(@Path("character_id") characterId : Int) : CharacterDto

}