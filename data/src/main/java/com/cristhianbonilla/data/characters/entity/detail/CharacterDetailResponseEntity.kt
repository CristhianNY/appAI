package com.cristhianbonilla.data.characters.entity.detail

import com.cristhianbonilla.domain.characters.model.detail.CharacterDetailResponseModel
import com.google.gson.annotations.SerializedName

data class CharacterDetailResponseEntity(
    @SerializedName("data")
    val characterDetail: CharacterDetailEntity
)

fun CharacterDetailResponseEntity.toModel() = CharacterDetailResponseModel(characterDetail.toModel())
