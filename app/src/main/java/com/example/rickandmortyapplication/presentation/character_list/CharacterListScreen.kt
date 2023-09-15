package com.example.rickandmortyapplication.presentation.character_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortyapplication.presentation.character_list.components.list.CharacterList
import com.example.rickandmortyapplication.presentation.character_list.components.pagination.PaginationEmptyItem
import com.example.rickandmortyapplication.presentation.character_list.components.pagination.PaginationErrorItem

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val characterList = viewModel.characterList.collectAsLazyPagingItems()
        CharacterList(characterList = characterList, navController = navController)
        when(characterList.loadState.refresh) {
            is LoadState.Loading -> { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
            is LoadState.Error -> { PaginationErrorItem { characterList.refresh() } }
            is LoadState.NotLoading -> { if (characterList.itemCount < 1) { PaginationEmptyItem() } }
        }
    }
}