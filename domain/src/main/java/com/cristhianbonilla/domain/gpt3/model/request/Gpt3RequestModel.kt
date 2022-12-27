package com.cristhianbonilla.domain.gpt3.model.request

data class Gpt3RequestModel(
    val frequencyPenalty: Double,
    val maxTokens: Int,
    val model: String,
    val presencePenalty: Double,
    val prompt: String? = null,
    val stop: List<String>,
    val temperature: Double,
    val topP: Int,
    val user: String
)
