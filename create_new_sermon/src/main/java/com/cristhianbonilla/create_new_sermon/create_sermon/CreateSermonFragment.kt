package com.cristhianbonilla.create_new_sermon.create_sermon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cristhianbonilla.createNewSermon.R
import com.cristhianbonilla.createNewSermon.databinding.CreateSermonFragmentBinding
import com.cristhianbonilla.resources.R.drawable
import com.cristhianbonilla.support.config.fragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateSermonFragment : Fragment() {
    private val binding by fragmentBinding<CreateSermonFragmentBinding>(R.layout.create_sermon_fragment)

    private val viewModel by viewModels<CreateSermonViewModel>()

    private var numberOfIdeas = "3"
    private var sermonCategory = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.run {
        lifecycleOwner = this@CreateSermonFragment.viewLifecycleOwner
        setUpSpinner()
        observeViewModelEvents()
        setUpView()
        root
    }

    private fun setUpView() {
        binding.btnGenerateSermon.setOnClickListener {
            if (binding.versesInclude.text?.isNotEmpty() == true) {
                viewModel.createSermon("Escribame un $sermonCategory  que tenga titulo, ${binding.objectives.text}, introducción $numberOfIdeas ideas principales con sus ideas secundarias , tambien mensiona citas de los siguientes authores ${binding.authors.text}, tambien que contenga los siguientes versiculos ${binding.versesInclude.text} conclusion y llamado ")
            } else {
                viewModel.createSermon("Escribame un $sermonCategory  que tenga titulo, ${binding.objectives.text}, introducción $numberOfIdeas ideas principales con sus ideas secundarias , tambien mensiona citas de los siguientes authores ${binding.authors.text} conclusion y llamado   ")
            }
        }
    }

    private fun observeViewModelEvents() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                CreateSermonState.Loading -> showLoading(true)
                is CreateSermonState.ShowGpt3ResponseState -> navigateToShowSermon(state.gpT3ResponseModel?.GPT3ChoiceEntities?.first()?.text.orEmpty())
                CreateSermonState.Gpt3Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Error Creando el sermon",
                        Toast.LENGTH_LONG
                    ).show()
                    showLoading(false)
                }
            }
        }
    }

    private fun navigateToShowSermon(sermon: String) {
        findNavController().navigate(
            CreateSermonFragmentDirections.actionCreateSermonFragmentToSermonViewFragment(
                sermon
            )
        )
    }

    private fun setUpSpinner() {
        val ideasSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )

        val categorySpinner = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items_category,
            android.R.layout.simple_spinner_item
        )

        ideasSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        categorySpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = categorySpinner
        binding.numberOfIdeas.adapter = ideasSpinnerAdapter
        binding.numberOfIdeas.background =
            ContextCompat.getDrawable(requireContext(), drawable.spinner_background)
        binding.categorySpinner.background =
            ContextCompat.getDrawable(requireContext(), drawable.spinner_background)

        binding.numberOfIdeas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                numberOfIdeas = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    sermonCategory = parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
    }

    private fun showLoading(isVisible: Boolean) {
        if (isVisible) {
            binding.progressCircular.visibility =
                View.VISIBLE
            binding.constraintLayout.visibility = View.GONE
        } else {
            binding.progressCircular.visibility = View.GONE
            binding.constraintLayout.visibility = View.VISIBLE
        }
    }
}
