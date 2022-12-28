package com.cristhianbonilla.feature_gpt3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristhianbonilla.domain.gpt3.model.request.Gpt3RequestModel
import com.cristhianbonilla.domain.gpt3.usecase.RequestCompletionFromGpt3UseCase
import com.cristhianbonilla.feature_gpt3.model.ChatModel
import com.cristhianbonilla.feature_gpt3.model.ChatType
import com.cristhianbonilla.feature_gpt3.state.GPT3State
import com.cristhianbonilla.support.config.ResultDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class Gpt3ViewModel @Inject constructor(
    private val requestCompletionFromGpt3UseCase: RequestCompletionFromGpt3UseCase,
) : ViewModel() {

    private val _state = MutableLiveData<GPT3State>()
    val state: LiveData<GPT3State> = _state

    private val _chatModelList = MutableLiveData<ArrayList<ChatModel>>(ArrayList())
    val chatModelList: LiveData<ArrayList<ChatModel>> = _chatModelList

    fun sendPetitionToChatGpt3(text: String) {
        insertMessageToList(ChatType.SENDER_MESSAGE, text, "")
        setState(GPT3State.Loading)
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
                    insertMessageToList(
                        ChatType.RECEIVER_MESSAGE,
                        response.data?.GPT3ChoiceEntities?.first()?.text.orEmpty(),
                        response.data?.id.orEmpty()
                    )
                    setState(GPT3State.ShowGpt3ResponseState(response.data))
                }

                is ResultDomain.Error -> setState(GPT3State.Gpt3Error)
            }
        }
    }

    fun cleanConversation() {
        setState(GPT3State.Loading)
        viewModelScope.launch {
            when (
                val response = requestCompletionFromGpt3UseCase(
                    RequestCompletionFromGpt3UseCase.Params(
                        Gpt3RequestModel(
                            frequencyPenalty = 0.0,
                            maxTokens = 100,
                            model = "text-davinci-003",
                            presencePenalty = 0.6,
                            stop = listOf("Human", "AI"),
                            temperature = 0.9,
                            topP = 1,
                            user = "user3"
                        )
                    )
                )
            ) {
                is ResultDomain.Success -> {
                    insertMessageToList(
                        ChatType.RECEIVER_MESSAGE,
                        response.data?.GPT3ChoiceEntities?.first()?.text.orEmpty(),
                        response.data?.id.orEmpty()
                    )
                    setState(GPT3State.ShowGpt3ResponseState(response.data))
                }

                is ResultDomain.Error -> setState(GPT3State.Gpt3Error)
            }
        }
    }

    private fun insertMessageToList(chatType: ChatType, message: String?, id: String) {
        val chatModel = when (chatType) {
            ChatType.SENDER_MESSAGE -> ChatModel(
                getUniqueId(),
                message.orEmpty(),
                chatType

            )
            ChatType.RECEIVER_MESSAGE -> ChatModel(
                id,
                message.orEmpty(),
                chatType
            )
        }
        _chatModelList.postValue(_chatModelList.value?.apply { add(chatModel) })
    }

    private fun getUniqueId(): String {
        var randomId = UUID.randomUUID().toString()
        var foundId = false
        while (!foundId) {
            for (obj in _chatModelList.value.orEmpty()) {
                if (obj.id == randomId) {
                    randomId = UUID.randomUUID().toString()
                    break
                }
            }
            foundId = true
        }

        return randomId
    }

    private fun setState(state: GPT3State) {
        _state.value = state
    }
}
