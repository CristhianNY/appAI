package com.cristhianbonilla.domain.gpt3.model.request

data class Gpt3RequestModel(
    val maxTokens: Int,
    val model: String,
    val prompt: String,
    val temperature: Int
)
