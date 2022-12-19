package com.cristhianbonilla.data.chatgpt3.data_source

import com.cristhianbonilla.data.chatgpt3.entity.GPT3ResponseEntity
import com.cristhianbonilla.data.chatgpt3.entity.request.GPT3RequestEntity
import com.cristhianbonilla.support.config.Result

interface Gpt3Datasource {
    suspend fun getAnswerFromGpt3(request: GPT3RequestEntity): Result<GPT3ResponseEntity>
}
