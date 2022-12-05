package com.cristhianbonilla.domain.characters.usecase

import com.cristhianbonilla.domain.characters.model.list.CharacterResponseModel
import com.cristhianbonilla.support.config.UseCase

interface GetCharactersListUseCase : UseCase<UseCase.None, CharacterResponseModel>
