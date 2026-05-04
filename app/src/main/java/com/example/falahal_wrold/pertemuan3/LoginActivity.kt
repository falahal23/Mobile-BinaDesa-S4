package com.example.falahal_wrold.pertemuan3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.falahal_wrold.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val user = binding.etUsername.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            if (user.isEmpty()) {
                binding.etUsername.error = "Username tidak boleh kosong"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }

            if (pass.isEmpty()) {
                binding.etPassword.error = "Password tidak boleh kosong"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            // 🔥 Log sukses login
            Log.d("LOGIN_STATUS", "User $user berhasil login")

            // 🔥 Intent + kirim data
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("username", user)
            // ⚠️ password sebaiknya tidak dikirim (security)
            // intent.putExtra("password", pass)

            startActivity(intent)
            finish() // biar tidak balik ke login lagi
        }
    }
}