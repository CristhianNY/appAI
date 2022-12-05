package com.cristhianbonilla.domain.characters.usecase

import com.cristhianbonilla.domain.characters.model.list.CharacterResponseModel
import com.cristhianbonilla.domain.characters.repository.MarvelRepository
import com.cristhianbonilla.support.config.ResultDomain
import com.cristhianbonilla.support.config.UseCase
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetCharactersListUseCaseImpl @Inject constructor(private val marvelRepository: MarvelRepository) :
    GetCharactersListUseCase {
    override suspend fun invoke(
        params: UseCase.None,
        context: CoroutineContext
    ): ResultDomain<CharacterResponseModel> {
        return marvelRepository.getCharacterList()
    }
}
