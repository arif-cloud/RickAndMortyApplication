package com.example.rickandmortyapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapplication.data.mappers.toCharacter
import com.example.rickandmortyapplication.domain.model.Character
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository

class CharactersPagingSource(private val repository : RickAndMortyRepository) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllCharacters(currentPage)
            val data = response.results.map { it.toCharacter() }
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage.plus(1)
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }
}