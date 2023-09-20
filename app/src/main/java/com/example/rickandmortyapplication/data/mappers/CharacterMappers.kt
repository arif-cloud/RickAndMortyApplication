package com.example.rickandmortyapplication.data.mappers

import com.example.rickandmortyapplication.data.local.CharacterEntity
import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.domain.model.Character
import com.example.rickandmortyapplication.domain.model.CharacterDetail

fun CharacterDto.toCharacter() : Character {
    return Character(
        id = id,
        name = name,
        image = image,
        status = status,
        location = location,
        gender = gender
    )
}

fun CharacterDto.toCharacterEntity() : CharacterEntity {
    return CharacterEntity(
        characterId = id,
        name = name,
        gender = gender,
        status = status,
        location = location,
        image = image
    )
}

fun Character.toCharacterEntity() : CharacterEntity {
    return CharacterEntity(
        characterId = id,
        name = name,
        image = image,
        status = status,
        location = location,
        gender = gender
    )
}

fun CharacterEntity.toCharacter() : Character {
    return Character(
        id = characterId,
        name = name,
        image = image,
        status = status,
        location = location,
        gender = gender
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