package com.cristhianbonilla.feature_gpt3.state

import com.cristhianbonilla.domain.gpt3.model.GPT3ResponseModel

sealed class GPT3State {
    data class ShowGpt3ResponseState(val gpT3ResponseModel: GPT3ResponseModel?) : GPT3State()
    object Gpt3Error : GPT3State()
    object Loading : GPT3State()
}
