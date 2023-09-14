package com.example.rickandmortyapplication.presentation.character_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortyapplication.presentation.character_list.components.CharacterListItem
import com.example.rickandmortyapplication.presentation.character_list.components.paging.PaginationEmptyItem
import com.example.rickandmortyapplication.presentation.character_list.components.paging.PaginationErrorItem
import com.example.rickandmortyapplication.presentation.character_list.components.paging.PaginationLoadingItem
import com.example.rickandmortyapplication.presentation.character_list.components.paging.PaginationFullItem
import com.example.rickandmortyapplication.presentation.screen.Screen

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val characterList = viewModel.characterList.collectAsLazyPagingItems()
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(count = characterList.itemCount) {index ->
                characterList[index]?.let {character ->
                    CharacterListItem(character = character, onItemClick = { navController.navigate("${Screen.CharacterDetailScreen.route}/${character.id}") })
                }
            }
            when(characterList.loadState.append) {
                LoadState.Loading -> {
                    item {
                        PaginationLoadingItem()
                    }
                }
                is LoadState.Error -> {
                    item {
                        PaginationFullItem()
                    }
                }
                is LoadState.NotLoading -> {}
            }
        }
        when(characterList.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is LoadState.Error -> {
                PaginationErrorItem {
                    characterList.refresh()
                }
            }
            is LoadState.NotLoading -> {
                if (characterList.itemCount < 1) {
                    PaginationEmptyItem()
                }
            }
        }
    }
}