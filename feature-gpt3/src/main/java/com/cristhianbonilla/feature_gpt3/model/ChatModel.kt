package com.cristhianbonilla.feature_gpt3.model

data class ChatModel(val id: String, val message: String, val typeMessage: ChatType)

enum class ChatType {
    SENDER_MESSAGE, RECEIVER_MESSAGE
}
