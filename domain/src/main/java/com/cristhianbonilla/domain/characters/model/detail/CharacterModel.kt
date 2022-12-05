package com.cristhianbonilla.domain.characters.model.detail

data class CharacterModel(
    val description: String,
    val id: String,
    val modified: String,
    val name: String,
    val thumbnail: CharacterThumbnailModel,
)
