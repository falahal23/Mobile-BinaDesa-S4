package com.example.falahal_wrold

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.falahal_wrold.databinding.ActivityAuthBinding
import com.example.falahal_wrold.pertemuan6.MainActivityBinaDesa
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
            val nama = binding.etNama.text.toString().trim()
            val noHp = binding.etNoHp.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            val savedUser = pref.getString("saved_username", "")
            val savedPass = pref.getString("saved_password", "")

            val isRule1 = (username == password && username.isNotEmpty())
            val isRule2 = (username == savedUser && password == savedPass && username.isNotEmpty())

            if (isRule1 || isRule2) {
                startActivity(Intent(this, MainActivityBinaDesa::class.java))
                finish()
            } else {
                if (noHp.isNotEmpty() && nama.isNotEmpty()) {
                    if (username == noHp) {
                        pref.edit().apply {
                            putString("saved_nama", nama)
                            putString("saved_nohp", noHp)
                            putString("saved_username", username)
                            putString("saved_password", password)
                            apply()
                        }

                        MaterialAlertDialogBuilder(this)
                            .setTitle("Registrasi Berhasil")
                            .setMessage("Data disimpan. Silahkan login kembali.")
                            .setPositiveButton("OK", null)
                            .show()
                    } else {
                        MaterialAlertDialogBuilder(this)
                            .setTitle("Error OTP")
                            .setMessage("Inputan username harus sama dengan nomor HP untuk verifikasi!")
                            .setPositiveButton("OK", null)
                            .show()
                    }
                } else {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Login Gagal")
                        .setMessage("Kredensial salah atau data tidak lengkap")
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
        }
    }
}