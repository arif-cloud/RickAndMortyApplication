package com.example.rickandmortyapplication.data.mappers

import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.domain.model.Character
import com.example.rickandmortyapplication.domain.model.CharacterDetail

fun CharacterDto.toCharacter() : Character {
    return Character(
        id = id,
        name = name,
        image = image,
        location = location,
        species = species,
        status = status
    )
}

fun CharacterDto.toCharacterDetail() : CharacterDetail {
    return CharacterDetail(
        id = id,
        name = name,
        image = image,
        location = location,
        species = species,
        status = status,
        episode = episode,
        gender = gender,
        origin = origin
    )
}