package com.example.rickandmortyapplication.presentation.character_list.components.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmortyapplication.data.mappers.toCharacter
import com.example.rickandmortyapplication.domain.model.Character
import com.example.rickandmortyapplication.presentation.character_list.CharacterListViewModel
import com.example.rickandmortyapplication.presentation.character_list.components.pagination.PaginationLoadingItem
import com.example.rickandmortyapplication.presentation.screen.Screen

@Composable
fun CharacterList(
    characterList : LazyPagingItems<Character>,
    navController : NavController,
    viewModel: CharacterListViewModel
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        if (viewModel.getCharactersData().isNotEmpty()) {
            items(viewModel.getCharactersData()) {
                val character = it.toCharacter()
                CharacterListItem(character = character, onItemClick = { navController.navigate("${Screen.CharacterDetailScreen.route}/${character.id}") })
            }
        }
        items(count = characterList.itemCount) {index ->
            characterList[index]?.let {character ->
                CharacterListItem(character = character, onItemClick = { navController.navigate("${Screen.CharacterDetailScreen.route}/${character.id}") })
            }
        }
        if (characterList.loadState.append is LoadState.Loading) {
            item {
                PaginationLoadingItem()
            }
            viewModel.clearAllData()
            viewModel.saveCharactersData(characterList)
        }
    }
}