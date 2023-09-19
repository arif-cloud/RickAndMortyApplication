package com.example.rickandmortyapplication.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.data.mappers.toCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    pager : Pager<Int, CharacterEntity>
) : ViewModel() {

    val characterList = pager.flow.map {pagingData ->
        pagingData.map { it.toCharacter() }
    }.cachedIn(scope = viewModelScope)

}