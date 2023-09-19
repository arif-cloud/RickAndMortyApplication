package com.example.rickandmortyapplication.presentation.character_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortyapplication.presentation.character_list.components.list.CharacterList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val characterList = viewModel.characterList.collectAsLazyPagingItems()
    val context = LocalContext.current
    val refreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(refreshing = refreshing, onRefresh = { characterList.refresh() })
    LaunchedEffect(key1 = characterList.loadState) {
        if (characterList.loadState.refresh is LoadState.Error) {
            Toast.makeText(context, "Error: " + (characterList.loadState.refresh as LoadState.Error).error.message, Toast.LENGTH_LONG).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState)) {
        if (characterList.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        CharacterList(characterList = characterList, navController = navController)
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}