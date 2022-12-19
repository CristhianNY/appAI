package com.cristhianbonilla.data.chatgpt3.entity

import com.cristhianbonilla.domain.gpt3.model.GPT3ResponseModel
import com.google.gson.annotations.SerializedName

data class GPT3ResponseEntity(
    @SerializedName("choices")
    val gpt3ChoiceEntities: List<GPT3ChoiceEntity>,
    @SerializedName("created")
    val created: Int,
    @SerializedName("id")
    val id: String?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("object")
    val objectX: String?,
    @SerializedName("usage")
    val gPT3UsageEntity: GPT3UsageEntity
)

fun GPT3ResponseEntity.toModel(): GPT3ResponseModel = GPT3ResponseModel(
    gpt3ChoiceEntities.map { it.toModel() },
    created,
    id.orEmpty(),
    model.orEmpty(),
    objectX.orEmpty(),
    gPT3UsageEntity.toModel()
)
