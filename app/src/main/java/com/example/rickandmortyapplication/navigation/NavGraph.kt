package com.example.rickandmortyapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rickandmortyapplication.common.Constants
import com.example.rickandmortyapplication.presentation.character_detail.CharacterDetailScreen
import com.example.rickandmortyapplication.presentation.character_list.CharacterListScreen
import com.example.rickandmortyapplication.presentation.screen.Screen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.CharacterListScreen.route) {
        composable(route = Screen.CharacterListScreen.route) {
            CharacterListScreen(navController = navController)
        }
        composable(route = "${Screen.CharacterDetailScreen.route}/{character_id}", arguments = listOf(
            navArgument(Constants.PARAM_CHARACTER_ID) {type = NavType.IntType})) {
            CharacterDetailScreen(navController = navController)
        }
    }
}