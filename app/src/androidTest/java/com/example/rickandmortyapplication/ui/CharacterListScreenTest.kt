package com.example.rickandmortyapplication.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapplication.domain.repository.RickAndMortyRepository
import com.example.rickandmortyapplication.domain.repository.RoomRepository
import com.example.rickandmortyapplication.presentation.character_list.CharacterListScreen
import com.example.rickandmortyapplication.presentation.character_list.CharacterListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class CharacterListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var repository: RickAndMortyRepository

    @Mock
    private lateinit var characterRepository: RoomRepository

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = CharacterListViewModel(repository, characterRepository)
    }

    @Test
    fun screenTest() {
        composeTestRule.setContent {
            CharacterListScreen(navController = rememberNavController(), viewModel = viewModel)
        }
        composeTestRule.onNodeWithContentDescription("Character list screen").assertIsDisplayed()
    }
}