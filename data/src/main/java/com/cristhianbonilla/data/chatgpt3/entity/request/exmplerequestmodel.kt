package com.cristhianbonilla.data.chatgpt3.entity.request

import com.google.gson.annotations.SerializedName

data class exmplerequestmodel(
    @SerializedName("max_tokens")
    val maxTokens: Int,
    @SerializedName("model")
    val model: String,
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("temperature")
    val temperature: Int
)
