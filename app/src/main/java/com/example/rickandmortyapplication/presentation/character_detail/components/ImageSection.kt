package com.example.rickandmortyapplication.presentation.character_detail.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageSection(
    characterImage : String?
) {
    Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(vertical = 20.dp)) {
        AsyncImage(model = characterImage, contentDescription = null, modifier = Modifier
            .width(220.dp)
            .height(280.dp), contentScale = ContentScale.Crop)
    }
}