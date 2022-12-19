package com.cristhianbonilla.data.chatgpt3.entity

import com.cristhianbonilla.domain.gpt3.model.GPT3UsageModel
import com.google.gson.annotations.SerializedName

data class GPT3UsageEntity(
    @SerializedName("completion_tokens")
    val completionTokens: Int,
    @SerializedName("prompt_tokens")
    val promptTokens: Int,
    @SerializedName("total_tokens")
    val totalTokens: Int
)

fun GPT3UsageEntity.toModel(): GPT3UsageModel = GPT3UsageModel(
    completionTokens,
    promptTokens,
    totalTokens
)
