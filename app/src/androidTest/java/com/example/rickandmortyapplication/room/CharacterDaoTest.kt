package com.example.rickandmortyapplication.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.rickandmortyapplication.data.local.CharacterDao
import com.example.rickandmortyapplication.data.local.CharacterDatabase
import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.data.remote.dto.Location
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class CharacterDaoTest {

    private lateinit var database : CharacterDatabase
    private lateinit var dao : CharacterDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), CharacterDatabase::class.java).
        allowMainThreadQueries().
        build()
        dao = database.characterDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    private val fakeCharacterList = listOf(
        CharacterEntity(4,14,"Rick","https://api/image","Alive", Location("Earth",""),"Male"),
        CharacterEntity(5,24,"Morty","https://api/image","Alive", Location("Earth",""),"Male")
    )

    @Test
    fun getAllCharactersTest() {
        val result = dao.getAllCharacters()
        assertThat(result).isEqualTo(emptyList<CharacterEntity>())
    }

    @Test
    fun insertAllCharactersTesting() = runTest {
        dao.insertAllCharacters(fakeCharacterList)
        assertThat(dao.getAllCharacters()).isNotEmpty()
    }
    @Test
    fun clearAllCharactersTesting() = runTest {
        dao.insertAllCharacters(fakeCharacterList)
        dao.clearAllCharacters()
        assertThat(dao.getAllCharacters()).isEmpty()
    }
}