package com.cristhianbonilla.feature_home.ui.sermon_transcription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cristhianbonilla.featureHome.databinding.FragmentSermonTranscriptionBinding

class SermonTranscriptionFragment : Fragment() {

    private var _binding: FragmentSermonTranscriptionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sermonTranscriptionViewModel =
            ViewModelProvider(this).get(SermonTranscriptionViewModel::class.java)

        _binding = FragmentSermonTranscriptionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFragmentTranscription
        sermonTranscriptionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
