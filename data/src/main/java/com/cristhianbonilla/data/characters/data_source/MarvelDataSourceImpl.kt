package com.cristhianbonilla.data.characters.data_source

import com.cristhianbonilla.data.characters.api.MarvelApi
import com.cristhianbonilla.support.config.BaseDataSource
import javax.inject.Inject

class MarvelDataSourceImpl @Inject constructor(private val marvelApi: MarvelApi) :
    BaseDataSource(),
    MarvelDataSource {
    override suspend fun getCharacterList() = getResult {
        executeNetworkAction {
            marvelApi.getCharacterList()
        }
    }

    override suspend fun getCharacterById(characterId: String) = getResult {
        executeNetworkAction {
            marvelApi.getCharacterById(characterId)
        }
    }
}
