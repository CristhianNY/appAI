package com.cristhianbonilla.domain.characters.usecase

import com.cristhianbonilla.domain.characters.model.detail.CharacterDetailResponseModel
import com.cristhianbonilla.domain.characters.usecase.GetCharacterByIdUseCase.Params
import com.cristhianbonilla.support.config.UseCase

interface GetCharacterByIdUseCase : UseCase<Params, CharacterDetailResponseModel> {
    data class Params(val characterId: String)
}
