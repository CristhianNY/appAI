package com.cristhianbonilla.data.chatgpt3.entity.request

import com.google.gson.annotations.SerializedName

data class example(
    @SerializedName("frequency_penalty")
    val frequencyPenalty: Double,
    @SerializedName("max_tokens")
    val maxTokens: Int,
    @SerializedName("model")
    val model: String,
    @SerializedName("presence_penalty")
    val presencePenalty: Double,
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("stop")
    val stop: List<String>,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("top_p")
    val topP: Int
)
