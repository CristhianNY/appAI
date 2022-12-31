package com.cristhianbonilla.create_new_sermon.create_sermon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristhianbonilla.domain.gpt3.model.request.Gpt3RequestModel
import com.cristhianbonilla.domain.gpt3.usecase.RequestCompletionFromGpt3UseCase
import com.cristhianbonilla.support.config.ResultDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateSermonViewModel @Inject constructor(
    private val requestCompletionFromGpt3UseCase: RequestCompletionFromGpt3UseCase,
) : ViewModel() {

    private val _state = MutableLiveData<CreateSermonState>()
    val state: LiveData<CreateSermonState> = _state

    fun createSermon(text: String) {
        setState(CreateSermonState.Loading)
        viewModelScope.launch {
            when (
                val response = requestCompletionFromGpt3UseCase(
                    RequestCompletionFromGpt3UseCase.Params(
                        Gpt3RequestModel(
                            frequencyPenalty = 0.0,
                            maxTokens = 100,
                            model = "text-davinci-003",
                            prompt = text,
                            presencePenalty = 0.6,
                            stop = listOf("Human", "AI"),
                            temperature = 0.9,
                            topP = 1,
                            user = "user1"
                        )
                    )
                )
            ) {
                is ResultDomain.Success -> {
                    setState(CreateSermonState.ShowGpt3ResponseState(response.data))
                }

                is ResultDomain.Error -> setState(CreateSermonState.Gpt3Error)
            }
        }
    }

    private fun setState(state: CreateSermonState) {
        _state.value = state
    }
}
