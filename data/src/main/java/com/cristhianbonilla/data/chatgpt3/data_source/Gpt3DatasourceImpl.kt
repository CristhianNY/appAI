package com.cristhianbonilla.data.chatgpt3.data_source

import com.cristhianbonilla.data.chatgpt3.api.Gpt3APi
import com.cristhianbonilla.data.chatgpt3.entity.request.GPT3RequestEntity
import com.cristhianbonilla.support.config.BaseDataSource
import javax.inject.Inject

class Gpt3DatasourceImpl @Inject constructor(private val gpt3Api: Gpt3APi) :
    BaseDataSource(),
    Gpt3Datasource {

    override suspend fun getAnswerFromGpt3(request: GPT3RequestEntity) = getResult {
        executeNetworkAction {
            gpt3Api.callCompletionEndpoint(request)
        }
    }
}
