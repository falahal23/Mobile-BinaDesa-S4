package com.example.falahal_wrold.Home.pertemuan9.pertemuan4

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.falahal_wrold.databinding.ActivityDashboardBinding
import com.google.android.material.snackbar.Snackbar

class Dashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle padding system
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔥 Snackbar saat tombol download diklik
        binding.btnDownload.setOnClickListener {
            Snackbar.make(binding.root, "Download dimulai 🚀", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") {
                    Log.e("Snackbar", "Snackbar ditutup")
                }
                .show()
        }
    }
}