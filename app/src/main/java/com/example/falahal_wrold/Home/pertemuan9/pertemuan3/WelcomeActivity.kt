package com.example.falahal_wrold.Home.pertemuan9.pertemuan3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import com.example.falahal_wrold.MainActivity
import com.example.falahal_wrold.databinding.ActivityWelcomeBinding
import com.example.falahal_wrold.Home.pertemuan9.pertemuan4.Dashboard
import com.example.falahal_wrold.Home.pertemuan9.pertemuan4.Dashboard2

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔥 Tombol ke Bangun Ruang
        binding.btnBangunRuang.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 🔥 Tombol ke Dashboard 1
        binding.btnDashboard.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        // 🔥 Tombol ke Dashboard 2
        binding.btnDashboard2.setOnClickListener {
            val intent = Intent(this, Dashboard2::class.java)
            startActivity(intent)
        }

        // 🔥 Tombol logout dengan konfirmasi
        binding.btnLogOut.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah kamu yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}