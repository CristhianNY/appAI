package com.cristhianbonilla.data.characters.entity.detail

import com.cristhianbonilla.domain.characters.model.detail.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharacterEntity(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("thumbnail")
    val thumbnail: CharacterThumbnailEntity,
)

fun CharacterEntity.toModel() = CharacterModel(description.orEmpty(), id.orEmpty(), modified.orEmpty(), name.orEmpty(), thumbnail.toModel())
