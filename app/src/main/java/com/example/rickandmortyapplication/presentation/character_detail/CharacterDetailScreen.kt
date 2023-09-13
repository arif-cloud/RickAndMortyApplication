package com.example.rickandmortyapplication.presentation.character_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmortyapplication.ui.theme.Grey

@Composable
fun CharacterDetailScreen(
    navController: NavController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val state = viewModel.characterDetailState.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.data?.let {characterDetail ->
            Column {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }, content = {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
                    })
                    Text(text = characterDetail.name ?: "", modifier = Modifier.weight(1F), textAlign = TextAlign.Center, fontSize = 28.sp)
                }
                Box(modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 20.dp)) {
                    Card(shape = RoundedCornerShape(10.dp)) {
                        AsyncImage(model = characterDetail.image, contentDescription = null, modifier = Modifier
                            .width(220.dp)
                            .height(280.dp), contentScale = ContentScale.Crop)
                    }
                }
                characterDetail.episode?.let {
                    val episodeList = it.map {url -> url?.substring(40) }
                    Text(text = "Episodes :", modifier = Modifier.padding(start = 10.dp), fontSize = 20.sp)
                    LazyRow(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
                        items(episodeList) {episode ->
                            Card(shape = RoundedCornerShape(5.dp), colors = CardDefaults.cardColors(containerColor = Grey), modifier = Modifier.padding(start = 10.dp)) {
                                Text(text = episode ?: "", modifier = Modifier.padding(10.dp), color = Color.White)
                            }
                        }
                    }
                }
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