package com.cristhianbonilla.feature_marvel_characters.character_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.cristhianbonilla.domain.characters.model.detail.CharacterModel
import com.cristhianbonilla.feature_marvel_characters.R
import com.cristhianbonilla.feature_marvel_characters.databinding.FragmentCharacterDetailBinding
import com.cristhianbonilla.support.config.fragmentBinding
import com.cristhianbonilla.support.config.setUpLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private val viewModel by viewModels<CharacterDetailViewModel>()
    private val binding by fragmentBinding<FragmentCharacterDetailBinding>(R.layout.fragment_character_detail)

    private val args: CharacterDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModeEvents()
        viewModel.getCharacterDetailById(args.characterId.orEmpty())

        binding.container.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                binding.scrollDownAnimation.visibility = View.GONE
            }
            true
        }
    }

    private fun observeViewModeEvents() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            this.setUpLoader(
                binding.container,
                binding.loader,
                false
            )
            when (state) {
                is CharacterDetailState.Loading -> this.setUpLoader(
                    binding.container,
                    binding.loader,
                    true
                )
                is CharacterDetailState.ShowCharacterDetail -> loadCharacterInformation(state.characterList)

                is CharacterDetailState.Error -> handleError()
            }
        }
    }

    private fun loadCharacterInformation(characterList: List<CharacterModel>?) {
        val imageSelected = characterList?.first()?.thumbnail?.let {
            it.path.plus(".").plus(it.extension)
        }
        Glide.with(this).load(imageSelected).centerCrop().into(binding.ivCharacterSelected)
        binding.tvTitle.text = characterList?.first()?.name.orEmpty()
        binding.tvDescription.text = characterList?.first()?.description.orEmpty()
    }

    private fun handleError() {
        Toast.makeText(
            requireContext(),
            requireContext().getString(R.string.error_getting_marvel_characters),
            Toast.LENGTH_LONG
        ).show()
    }
}
