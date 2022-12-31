package com.cristhianbonilla.create_new_sermon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristhianbonilla.createNewSermon.R
import com.cristhianbonilla.createNewSermon.databinding.ActivitySermonBinding
import com.cristhianbonilla.support.config.activityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SermonActivity : AppCompatActivity() {
    private val binding by activityBinding<ActivitySermonBinding>(R.layout.activity_sermon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            setContentView(root)
        }
    }
}
