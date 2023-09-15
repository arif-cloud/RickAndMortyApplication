package com.example.rickandmortyapplication.presentation.character_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapplication.domain.model.CharacterDetail

@Composable
fun FeatureSection(
    characterDetail : CharacterDetail,
    color: Color
) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = "Species  :  ${characterDetail.species}", fontSize = 20.sp)
        Text(text = "Gender  :  ${characterDetail.gender}", fontSize = 20.sp)
        Text(text = "Location  :  ${characterDetail.location?.name}", fontSize = 20.sp)
        characterDetail.episode?.let {urlList ->
            EpisodeList(episodeUrlList = urlList, color = color)
        }
    }
}