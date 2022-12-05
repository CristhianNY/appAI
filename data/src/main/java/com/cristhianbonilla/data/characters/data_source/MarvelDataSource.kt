package com.cristhianbonilla.data.characters.data_source

import com.cristhianbonilla.data.characters.entity.detail.CharacterDetailResponseEntity
import com.cristhianbonilla.data.characters.entity.list.CharacterResponseEntity
import com.cristhianbonilla.support.config.Result

interface MarvelDataSource {
    suspend fun getCharacterList(): Result<CharacterResponseEntity>
    suspend fun getCharacterById(characterId: String): Result<CharacterDetailResponseEntity>
}
