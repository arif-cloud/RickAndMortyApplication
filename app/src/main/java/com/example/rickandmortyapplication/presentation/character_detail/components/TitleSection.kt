package com.example.rickandmortyapplication.presentation.character_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rickandmortyapplication.R

@Composable
fun TitleSection(
    characterName : String?,
    color : Color,
    navController: NavController
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { navController.popBackStack() }, content = {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
        })
        Text(text = characterName ?: "", modifier = Modifier.weight(1F), textAlign = TextAlign.Center, fontSize = 28.sp)
        Icon(painter = painterResource(id = R.drawable.status), contentDescription = null, tint = color)
    }
}