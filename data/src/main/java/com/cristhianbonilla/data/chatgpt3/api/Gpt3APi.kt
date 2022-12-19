package com.cristhianbonilla.data.chatgpt3.api

import com.cristhianbonilla.data.chatgpt3.entity.GPT3ResponseEntity
import com.cristhianbonilla.data.chatgpt3.entity.request.GPT3RequestEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Gpt3APi {

    @POST("v1/completions")
    suspend fun callCompletionEndpoint(@Body request: GPT3RequestEntity): Response<GPT3ResponseEntity>
}
