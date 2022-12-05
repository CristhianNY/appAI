package com.cristhianbonilla.feature_marvel_characters.character_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cristhianbonilla.domain.characters.model.list.CharacterModel
import com.cristhianbonilla.feature_marvel_characters.databinding.CharacterItemViewBinding

private const val DOT = "."

class CharacterListAdapter(private val action: (characterModel: CharacterModel) -> Unit) :
    ListAdapter<CharacterModel, RecyclerView.ViewHolder>(CharacterDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CharacterItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CharacterViewHolder).bind(getItem(position) as CharacterModel)
    }

    inner class CharacterViewHolder(val binding: CharacterItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterModel) {
            val imageUrl: String = character.thumbnail.let { it.path.plus(DOT).plus(it.extension) }
            binding.ivCharacter.apply {
                Glide.with(context).load(imageUrl).apply(RequestOptions().override(600, 600))
                    .centerCrop().into(binding.ivCharacter)
            }

            binding.ivCharacter.setOnClickListener {
                action.invoke(character)
            }
        }
    }
}

object CharacterDiff : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areContentsTheSame(
        oldItem: CharacterModel,
        newItem: CharacterModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(
        oldItem: CharacterModel,
        newItem: CharacterModel
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
