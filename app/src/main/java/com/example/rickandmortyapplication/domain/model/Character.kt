package com.example.rickandmortyapplication.domain.model

import com.example.rickandmortyapplication.data.remote.dto.Location
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("species")
    val species: String?
)
