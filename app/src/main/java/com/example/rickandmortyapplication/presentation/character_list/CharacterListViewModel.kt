package com.example.rickandmortyapplication.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.data.mappers.toCharacterEntity
import com.example.rickandmortyapplication.domain.model.Character
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import com.example.rickandmortyapplication.domain.repository.RoomRepository
import com.example.rickandmortyapplication.paging.CharactersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: RickAndMortyRepository,
    private val characterRepository : RoomRepository
) : ViewModel() {

    val characterList = Pager(PagingConfig(pageSize = 1)) {
        CharactersPagingSource(repository)
    }.flow.cachedIn(scope = viewModelScope)

    fun clearAllData() {
        viewModelScope.launch {
            characterRepository.clearAllCharacters()
        }
    }

    fun getCharactersData() : List<CharacterEntity> {
        return characterRepository.getAllCharacters()
    }

    fun saveCharactersData(lazyPagingItems: LazyPagingItems<Character>) {
        viewModelScope.launch {
            val characterEntityList = mutableListOf<CharacterEntity>()
            lazyPagingItems.itemSnapshotList.items.forEach{character ->
                val characterEntity = character.toCharacterEntity()
                characterEntityList.add(characterEntity)
            }
            characterRepository.insertAllCharacters(characterEntityList)
        }
    }

}