package com.cristhianbonilla.domain.characters.usecase

import com.cristhianbonilla.domain.characters.model.detail.CharacterDetailResponseModel
import com.cristhianbonilla.domain.characters.repository.MarvelRepository
import com.cristhianbonilla.support.config.ResultDomain
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetCharacterByIdUseCaseImpl @Inject constructor(private val repository: MarvelRepository) :
    GetCharacterByIdUseCase {
    override suspend fun invoke(
        params: GetCharacterByIdUseCase.Params,
        context: CoroutineContext
    ): ResultDomain<CharacterDetailResponseModel> {
        return repository.getCharacterById(params.characterId)
    }
}
