package com.example.rickandmortyapplication.viewmodel

import com.example.rickandmortyapplication.MainDispatcherRule
import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.data.remote.dto.Location
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import com.example.rickandmortyapplication.domain.repository.RoomRepository
import com.example.rickandmortyapplication.presentation.character_list.CharacterListViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CharacterListViewModel

    @Mock
    private lateinit var repository : RickAndMortyRepository

    @Mock
    private lateinit var characterRepository : RoomRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = CharacterListViewModel(repository, characterRepository)
    }

    @Test
    fun testClearAllData() = runTest {
        viewModel.clearAllData()
        Mockito.verify(characterRepository).clearAllCharacters()
    }

    @Test
    fun testGetCharactersData() {
        val fakeCharacterList = listOf(CharacterEntity(4,14,"Rick","https://api/image","Alive", Location("Earth",""),"Male"))
        Mockito.`when`(characterRepository.getAllCharacters()).thenReturn(fakeCharacterList)
        val result = viewModel.getCharactersData()
        assertThat(result).isEqualTo(fakeCharacterList)
    }

    @Test
    fun testSaveCharactersData() = runTest {
        val exampleCharacterEntityList = mutableListOf(CharacterEntity(4,14,"Rick","https://api/image","Alive", Location("Earth",""),"Male"))
        characterRepository.insertAllCharacters(exampleCharacterEntityList)
        Mockito.verify(characterRepository).insertAllCharacters(Mockito.anyList())
    }
}