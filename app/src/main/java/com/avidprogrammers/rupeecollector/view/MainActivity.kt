package com.avidprogrammers.rupeecollector.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.avidprogrammers.rupeecollector.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)


        Handler(Looper.getMainLooper()).postDelayed(Runnable { /* Create an Intent that will start the MainActivity. */
            val mainIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 1000)
    }
}