package com.example.rickandmortyapplication.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.rickandmortyapplication.MainDispatcherRule
import com.example.rickandmortyapplication.common.Constants
import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.data.remote.dto.Location
import com.example.rickandmortyapplication.data.remote.dto.Origin
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import com.example.rickandmortyapplication.presentation.character_detail.CharacterDetailViewModel
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
class CharacterDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel : CharacterDetailViewModel

    @Mock
    private lateinit var repository : RickAndMortyRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        val savedStateHandle = SavedStateHandle()
        savedStateHandle[Constants.PARAM_CHARACTER_ID] = 123
        viewModel = CharacterDetailViewModel(repository, savedStateHandle)
    }

    @Test
    fun testFetchDataSuccess() = runTest {
        val fakeCharacter = CharacterDto("2017-11-04T18:50:21.651Z", listOf(),"Male",4,"https://api/image", Location("Earth",""),"Rick", Origin("Earth",""),"Human","Alive","","https://api/character")
        Mockito.`when`(repository.getCharacter(123)).thenReturn(fakeCharacter)
        assertThat(viewModel.characterDetailState.value.isLoading).isFalse()
        assertThat(viewModel.characterDetailState.value.data).isNotNull()
        assertThat(viewModel.characterDetailState.value.error).isEmpty()
    }
}