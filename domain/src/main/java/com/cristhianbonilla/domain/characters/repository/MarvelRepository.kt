package com.cristhianbonilla.domain.characters.repository

import com.cristhianbonilla.domain.characters.model.detail.CharacterDetailResponseModel
import com.cristhianbonilla.domain.characters.model.list.CharacterResponseModel
import com.cristhianbonilla.support.config.ResultDomain

interface MarvelRepository {
    suspend fun getCharacterList(): ResultDomain<CharacterResponseModel>
    suspend fun getCharacterById(characterId: String): ResultDomain<CharacterDetailResponseModel>
}
