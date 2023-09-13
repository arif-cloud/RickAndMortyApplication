package com.example.rickandmortyapplication.presentation.character_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapplication.data.mappers.toCharacter
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository : RickAndMortyRepository
) : ViewModel() {

    private val _characterListState = mutableStateOf(CharacterListState())
    val characterListState : State<CharacterListState> get() = _characterListState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                _characterListState.value = CharacterListState(isLoading = true)
                val response = repository.getAllCharacters()
                val characterList = response.results?.map { it.toCharacter() }
                _characterListState.value = CharacterListState(data = characterList ?: listOf())
            } catch (e : HttpException) {
                _characterListState.value = CharacterListState(error = e.message())
            }
        }
    }

}