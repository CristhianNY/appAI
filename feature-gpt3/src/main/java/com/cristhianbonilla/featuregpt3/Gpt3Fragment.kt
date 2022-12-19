package com.cristhianbonilla.featuregpt3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cristhianbonilla.featuregpt3.databinding.Gpt3FragmentBinding
import com.cristhianbonilla.featuregpt3.state.GPT3State
import com.cristhianbonilla.featuregpt3.state.GPT3State.ShowGpt3ResponseState
import com.cristhianbonilla.support.config.fragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Gpt3Fragment : Fragment() {

    private val binding by fragmentBinding<Gpt3FragmentBinding>(R.layout.gpt3_fragment)

    private val viewModel by viewModels<Gpt3ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.run {
        viewModel = this@Gpt3Fragment.viewModel
        lifecycleOwner = this@Gpt3Fragment.viewLifecycleOwner
        iniView()
        observeViewModelEvents()
        root
    }

    private fun iniView() {
        binding.sendButton.setOnClickListener {
            viewModel.sendPetitionToChatGpt3(binding.message.text.toString())
        }
    }

    private fun observeViewModelEvents() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            showLoading(false)
            when (state) {
                GPT3State.Loading -> showLoading(true)
                is ShowGpt3ResponseState -> Toast.makeText(
                    requireContext(),
                    state.gpT3ResponseModel?.GPT3ChoiceEntities.orEmpty().first().text,
                    Toast.LENGTH_LONG
                ).show()
                GPT3State.Gpt3Error -> Toast.makeText(
                    requireContext(),
                    "Error",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showLoading(isVisible: Boolean) {
        if (isVisible) binding.progressCircular.visibility =
            View.VISIBLE else binding.progressCircular.visibility = View.GONE
    }
}
