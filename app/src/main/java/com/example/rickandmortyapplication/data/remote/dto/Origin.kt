package com.example.rickandmortyapplication.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)