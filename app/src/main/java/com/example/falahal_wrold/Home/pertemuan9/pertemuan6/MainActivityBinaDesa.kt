package com.example.falahal_wrold.Home.pertemuan9.pertemuan6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.falahal_wrold.AuthActivity
import com.example.falahal_wrold.MainActivity
import com.example.falahal_wrold.R
import com.example.falahal_wrold.SettingsActivity
import com.example.falahal_wrold.databinding.ActivityMainBinaDesaBinding
import com.example.falahal_wrold.Home.pertemuan9.pertemuan4.Dashboard
import com.example.falahal_wrold.Home.pertemuan9.pertemuan4.Dashboard2
import com.example.falahal_wrold.Home.pertemuan9.pertemuan7.AboutFragment
import com.example.falahal_wrold.Home.pertemuan9.pertemuan7.ProfileFragment
import com.example.falahal_wrold.Home.pertemuan9.pertemuan10.TenthActivity
import com.example.falahal_wrold.Home.pertemuan9.ListFragment
import com.example.falahal_wrold.Home.pertemuan9.NinthActivity

class MainActivityBinaDesa : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinaDesaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinaDesaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // =========================
        // SYSTEM BAR
        // =========================

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->

            val bar = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                bar.left,
                bar.top,
                bar.right,
                0
            )

            insets
        }

        // =========================
        // BUTTON MENU
        // =========================

        // BANGUN RUANG
        binding.btnMenu1.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }

        // DASHBOARD 1
        binding.btnMenu2.setOnClickListener {

            startActivity(
                Intent(this, Dashboard::class.java)
            )
        }

        // DASHBOARD 2
        binding.btnMenu3.setOnClickListener {

            startActivity(
                Intent(this, Dashboard2::class.java)
            )
        }

        // INFO BENCANA
        binding.btnMenu5.setOnClickListener {

            startActivity(
                Intent(this, NinthActivity::class.java)
            )
        }

        // WEB BINA DESA
        binding.btnWebLink.setOnClickListener {
            val url = "http://falahal.alwaysdata.net/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        // PERTEMUAN SEBELUMNYA (Contoh: Pertemuan 10)
        binding.btnMenuPertemuan10.setOnClickListener {
            startActivity(
                Intent(this, TenthActivity::class.java)
            )
        }

        // SETTINGS
        binding.btnSettings.setOnClickListener {
            startActivity(
                Intent(this, SettingsActivity::class.java)
            )
        }

        // LOGOUT
        binding.btnMenu4.setOnClickListener {

            showLogoutDialog()
        }

        // =========================
        // FOOTER VIEW
        // =========================

        val menuHome = findViewById<LinearLayout>(R.id.menuHome)
        val menuLaporan = findViewById<LinearLayout>(R.id.menuLaporan)
        val menuList = findViewById<LinearLayout>(R.id.menuList)
        val menuAbout = findViewById<LinearLayout>(R.id.menuAbout)
        val menuProfile = findViewById<LinearLayout>(R.id.menuProfile)

        val iconHome = findViewById<ImageView>(R.id.iconHome)
        val iconLaporan = findViewById<ImageView>(R.id.iconLaporan)
        val iconList = findViewById<ImageView>(R.id.iconList)
        val iconAbout = findViewById<ImageView>(R.id.iconAbout)
        val iconProfile = findViewById<ImageView>(R.id.iconProfile)

        val textHome = findViewById<TextView>(R.id.textHome)
        val textLaporan = findViewById<TextView>(R.id.textLaporan)
        val textList = findViewById<TextView>(R.id.textList)
        val textAbout = findViewById<TextView>(R.id.textAbout)
        val textProfile = findViewById<TextView>(R.id.textProfile)

        // =========================
        // FOOTER FUNCTION
        // =========================

        // HOME
        menuHome.setOnClickListener {

            setSelectedMenu(
                menuHome,
                iconHome,
                textHome
            )

            showMainMenu(true)
        }

        // LAPORAN
        menuLaporan.setOnClickListener {

            setSelectedMenu(
                menuLaporan,
                iconLaporan,
                textLaporan
            )

            startActivity(
                Intent(this, NinthActivity::class.java)
            )
        }

        // LIST
        menuList.setOnClickListener {

            setSelectedMenu(
                menuList,
                iconList,
                textList
            )

            showMainMenu(false)
            binding.tvFragmentTitle.text = "List Bencana"

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    ListFragment()
                )
                .commit()
        }

        // ABOUT
        menuAbout.setOnClickListener {

            setSelectedMenu(
                menuAbout,
                iconAbout,
                textAbout
            )

            showMainMenu(false)
            binding.tvFragmentTitle.text = "Tentang Aplikasi"

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    AboutFragment()
                )
                .commit()
        }

        // PROFILE
        menuProfile.setOnClickListener {

            setSelectedMenu(
                menuProfile,
                iconProfile,
                textProfile
            )

            showMainMenu(false)
            binding.tvFragmentTitle.text = "Profil Pengguna"

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    ProfileFragment()
                )
                .commit()
        }
    }

    // =========================
    // ACTIVE FOOTER MENU
    // =========================

    private fun setSelectedMenu(
        selectedMenu: LinearLayout,
        selectedIcon: ImageView,
        selectedText: TextView
    ) {

        val menus = listOf(
            findViewById<LinearLayout>(R.id.menuHome),
            findViewById<LinearLayout>(R.id.menuLaporan),
            findViewById<LinearLayout>(R.id.menuList),
            findViewById<LinearLayout>(R.id.menuAbout),
            findViewById<LinearLayout>(R.id.menuProfile)
        )

        val icons = listOf(
            findViewById<ImageView>(R.id.iconHome),
            findViewById<ImageView>(R.id.iconLaporan),
            findViewById<ImageView>(R.id.iconList),
            findViewById<ImageView>(R.id.iconAbout),
            findViewById<ImageView>(R.id.iconProfile)
        )

        val texts = listOf(
            findViewById<TextView>(R.id.textHome),
            findViewById<TextView>(R.id.textLaporan),
            findViewById<TextView>(R.id.textList),
            findViewById<TextView>(R.id.textAbout),
            findViewById<TextView>(R.id.textProfile)
        )

        menus.forEach {
            it.isSelected = false
        }

        icons.forEach {
            it.isSelected = false
        }

        texts.forEach {
            it.isSelected = false
        }

        selectedMenu.isSelected = true
        selectedIcon.isSelected = true
        selectedText.isSelected = true
    }

    // =========================
    // SHOW MENU / FRAGMENT
    // =========================

    private fun showMainMenu(isShowing: Boolean) {

        if (isShowing) {

            binding.tvGreeting.visibility = View.VISIBLE
            binding.tvSubtitle.visibility = View.VISIBLE
            binding.fragmentLayout.visibility = View.GONE

        } else {

            binding.tvGreeting.visibility = View.GONE
            binding.tvSubtitle.visibility = View.GONE
            binding.fragmentLayout.visibility = View.VISIBLE
        }
    }

    // =========================
    // LOGOUT DIALOG
    // =========================

    private fun showLogoutDialog() {

        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah kamu yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->

                startActivity(
                    Intent(this, AuthActivity::class.java)
                )

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