package com.cristhianbonilla.domain.gpt3.usecase

import com.cristhianbonilla.domain.gpt3.model.GPT3ResponseModel
import com.cristhianbonilla.domain.gpt3.repository.Gpt3Repository
import com.cristhianbonilla.support.config.ResultDomain
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RequestCompletionFromGpt3UseCaseImpl @Inject constructor(private val repository: Gpt3Repository) :
    RequestCompletionFromGpt3UseCase {
    override suspend fun invoke(
        params: RequestCompletionFromGpt3UseCase.Params,
        context: CoroutineContext
    ): ResultDomain<GPT3ResponseModel> {
        return repository.getAnswerFromGpt3(params.request)
    }
}
