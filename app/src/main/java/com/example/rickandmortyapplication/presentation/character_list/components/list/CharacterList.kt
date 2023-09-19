package com.example.rickandmortyapplication.presentation.character_list.components.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmortyapplication.domain.model.Character
import com.example.rickandmortyapplication.presentation.character_list.components.pagination.PaginationLoadingItem
import com.example.rickandmortyapplication.presentation.screen.Screen

@Composable
fun CharacterList(
    characterList : LazyPagingItems<Character>,
    navController : NavController
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(count = characterList.itemCount) {index ->
            characterList[index]?.let {character ->
                CharacterListItem(character = character, onItemClick = { navController.navigate("${Screen.CharacterDetailScreen.route}/${character.id}") })
            }
        }
        if (characterList.loadState.append is LoadState.Loading) {
            item {
                PaginationLoadingItem()
            }
        }
    }
}