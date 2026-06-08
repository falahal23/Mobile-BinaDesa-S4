package com.example.falahal_wrold.Home.pertemuan9

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.falahal_wrold.R
import com.example.falahal_wrold.databinding.ActivityNinthBinding
import com.example.falahal_wrold.Home.pertemuan7.AboutFragment
import com.example.falahal_wrold.Home.pertemuan7.ProfileFragment
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // =========================
        // SYSTEM BAR
        // =========================

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                0
            )

            insets
        }

        // =========================
        // QUICK ACTION ACTION
        // =========================

        binding.btnPosko.setOnClickListener {
            Toast.makeText(this, "Mencari Posko Terdekat...", Toast.LENGTH_SHORT).show()
        }

        binding.btnEvakuasi.setOnClickListener {
            Toast.makeText(this, "Menampilkan Jalur Evakuasi...", Toast.LENGTH_SHORT).show()
        }

        // =========================
        // FORM ACTION
        // =========================

        binding.btnKirimLaporan.setOnClickListener {

            val selectedChipId = binding.chipGroupBencana.checkedChipId
            val chip = findViewById<Chip>(selectedChipId)
            val kategori = chip?.text?.toString() ?: ""

            val nama = binding.etNama.text.toString().trim()
            val jenis = binding.etJenisBencana.text.toString().trim()
            val lokasi = binding.etLokasi.text.toString().trim()
            val keterangan = binding.etKeterangan.text.toString().trim()

            if (nama.isEmpty() || jenis.isEmpty() || lokasi.isEmpty() || keterangan.isEmpty() || kategori.isEmpty()) {

                Toast.makeText(
                    this,
                    "Mohon lengkapi seluruh data laporan",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // Simulasi kirim data
                Toast.makeText(
                    this,
                    "Laporan [$kategori] berhasil dikirim! Terima kasih.",
                    Toast.LENGTH_LONG
                ).show()

                // Clear form
                binding.etNama.text?.clear()
                binding.etJenisBencana.text?.clear()
                binding.etLokasi.text?.clear()
                binding.etKeterangan.text?.clear()
                binding.chipGroupBencana.clearCheck()
            }
        }

        setupFooter()
    }

    private fun setupFooter() {

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

        // Set Active State for Laporan
        setSelectedMenu(menuLaporan, iconLaporan, textLaporan)

        // HOME
        menuHome.setOnClickListener {
            finish() // Kembali ke MainActivityBinaDesa
        }

        // LAPORAN (Current Activity)
        menuLaporan.setOnClickListener {
            setSelectedMenu(menuLaporan, iconLaporan, textLaporan)
            showMainMenu(true)
        }

        // LIST
        menuList.setOnClickListener {
            setSelectedMenu(menuList, iconList, textList)
            showMainMenu(false)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ListFragment())
                .commit()
        }

        // ABOUT
        menuAbout.setOnClickListener {
            setSelectedMenu(menuAbout, iconAbout, textAbout)
            showMainMenu(false)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AboutFragment())
                .commit()
        }

        // PROFILE
        menuProfile.setOnClickListener {
            setSelectedMenu(menuProfile, iconProfile, textProfile)
            showMainMenu(false)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment())
                .commit()
        }
    }

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

        // Reset all to inactive state
        for (i in menus.indices) {
            menus[i].setBackgroundResource(0)
            icons[i].setColorFilter(ContextCompat.getColor(this, R.color.text_sub))
            texts[i].setTextColor(ContextCompat.getColor(this, R.color.text_sub))
        }

        // Set selected menu to active state
        selectedMenu.setBackgroundResource(R.drawable.bg_footer_active)
        selectedIcon.setColorFilter(ContextCompat.getColor(this, R.color.md_theme_primary))
        selectedText.setTextColor(ContextCompat.getColor(this, R.color.md_theme_primary))
    }

    private fun showMainMenu(isShowing: Boolean) {
        if (isShowing) {
            binding.scrollView.visibility = View.VISIBLE
            binding.fragmentContainer.visibility = View.GONE
        } else {
            binding.scrollView.visibility = View.GONE
            binding.fragmentContainer.visibility = View.VISIBLE
        }
    }
}