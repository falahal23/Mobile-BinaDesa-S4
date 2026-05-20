package com.example.falahal_wrold

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        val listView = findViewById<ListView>(R.id.listViewSettings)

        // Data untuk ListView
        val settingsItems = arrayOf(
            "Privacy Policy",
            "Terms of Service",
            "Notification Settings",
            "App Version",
            "Help Center",
            "About Developer"
        )

        // Menggunakan ArrayAdapter sederhana
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            settingsItems
        )

        listView.adapter = adapter

        // Listener klik item
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = settingsItems[position]
            Toast.makeText(this, "Membuka: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}