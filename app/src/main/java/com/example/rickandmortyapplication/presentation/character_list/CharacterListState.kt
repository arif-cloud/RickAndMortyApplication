package com.example.rickandmortyapplication.presentation.character_list

import com.example.rickandmortyapplication.domain.model.Character

data class CharacterListState(
    val isLoading : Boolean = false,
    val data : List<Character>? = null,
    val error : String = ""
)
