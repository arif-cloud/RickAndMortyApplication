package com.example.rickandmortyapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortyapplication.presentation.character_detail.CharacterDetailScreen
import com.example.rickandmortyapplication.presentation.character_list.CharacterListScreen
import com.example.rickandmortyapplication.presentation.screen.Screen
import com.example.rickandmortyapplication.ui.theme.RickAndMortyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.CharacterListScreen.route) {
                    composable(route = Screen.CharacterListScreen.route) {
                        CharacterListScreen(navController = navController)
                    }
                    composable(route = "${Screen.CharacterDetailScreen.route}/{character_id}", arguments = listOf(navArgument("character_id") {type = NavType.IntType})) {
                        CharacterDetailScreen(navController = navController)
                    }
                }
            }
        }
    }
}