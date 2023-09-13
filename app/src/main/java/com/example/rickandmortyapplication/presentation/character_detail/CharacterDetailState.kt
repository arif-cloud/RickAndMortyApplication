package com.example.rickandmortyapplication.presentation.character_detail

import com.example.rickandmortyapplication.domain.model.CharacterDetail

data class CharacterDetailState(
    val isLoading : Boolean = false,
    val data : CharacterDetail? = null,
    val error : String = ""
)
