package com.cristhianbonilla.feature_marvel_characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristhianbonilla.feature_marvel_characters.databinding.ActivityMarvelBinding
import com.cristhianbonilla.support.config.activityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelActivity : AppCompatActivity() {
    private val binding by activityBinding<ActivityMarvelBinding>(R.layout.activity_marvel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            setContentView(root)
        }
    }
}
