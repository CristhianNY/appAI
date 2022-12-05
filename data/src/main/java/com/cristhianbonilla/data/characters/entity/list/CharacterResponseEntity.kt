package com.cristhianbonilla.data.characters.entity.list

import com.cristhianbonilla.domain.characters.model.list.CharacterResponseModel
import com.google.gson.annotations.SerializedName

data class CharacterResponseEntity(
    @SerializedName("data")
    val data: CharacterListResultEntity,
)

fun CharacterResponseEntity.toModel(): CharacterResponseModel = CharacterResponseModel(
    data.toModel()
)
