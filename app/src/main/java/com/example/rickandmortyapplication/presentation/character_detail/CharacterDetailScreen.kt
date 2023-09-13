package com.example.rickandmortyapplication.presentation.character_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CharacterDetailScreen(
    navController: NavController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val state = viewModel.characterDetailState.value
    Box(modifier = Modifier.fillMaxSize()) {

    }
}