package com.example.rickandmortyapplication.presentation.screen

sealed class Screen(val route : String) {
    object CharacterListScreen : Screen(route = "character_list")
    object CharacterDetailScreen : Screen(route = "character_detail")
}
