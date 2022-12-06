package com.cristhianbonilla.appAI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristhianbonilla.featuregpt3.Gpt3Activity
import com.cristhianbonilla.marvel.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val background = object : Thread() {
            override fun run() {
                super.run()

                try {
                    sleep(5000)
                    goToOtherActivity()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    private fun goToOtherActivity() {
        startActivity(Intent(this, Gpt3Activity::class.java))
        finish()
    }
}
