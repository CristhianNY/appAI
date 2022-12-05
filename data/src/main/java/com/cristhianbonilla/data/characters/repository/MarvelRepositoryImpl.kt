package com.cristhianbonilla.data.characters.repository

import com.cristhianbonilla.data.characters.data_source.MarvelDataSource
import com.cristhianbonilla.data.characters.entity.detail.toModel
import com.cristhianbonilla.data.characters.entity.list.toModel
import com.cristhianbonilla.domain.characters.model.detail.CharacterDetailResponseModel
import com.cristhianbonilla.domain.characters.model.list.CharacterResponseModel
import com.cristhianbonilla.domain.characters.repository.MarvelRepository
import com.cristhianbonilla.support.config.GenericErrorMapper
import com.cristhianbonilla.support.config.ResultDomain
import com.cristhianbonilla.support.config.baseResponseErrorHandler
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val dataSource: MarvelDataSource) :
    MarvelRepository {
    override suspend fun getCharacterList(): ResultDomain<CharacterResponseModel> =
        baseResponseErrorHandler(
            GenericErrorMapper,
            dataSource.getCharacterList()
        ) { ResultDomain.Success(it.toModel()) }

    override suspend fun getCharacterById(characterId: String): ResultDomain<CharacterDetailResponseModel> =
        baseResponseErrorHandler(
            GenericErrorMapper,
            dataSource.getCharacterById(characterId)
        ) { ResultDomain.Success(it.toModel()) }
}
