package com.example.rickandmortyapplication.repository

import com.example.rickandmortyapplication.data.local.CharacterDao
import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.data.remote.dto.Location
import com.example.rickandmortyapplication.data.repository.RoomRepositoryImpl
import com.example.rickandmortyapplication.domain.repository.RoomRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RoomRepositoryTest {

    private lateinit var roomRepository: RoomRepository

    @Mock
    private lateinit var dao : CharacterDao

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        roomRepository = RoomRepositoryImpl(dao)
    }

    private val fakeCharacterEntityList = listOf(CharacterEntity(4,14,"Rick","https://api/image","Alive", Location("Earth",""),"Male"))

    @Test
    fun getAllCharactersTest() {
        Mockito.`when`(roomRepository.getAllCharacters()).thenReturn(fakeCharacterEntityList)
        val characterEntityList = roomRepository.getAllCharacters()
        assertThat(characterEntityList).isEqualTo(fakeCharacterEntityList)
    }

    @Test
    fun insertAllCharactersTest() = runTest {
        roomRepository.insertAllCharacters(fakeCharacterEntityList)
        Mockito.verify(dao).insertAllCharacters(Mockito.anyList())
    }

    @Test
    fun clearAllCharacters() = runTest {
        roomRepository.insertAllCharacters(fakeCharacterEntityList)
        roomRepository.clearAllCharacters()
        Mockito.verify(dao).clearAllCharacters()
    }
}