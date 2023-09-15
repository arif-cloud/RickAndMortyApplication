package com.example.rickandmortyapplication.presentation.character_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapplication.data.remote.dto.Location
import com.example.rickandmortyapplication.domain.model.CharacterDetail
import com.example.rickandmortyapplication.presentation.character_detail.components.FeatureSection
import com.example.rickandmortyapplication.presentation.character_detail.components.ImageSection
import com.example.rickandmortyapplication.presentation.character_detail.components.TitleSection
import com.example.rickandmortyapplication.ui.theme.Green
import com.example.rickandmortyapplication.ui.theme.Grey

@Composable
fun CharacterDetailScreen(
    navController: NavController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val state = viewModel.characterDetailState.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.data?.let {characterDetail ->
            val color : Color = if(characterDetail.status == "Alive") Green else if(characterDetail.status == "unknown") Grey else Color.Red
            Column(modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                TitleSection(characterName = characterDetail.name, color = color, navController = navController)
                ImageSection(characterImage = characterDetail.image)
                FeatureSection(characterDetail = characterDetail, color = color)
            }
        }
        if (state.error.isNotEmpty()) {
            Text(text = state.error, color = Color.Red, modifier = Modifier.align(Alignment.Center))
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterDetailScreenPreview() {
    CharacterDetailScreenContent()
}

@Composable
fun CharacterDetailScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TitleSection(characterName = "CharacterName", color = Color.Green, navController = rememberNavController())
            ImageSection(characterImage = "")
            FeatureSection(characterDetail = CharacterDetail("",null,"", Location("",""),"",null,"","", emptyList()), color = Color.Green)
        }
    }
}