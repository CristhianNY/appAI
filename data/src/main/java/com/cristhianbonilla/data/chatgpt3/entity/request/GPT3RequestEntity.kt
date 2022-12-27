package com.cristhianbonilla.data.chatgpt3.entity.request

import com.cristhianbonilla.domain.gpt3.model.request.Gpt3RequestModel
import com.google.gson.annotations.SerializedName

data class GPT3RequestEntity(
    @SerializedName("frequency_penalty")
    val frequencyPenalty: Double,
    @SerializedName("max_tokens")
    val maxTokens: Int,
    @SerializedName("model")
    val model: String,
    @SerializedName("presence_penalty")
    val presencePenalty: Double,
    @SerializedName("prompt")
    val prompt: String? = null,
    @SerializedName("stop")
    val stop: List<String>,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("top_p")
    val topP: Int,
    @SerializedName("user")
    val user: String
)

fun Gpt3RequestModel.toEntity() = GPT3RequestEntity(
    frequencyPenalty,
    maxTokens,
    model,
    presencePenalty,
    prompt,
    stop,
    temperature,
    topP,
    user
)
