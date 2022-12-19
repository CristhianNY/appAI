package com.cristhianbonilla.domain.gpt3.model

data class GPT3UsageModel(
    val completionTokens: Int,
    val promptTokens: Int,
    val totalTokens: Int
)
