package com.example.rickandmortyapplication.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import com.example.rickandmortyapplication.paging.CharactersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository : RickAndMortyRepository
) : ViewModel() {

    val characterList = Pager(PagingConfig(1)) {
        CharactersPagingSource(repository)
    }.flow.cachedIn(scope = viewModelScope)

}