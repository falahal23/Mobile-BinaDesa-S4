package com.example.falahal_wrold

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit
import com.example.falahal_wrold.databinding.ActivityAuthBinding
import com.example.falahal_wrold.Home.pertemuan6.MainActivityBinaDesa
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bar.left, bar.top, bar.right, bar.bottom)
            insets
        }

        val pref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnSignIn.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi input kosong
            if (username.isEmpty() || password.isEmpty()) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Peringatan")
                    .setMessage("Username dan Password tidak boleh kosong!")
                    .setPositiveButton("OK", null)
                    .show()
                return@setOnClickListener
            }

            val savedUser = pref.getString("saved_username", "")
            val savedPass = pref.getString("saved_password", "")

            // Kondisi 1: Bypass jika username sama dengan password (untuk testing awal)
            val isRule1 = (username == password)
            // Kondisi 2: Login dengan data yang sudah terdaftar di SharedPreferences
            val isRule2 = (username == savedUser && password == savedPass)

            if (isRule1 || isRule2) {
                // LOGIKAL LOGIN BERHASIL
                startActivity(Intent(this, MainActivityBinaDesa::class.java))
                finish()
            } else {
                // LENGKAH REGISTRASI OTOMATIS
                // Jika belum ada user terdaftar, daftarkan inputan ini sebagai user baru
                if (savedUser.isNullOrEmpty()) {
                    pref.edit {
                        putString("saved_username", username)
                        putString("saved_password", password)
                    }

                    MaterialAlertDialogBuilder(this)
                        .setTitle("Registrasi Berhasil")
                        .setMessage("Akun Anda telah didaftarkan. Silakan klik tombol kembali untuk masuk.")
                        .setPositiveButton("OK", null)
                        .show()
                } else {
                    // Jika sudah ada akun terdaftar tapi inputan salah
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Login Gagal")
                        .setMessage("Username atau Password salah!")
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
        }
    }
}