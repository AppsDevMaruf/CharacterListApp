package com.maruf.characterlistapp.data

import com.maruf.characterlistapp.data.network.CharactersApi
import com.maruf.characterlistapp.model.CharactersModel
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val charactersApi: CharactersApi
) {
    suspend fun getCharacters(): Response<CharactersModel> {
        return charactersApi.getCharacters()

    }
}