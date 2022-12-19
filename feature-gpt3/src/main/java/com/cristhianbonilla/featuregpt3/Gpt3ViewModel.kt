package com.cristhianbonilla.featuregpt3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristhianbonilla.domain.gpt3.model.request.Gpt3RequestModel
import com.cristhianbonilla.domain.gpt3.usecase.RequestCompletionFromGpt3UseCase
import com.cristhianbonilla.featuregpt3.state.GPT3State
import com.cristhianbonilla.support.config.ResultDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Gpt3ViewModel @Inject constructor(
    private val requestCompletionFromGpt3UseCase: RequestCompletionFromGpt3UseCase,
) : ViewModel() {

    private val _state = MutableLiveData<GPT3State>()
    val state: LiveData<GPT3State> = _state

    fun sendPetitionToChatGpt3(text: String) {
        setState(GPT3State.Loading)
        viewModelScope.launch {
            when (
                val response = requestCompletionFromGpt3UseCase(
                    RequestCompletionFromGpt3UseCase.Params(
                        Gpt3RequestModel(7, "text-davinci-003", text, 1)
                    )
                )
            ) {
                is ResultDomain.Success -> setState(
                    GPT3State.ShowGpt3ResponseState(response.data)
                )

                is ResultDomain.Error -> setState(GPT3State.Gpt3Error)
            }
        }
    }

    private fun setState(state: GPT3State) {
        _state.value = state
    }
}
