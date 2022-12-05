package com.cristhianbonilla.domain.characters.model.list

data class CharacterModel(
    val id: String,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnail: CharacterThumbnailModel,
    val urls: List<CharacterUrlModel>
)
