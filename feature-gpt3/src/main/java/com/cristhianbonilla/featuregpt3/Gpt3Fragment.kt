package com.cristhianbonilla.featuregpt3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cristhianbonilla.featuregpt3.databinding.Gpt3FragmentBinding
import com.cristhianbonilla.featuregpt3.model.ChatModel
import com.cristhianbonilla.featuregpt3.state.GPT3State
import com.cristhianbonilla.featuregpt3.state.GPT3State.ShowGpt3ResponseState
import com.cristhianbonilla.support.config.fragmentBinding
import dagger.hilt.android.AndroidEntryPoint

private const val CLEAN_CONVERSATION = "<|endoftext|>"

@AndroidEntryPoint
class Gpt3Fragment : Fragment() {

    private val binding by fragmentBinding<Gpt3FragmentBinding>(R.layout.gpt3_fragment)

    private val viewModel by viewModels<Gpt3ViewModel>()

    private val gpt3Adapter = Gpt3Adapter { chatModel ->
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.run {
        viewModel = this@Gpt3Fragment.viewModel
        lifecycleOwner = this@Gpt3Fragment.viewLifecycleOwner
        iniView()
        observeViewModelEvents()
        setUpRecyclerView()
        root
    }

    private fun iniView() {
        binding.sendButton.setOnClickListener {
            viewModel.sendPetitionToChatGpt3(binding.message.text.toString())
        }

        binding.cleanChat.setOnClickListener {
            viewModel.cleanConversation()
        }
    }

    private fun setUpRecyclerView() {
        binding.rvChat.apply {
            adapter = gpt3Adapter
        }
    }

    private fun observeViewModelEvents() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            showLoading(false)
            when (state) {
                GPT3State.Loading -> showLoading(true)
                is ShowGpt3ResponseState -> showLoading(false)
                GPT3State.Gpt3Error -> Toast.makeText(
                    requireContext(),
                    "Error",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val chatModelListObserver = Observer<ArrayList<ChatModel>> { chatModelList ->
            gpt3Adapter.submitList(chatModelList.reversed())
            gpt3Adapter.notifyDataSetChanged()
        }

        viewModel.chatModelList.observe(viewLifecycleOwner, chatModelListObserver)
    }

    private fun showLoading(isVisible: Boolean) {
        if (isVisible) {
            binding.progressCircular.visibility =
                View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }
}
