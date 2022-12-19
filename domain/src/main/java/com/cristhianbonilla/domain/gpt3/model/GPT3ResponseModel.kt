package com.cristhianbonilla.domain.gpt3.model

data class GPT3ResponseModel(
    val GPT3ChoiceEntities: List<GPT3ChoiceModel>,
    val created: Int,
    val id: String,
    val model: String,
    val objectX: String,
    val GPT3UsageEntity: GPT3UsageModel
)
