package com.cristhianbonilla.domain.gpt3.repository

import com.cristhianbonilla.domain.gpt3.model.GPT3ResponseModel
import com.cristhianbonilla.domain.gpt3.model.request.Gpt3RequestModel
import com.cristhianbonilla.support.config.ResultDomain

interface Gpt3Repository {
    suspend fun getAnswerFromGpt3(gpt3RequestModel: Gpt3RequestModel): ResultDomain<GPT3ResponseModel>
}
