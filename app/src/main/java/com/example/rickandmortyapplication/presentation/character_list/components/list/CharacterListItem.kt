package com.example.rickandmortyapplication.presentation.character_list.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmortyapplication.R
import com.example.rickandmortyapplication.domain.model.Character
import com.example.rickandmortyapplication.ui.theme.Green
import com.example.rickandmortyapplication.ui.theme.Grey
import com.example.rickandmortyapplication.ui.theme.Orange

@Composable
fun CharacterListItem(
    character : Character,
    onItemClick : (Character) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 10.dp).clickable { onItemClick(character) }) {
        Card(shape = RoundedCornerShape(10.dp)) {
            AsyncImage(model = character.image, contentDescription = null, modifier = Modifier
                .width(120.dp)
                .height(160.dp), contentScale = ContentScale.Crop)
        }
        Column(modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth()) {
            Text(text = character.name ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 24.sp)
            Row(modifier = Modifier.padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.status), contentDescription = null, tint = if(character.status == "Alive") Green else if(character.status == "unknown") Grey else Color.Red)
                Text(text = character.status ?: "", modifier = Modifier.padding(start = 5.dp))
            }
            Row(modifier = Modifier.padding(top = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = when (character.gender) {
                    "Male" -> painterResource(id = R.drawable.male)
                    "Female" -> painterResource(id = R.drawable.female)
                    "Genderless" -> painterResource(id = R.drawable.genderless)
                    else -> painterResource(id = R.drawable.unknown)
                }, contentDescription = null)
                Text(text = character.gender ?: "", modifier = Modifier.padding(start = 5.dp))
            }
            Row(modifier = Modifier.padding(top = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.location), contentDescription = null, tint = Orange)
                Text(text = character.location?.name ?: "", modifier = Modifier.padding(start = 5.dp))
            }
        }
    }
}