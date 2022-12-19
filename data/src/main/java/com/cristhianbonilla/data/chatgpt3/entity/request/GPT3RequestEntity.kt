package com.cristhianbonilla.data.chatgpt3.entity.request

import com.cristhianbonilla.domain.gpt3.model.request.Gpt3RequestModel
import com.google.gson.annotations.SerializedName

data class GPT3RequestEntity(
    @SerializedName("max_tokens")
    val maxTokens: Int,
    @SerializedName("model")
    val model: String,
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("temperature")
    val temperature: Int
)

fun Gpt3RequestModel.toEntity() = GPT3RequestEntity(maxTokens, model, prompt, temperature)
