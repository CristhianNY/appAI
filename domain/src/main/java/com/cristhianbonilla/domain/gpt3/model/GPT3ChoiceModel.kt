package com.cristhianbonilla.domain.gpt3.model

data class GPT3ChoiceModel(
    val finishReason: String,
    val index: Int,
    val logprobs: Any?,
    val text: String
)
