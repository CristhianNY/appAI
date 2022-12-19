package com.cristhianbonilla.domain.gpt3.usecase

import com.cristhianbonilla.domain.gpt3.model.GPT3ResponseModel
import com.cristhianbonilla.domain.gpt3.model.request.Gpt3RequestModel
import com.cristhianbonilla.domain.gpt3.usecase.RequestCompletionFromGpt3UseCase.Params
import com.cristhianbonilla.support.config.UseCase

interface RequestCompletionFromGpt3UseCase : UseCase<Params, GPT3ResponseModel> {
    data class Params(val request: Gpt3RequestModel)
}
