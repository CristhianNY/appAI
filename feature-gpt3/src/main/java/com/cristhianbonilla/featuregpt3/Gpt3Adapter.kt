package com.cristhianbonilla.featuregpt3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cristhianbonilla.featuregpt3.databinding.ItemChatReceiverBinding
import com.cristhianbonilla.featuregpt3.databinding.ItemChatSendBinding
import com.cristhianbonilla.featuregpt3.model.ChatModel
import com.cristhianbonilla.featuregpt3.model.ChatType

private const val CHAT_SEND = 0
private const val CHAT_RECEIVER = 1

class Gpt3Adapter(private val action: (characterModel: ChatModel) -> Unit) :
    ListAdapter<ChatModel, RecyclerView.ViewHolder>(ChatModelDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val bindingChatSend =
            ItemChatSendBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val bindingChatReceiver =
            ItemChatReceiverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when (viewType) {
            CHAT_SEND -> ChatSendViewHolder(bindingChatSend)
            CHAT_RECEIVER -> ChatReceiverViewHolder(bindingChatReceiver)
            else -> ChatSendViewHolder(bindingChatSend)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            CHAT_SEND -> (holder as ChatSendViewHolder).bind(getItem(position))
            CHAT_RECEIVER -> (holder as ChatReceiverViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).typeMessage) {
            ChatType.RECEIVER_MESSAGE -> 0
            ChatType.SENDER_MESSAGE -> 1
        }
    }

    inner class ChatSendViewHolder(val binding: ItemChatSendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gpt3RequestModel: ChatModel) {
            binding.tvMessage.text = gpt3RequestModel.message
        }
    }

    inner class ChatReceiverViewHolder(val binding: ItemChatReceiverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gpt3RequestModel: ChatModel) {
            binding.tvMessage.text = gpt3RequestModel.message
        }
    }
}

object ChatModelDiff : DiffUtil.ItemCallback<ChatModel>() {
    override fun areContentsTheSame(
        oldItem: ChatModel,
        newItem: ChatModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(
        oldItem: ChatModel,
        newItem: ChatModel
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
