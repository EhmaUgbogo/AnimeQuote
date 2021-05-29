package com.ehmaugbogo.animequote.api.models
import com.google.gson.annotations.SerializedName

data class AnimeQuote(
    @SerializedName("anime")
    var anime: String = "",
    @SerializedName("character")
    var character: String = "",
    @SerializedName("quote")
    var quote: String = ""
)