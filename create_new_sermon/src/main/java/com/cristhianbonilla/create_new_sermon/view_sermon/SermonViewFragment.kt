package com.cristhianbonilla.create_new_sermon.view_sermon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cristhianbonilla.createNewSermon.R
import com.cristhianbonilla.createNewSermon.databinding.SermonViewFragmentBinding
import com.cristhianbonilla.support.config.fragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SermonViewFragment : Fragment() {
    private val binding by fragmentBinding<SermonViewFragmentBinding>(R.layout.sermon_view_fragment)

    private val args: SermonViewFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.run {
        lifecycleOwner = this@SermonViewFragment.viewLifecycleOwner
        setSermonText()
        root
    }

    private fun setSermonText() {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 1000
        binding.sermonText.text = args.sermon.orEmpty()
        for (i in 0 until binding.sermonText.text.length) {
            binding.sermonText.text = binding.sermonText.text.subSequence(0, i + 1)
            binding.sermonText.startAnimation(animation)
            try {
                Thread.sleep(200)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}
