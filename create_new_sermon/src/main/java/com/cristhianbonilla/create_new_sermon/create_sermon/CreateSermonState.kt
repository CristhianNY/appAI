package com.cristhianbonilla.create_new_sermon.create_sermon

import com.cristhianbonilla.domain.gpt3.model.GPT3ResponseModel

sealed class CreateSermonState {
    data class ShowGpt3ResponseState(val gpT3ResponseModel: GPT3ResponseModel?) : CreateSermonState()
    object Gpt3Error : CreateSermonState()
    object Loading : CreateSermonState()
}
