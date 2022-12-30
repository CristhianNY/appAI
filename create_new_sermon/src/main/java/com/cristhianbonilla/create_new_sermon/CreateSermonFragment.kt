package com.cristhianbonilla.create_new_sermon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cristhianbonilla.createNewSermon.R
import com.cristhianbonilla.createNewSermon.databinding.CreateSermonFragmentBinding
import com.cristhianbonilla.resources.R.drawable
import com.cristhianbonilla.support.config.fragmentBinding

class CreateSermonFragment : Fragment() {
    private val binding by fragmentBinding<CreateSermonFragmentBinding>(R.layout.create_sermon_fragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.run {
        lifecycleOwner = this@CreateSermonFragment.viewLifecycleOwner
        setUpSpinner()
        root
    }

    private fun setUpSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.numberOfIdeas.adapter = adapter
        binding.numberOfIdeas.background = ContextCompat.getDrawable(requireContext(), drawable.spinner_background)

        binding.numberOfIdeas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
}
