package com.cristhianbonilla.domain.characters.model.list

data class CharacterListResultModel(
    val characterEntities: List<CharacterModel>,
    val total: String
)
