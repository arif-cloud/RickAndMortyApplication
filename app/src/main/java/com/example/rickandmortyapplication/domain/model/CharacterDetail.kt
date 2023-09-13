package com.example.rickandmortyapplication.domain.model

import com.example.rickandmortyapplication.data.remote.dto.Location
import com.example.rickandmortyapplication.data.remote.dto.Origin
import com.google.gson.annotations.SerializedName

data class CharacterDetail(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: Origin?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("episode")
    val episode: List<String?>?
)
