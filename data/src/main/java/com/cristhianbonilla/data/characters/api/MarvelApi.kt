package com.cristhianbonilla.data.characters.api

import com.cristhianbonilla.data.characters.entity.detail.CharacterDetailResponseEntity
import com.cristhianbonilla.data.characters.entity.list.CharacterResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {
    @GET("public/characters")
    suspend fun getCharacterList(): Response<CharacterResponseEntity>

    @GET("public/characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: String): Response<CharacterDetailResponseEntity>
}
