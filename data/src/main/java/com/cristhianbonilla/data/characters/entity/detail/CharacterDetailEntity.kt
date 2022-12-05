package com.cristhianbonilla.data.characters.entity.detail

import com.cristhianbonilla.domain.characters.model.detail.CharacterDetailModel
import com.google.gson.annotations.SerializedName

data class CharacterDetailEntity(
    @SerializedName("results")
    val results: List<CharacterEntity>,
)

fun CharacterDetailEntity.toModel() = CharacterDetailModel(results.map { it.toModel() })
