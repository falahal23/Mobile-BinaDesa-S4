package com.example.falahal_wrold.pertemuan6

import android.content.Intent
import android.os.Bundle
import android.view.View // Import ini penting
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.falahal_wrold.AuthActivity
import com.example.falahal_wrold.MainActivity
import com.example.falahal_wrold.R
import com.example.falahal_wrold.databinding.ActivityMainBinaDesaBinding
import com.example.falahal_wrold.pertemuan4.Dashboard
import com.example.falahal_wrold.pertemuan4.Dashbord2
import com.example.falahal_wrold.pertemuan7.AboutFragment
import com.example.falahal_wrold.pertemuan7.HomeFragment
import com.example.falahal_wrold.pertemuan7.ProfileFragment

class MainActivityBinaDesa : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinaDesaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinaDesaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TOOLBAR
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Dashboard"
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // PADDING SYSTEM BARS
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bar.left, bar.top, bar.right, bar.bottom)
            insets
        }

        // BUTTON MENU UTAMA
        binding.apply {
            btnMenu1.setOnClickListener {
                startActivity(Intent(this@MainActivityBinaDesa, MainActivity::class.java))
            }
            btnMenu2.setOnClickListener {
                startActivity(Intent(this@MainActivityBinaDesa, Dashboard::class.java))
            }
            btnMenu3.setOnClickListener {
                startActivity(Intent(this@MainActivityBinaDesa, Dashbord2::class.java))
            }
            btnMenu4.setOnClickListener {
                showLogoutDialog()
            }
            btnWebView.setOnClickListener {
                val intent = Intent(this@MainActivityBinaDesa, WebViewActivity::class.java)
                intent.putExtra("url", "https://falahal.alwaysdata.net/")
                startActivity(intent)
            }
        }

        // =========================
        // FOOTER FUNCTION
        // =========================

        // HOME (Kembali ke Dashboard Utama)
        binding.menuHome.setOnClickListener {
            showMainMenu(true) // Tampilkan menu dashboard
        }

        // ABOUT
        binding.menuAbout.setOnClickListener {
            showMainMenu(false) // Sembunyikan menu dashboard
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AboutFragment())
                .commit()
        }

        // PROFILE
        binding.menuProfile.setOnClickListener {
            showMainMenu(false) // Sembunyikan menu dashboard
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, ProfileFragment())
                .commit()
        }
    }

    private fun showMainMenu(isShowing: Boolean) {
        if (isShowing) {
            // Tampilkan konten dashboard asli
            binding.headerBackground.visibility = View.VISIBLE
            binding.tvGreeting.visibility = View.VISIBLE
            binding.tvSubtitle.visibility = View.VISIBLE
            binding.cardMenu.visibility = View.VISIBLE
            // Sembunyikan container fragment
            binding.frameLayout.visibility = View.GONE
        } else {
            // Sembunyikan konten dashboard agar tidak tumpang tindih
            binding.headerBackground.visibility = View.GONE
            binding.tvGreeting.visibility = View.GONE
            binding.tvSubtitle.visibility = View.GONE
            binding.cardMenu.visibility = View.GONE
            // Tampilkan container fragment
            binding.frameLayout.visibility = View.VISIBLE
        }
    }

    // LOGOUT
    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah kamu yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}