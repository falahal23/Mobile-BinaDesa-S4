package com.example.falahal_wrold

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // tampilkan layout splash
        setContentView(R.layout.activity_splash_screen)

        // delay 2 detik biar keliatan
        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()

        }, 2000)
    }
}