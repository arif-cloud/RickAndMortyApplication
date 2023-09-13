package com.example.rickandmortyapplication.data.remote.response

import com.example.rickandmortyapplication.data.remote.dto.CharacterDto
import com.example.rickandmortyapplication.data.remote.dto.Info
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info : Info?,
    @SerializedName("results")
    val results : List<CharacterDto>
)
