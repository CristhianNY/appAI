package com.cristhianbonilla.data.chatgpt3.repository

import com.cristhianbonilla.data.chatgpt3.data_source.Gpt3Datasource
import com.cristhianbonilla.data.chatgpt3.entity.request.toEntity
import com.cristhianbonilla.data.chatgpt3.entity.toModel
import com.cristhianbonilla.domain.gpt3.model.request.Gpt3RequestModel
import com.cristhianbonilla.domain.gpt3.repository.Gpt3Repository
import com.cristhianbonilla.support.config.GenericErrorMapper
import com.cristhianbonilla.support.config.ResultDomain
import com.cristhianbonilla.support.config.baseResponseErrorHandler
import javax.inject.Inject

class Gpt3RepositoryImpl @Inject constructor(private val dataSource: Gpt3Datasource) :
    Gpt3Repository {
    override suspend fun getAnswerFromGpt3(requestModel: Gpt3RequestModel) =
        baseResponseErrorHandler(
            GenericErrorMapper,
            dataSource.getAnswerFromGpt3(requestModel.toEntity())
        ) { ResultDomain.Success(it.toModel()) }
}
