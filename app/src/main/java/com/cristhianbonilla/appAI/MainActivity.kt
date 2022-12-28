package com.cristhianbonilla.appai

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristhianbonilla.feature_home.HomeActivity

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
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
