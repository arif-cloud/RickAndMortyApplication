package com.example.rickandmortyapplication.presentation.character_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.common.Constants
import com.example.rickandmortyapplication.data.mappers.toCharacterDetail
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: RickAndMortyRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _characterDetailState = mutableStateOf(CharacterDetailState())
    val characterDetailState : State<CharacterDetailState> get() = _characterDetailState

    init {
        savedStateHandle.get<Int>(Constants.PARAM_CHARACTER_ID)?.let {characterId ->
            fetchData(characterId)
        }
    }

    private fun fetchData(characterId : Int) {
        viewModelScope.launch {
            try {
                _characterDetailState.value = CharacterDetailState(isLoading = true)
                val characterDetail = repository.getCharacter(characterId).toCharacterDetail()
                _characterDetailState.value = CharacterDetailState(data = characterDetail)
            } catch (e : HttpException) {
                _characterDetailState.value = CharacterDetailState(error = e.message())
            }
        }
    }
}