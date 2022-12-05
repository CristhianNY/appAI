package com.cristhianbonilla.featuregpt3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristhianbonilla.featuregpt3.databinding.ActivityGpt3Binding
import com.cristhianbonilla.support.config.activityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Gpt3Activity : AppCompatActivity() {
    private val binding by activityBinding<ActivityGpt3Binding>(R.layout.activity_gpt3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            setContentView(root)
        }
    }
}
