package com.example.rickandmortyapplication.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmortyapplication.data.local.CharacterDatabase
import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.data.mappers.toCharacterEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val api: RickAndMortyApi,
    private val characterDatabase: CharacterDatabase
) : RemoteMediator<Int, CharacterEntity>() {
    private val characterDao = characterDatabase.characterDao
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    (lastItem.id / state.config.pageSize) + 1
                }
            }
            val characters = api.getAllCharacters(
                page = loadKey
            )
            characterDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAllCharacters()
                }
                val characterEntities = characters.results.map { it.toCharacterEntity() }
                characterDao.insertAllCharacters(characterEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = characters.results.isEmpty()
            )
        } catch (e : IOException) {
            MediatorResult.Error(e)
        } catch (e : HttpException) {
            MediatorResult.Error(e)
        }
    }
}