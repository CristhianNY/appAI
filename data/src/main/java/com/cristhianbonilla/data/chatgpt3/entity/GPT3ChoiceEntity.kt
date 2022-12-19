package com.cristhianbonilla.data.chatgpt3.entity

import com.cristhianbonilla.domain.gpt3.model.GPT3ChoiceModel
import com.google.gson.annotations.SerializedName

data class GPT3ChoiceEntity(
    @SerializedName("finish_reason")
    val finishReason: String?,
    @SerializedName("index")
    val index: Int,
    @SerializedName("logprobs")
    val logprobs: Any?,
    @SerializedName("text")
    val text: String?
)

fun GPT3ChoiceEntity.toModel(): GPT3ChoiceModel = GPT3ChoiceModel(
    finishReason.orEmpty(),
    index,
    logprobs,
    text.orEmpty()
)
