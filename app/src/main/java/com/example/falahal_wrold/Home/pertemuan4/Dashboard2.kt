package com.example.falahal_wrold.Home.pertemuan4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.falahal_wrold.databinding.ActivityDashboard2Binding

class Dashboard2 : AppCompatActivity() {

    private lateinit var binding: ActivityDashboard2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDashboard2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle padding system bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔥 Contoh klik tombol (kalau ada di XML)
        binding.btnStart.setOnClickListener {
            // TODO: aksi tombol
        }
    }
}