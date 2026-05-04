package com.example.falahal_wrold

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.falahal_wrold.databinding.ActivityAuthBinding
import com.example.falahal_wrold.pertemuan6.MainActivityBinaDesa

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Padding system
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bar.left, bar.top, bar.right, bar.bottom)
            insets
        }

        val pref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // 🔥 Login Button
        binding.btnSignIn.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val pass  = binding.etPassword.text.toString().trim()

            if (email == "falahal@gmail.com" && pass == "12345678") {

                pref.edit().apply {
                    putBoolean("isLogin", true)
                    putString("email", email)
                    apply()
                }

                startActivity(Intent(this, MainActivityBinaDesa::class.java))
                finish()

            } else {
                showError()
            }
        }
    }

    // 🔥 Function biar rapi
    private fun showError() {
        AlertDialog.Builder(this)
            .setTitle("Login Gagal")
            .setMessage("Email atau password salah")
            .setPositiveButton("OK", null)
            .show()
    }
}