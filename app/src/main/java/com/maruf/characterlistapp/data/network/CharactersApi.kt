package com.maruf.characterlistapp.data.network

import com.maruf.characterlistapp.model.CharactersModel
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET("/characters")
    suspend fun getCharacters(): Response<CharactersModel>
}