package com.example.rickandmortyapplication.presentation.character_list.components.list

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val context : Context = LocalContext.current
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        if (characterList.loadState.refresh is LoadState.Error) {
            Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show()
            val entityList = viewModel.getCharactersData()
            items(entityList) {
                val character = it.toCharacter()
                CharacterListItem(character = character, onItemClick = {})
            }
        } else {
            items(count = characterList.itemCount) {index ->
                characterList[index]?.let {character ->
                    CharacterListItem(character = character, onItemClick = { navController.navigate("${Screen.CharacterDetailScreen.route}/${character.id}") })
                }
            }
        }
        if (characterList.loadState.append is LoadState.Loading) {
            item {
                PaginationLoadingItem()
            }
        }
    }
}