package com.example.rickandmortyapplication.presentation.character_detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EpisodeList(
    episodeUrlList : List<String?>,
    color: Color
) {
    val episodeList = episodeUrlList.map {url -> url?.substring(40) }
    Text(text = "Episodes :", fontSize = 20.sp)
    LazyRow {
        items(episodeList) {episode ->
            Card(shape = RoundedCornerShape(5.dp), colors = CardDefaults.cardColors(containerColor = color), modifier = Modifier.padding(end = 10.dp)) {
                Text(text = episode ?: "", modifier = Modifier.padding(10.dp), color = Color.White)
            }
        }
    }
}