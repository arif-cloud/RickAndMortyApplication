package com.example.rickandmortyapplication.presentation.character_list.components.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.data.mappers.toCharacter

@Composable
fun CharacterListWithDb(
    entityList : List<CharacterEntity>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(entityList) {
            val character = it.toCharacter()
            CharacterListItem(character = character, onItemClick = {})
        }
    }
}