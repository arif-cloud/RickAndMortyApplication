package com.example.rickandmortyapplication.repository

import com.example.rickandmortyapplication.data.remote.RickAndMortyApi
import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.data.remote.dto.Info
import com.example.rickandmortyapplication.data.remote.dto.Location
import com.example.rickandmortyapplication.data.remote.dto.Origin
import com.example.rickandmortyapplication.data.remote.response.CharacterResponse
import com.example.rickandmortyapplication.data.repository.RickAndMortyRepositoryImpl
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RickAndMortyRepositoryTest {

    private lateinit var repository: RickAndMortyRepository

    @Mock
    private lateinit var api : RickAndMortyApi

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = RickAndMortyRepositoryImpl(api)
    }

    @Test
    fun getAllCharactersTest() = runTest {
        val exampleCharacterResponse = CharacterResponse(Info(20,"next",20,"prev"), listOf())
        Mockito.`when`(api.getAllCharacters(1)).thenReturn(exampleCharacterResponse)
        val result = repository.getAllCharacters(1)
        assertThat(result).isEqualTo(exampleCharacterResponse)
    }

    @Test
    fun getCharacterTest() = runTest {
        val exampleCharacterDto = CharacterDto("2017-11-04T18:50:21.651Z", listOf(),"Male",4,"https://api/image", Location("Earth",""),"Rick", Origin("Earth",""),"Human","Alive","","https://api/character")
        Mockito.`when`(repository.getCharacter(2)).thenReturn(exampleCharacterDto)
        val result = repository.getCharacter(2)
        assertThat(result).isEqualTo(exampleCharacterDto)
    }

}