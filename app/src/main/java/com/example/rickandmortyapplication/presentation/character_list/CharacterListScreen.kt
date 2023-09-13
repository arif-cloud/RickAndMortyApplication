package com.example.rickandmortyapplication.presentation.character_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rickandmortyapplication.presentation.character_list.components.CharacterListItem
import com.example.rickandmortyapplication.presentation.screen.Screen

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state = viewModel.characterListState.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.data?.let {characters ->
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(characters) {character ->
                    CharacterListItem(character = character, onItemClick = { navController.navigate("${Screen.CharacterDetailScreen.route}/${character.id}") })
                }
            }
        }
        if (state.error.isNotEmpty()) {
            Text(text = state.error, color = Color.Red, modifier = Modifier.align(Alignment.Center))
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}