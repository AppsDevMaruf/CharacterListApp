package com.maruf.characterlistapp.model

import com.google.gson.annotations.SerializedName

data class CharactersModel(
    val results: MutableList<CharacterModelItem>
)