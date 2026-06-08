package com.example.falahal_wrold.Home.pertemuan6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.text.Html
import android.text.Spanned
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.falahal_wrold.AuthActivity
import com.example.falahal_wrold.MainActivity
import com.example.falahal_wrold.R
import com.example.falahal_wrold.SettingsActivity
import com.example.falahal_wrold.databinding.ActivityMainBinaDesaBinding
import com.example.falahal_wrold.Home.pertemuan4.Dashboard
import com.example.falahal_wrold.Home.pertemuan4.Dashboard2
import com.example.falahal_wrold.Home.pertemuan7.AboutFragment
import com.example.falahal_wrold.Home.pertemuan7.ProfileFragment
import com.example.falahal_wrold.Home.pertemuan10.TenthActivity
import com.example.falahal_wrold.Home.pertemuan9.ListFragment
import com.example.falahal_wrold.Home.pertemuan9.NinthActivity
import com.example.falahal_wrold.Home.photo.PhotoAdapter
import com.example.falahal_wrold.data.api.DisasterApiClient
import com.example.falahal_wrold.data.model.DisasterEventModel
import com.example.falahal_wrold.data.model.PhotoModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

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
            v.setPadding(bar.left, bar.top, bar.right, 0)
            insets
        }

        // =========================
        // BUTTON MENU
        // =========================

        binding.btnMenu1.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnMenu2.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
        }

        binding.btnMenu3.setOnClickListener {
            startActivity(Intent(this, Dashboard2::class.java))
        }

        binding.btnMenu5.setOnClickListener {
            startActivity(Intent(this, NinthActivity::class.java))
        }

        binding.btnWebLink.setOnClickListener {
            val url = "http://falahal.alwaysdata.net/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        binding.btnMenuPertemuan10.setOnClickListener {
            startActivity(Intent(this, TenthActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.btnRefresh.setOnClickListener {
            loadDisasterInfo()
        }

        binding.btnMenu4.setOnClickListener {
            showLogoutDialog()
        }

        // =========================
        // FOOTER VIEW SETUP
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

        // HOME
        menuHome.setOnClickListener {
            setSelectedMenu(menuHome, iconHome, textHome)
            showMainMenu(true)
        }

        // LAPORAN
        menuLaporan.setOnClickListener {
            setSelectedMenu(menuLaporan, iconLaporan, textLaporan)
            startActivity(Intent(this, NinthActivity::class.java))
        }

        // LIST
        menuList.setOnClickListener {
            setSelectedMenu(menuList, iconList, textList)
            showMainMenu(false)
            binding.tvFragmentTitle.text = "List Bencana"
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, ListFragment())
                .commit()
        }

        // ABOUT
        menuAbout.setOnClickListener {
            setSelectedMenu(menuAbout, iconAbout, textAbout)
            showMainMenu(false)
            binding.tvFragmentTitle.text = "Tentang Aplikasi"
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AboutFragment())
                .commit()
        }

        // PROFILE
        menuProfile.setOnClickListener {
            setSelectedMenu(menuProfile, iconProfile, textProfile)
            showMainMenu(false)
            binding.tvFragmentTitle.text = "Profil Pengguna"
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, ProfileFragment())
                .commit()
        }

        // SET DEFAULT ACTIVE MENU (HOME)
        setSelectedMenu(menuHome, iconHome, textHome)
        loadDisasterInfo()
    }

    private fun loadDisasterInfo() {
        binding.tvDisasterInfo.text = "Loading info bencana..."
        binding.btnRefresh.isEnabled = false

        lifecycleScope.launch {
            // Fetch Disaster Info
            try {
                val response = DisasterApiClient.apiService.getDisasterEvents()
                val event = response.events.firstOrNull()

                binding.tvDisasterInfo.text = if (event != null) {
                    formatDisasterEvent(event)
                } else {
                    "Belum ada data kejadian bencana aktif saat ini."
                }
            } catch (exception: Exception) {
                binding.tvDisasterInfo.text =
                    "Gagal mengambil info bencana. Cek koneksi internet lalu tekan Refresh."
            }

            // 2. Tampilkan Gambar Bertema Bencana (Menggunakan Unsplash Source)
            try {
                val disasterPhotos = listOf(
                    PhotoModel("1", "Kondisi Pasca Banjir", "https://images.unsplash.com/photo-1547683905-f686c993aae5?q=80&w=500"),
                    PhotoModel("2", "Kebakaran Hutan", "https://images.unsplash.com/photo-1542332213-31f87348057f?q=80&w=500"),
                    PhotoModel("3", "Evakuasi Warga", "https://images.unsplash.com/photo-1469571486292-0ba58a3f068b?q=80&w=500"),
                    PhotoModel("4", "Kerusakan Infrastruktur", "https://images.unsplash.com/photo-1580582282544-3382f168f638?q=80&w=500"),
                    PhotoModel("5", "Tanah Longsor", "https://images.unsplash.com/photo-1511059345003-999330953c8c?q=80&w=500")
                )
                binding.rvGallery.adapter = PhotoAdapter(disasterPhotos)
            } catch (exception: Exception) {
                // Silently fail or log for gallery
            } finally {
                binding.btnRefresh.isEnabled = true
            }
        }
    }

    private fun formatDisasterEvent(event: DisasterEventModel): Spanned {
        val category = event.categories.firstOrNull()?.title ?: "Kejadian alam"
        val title = event.title ?: "Informasi bencana terbaru"
        val rawDate = event.geometry.firstOrNull()?.date ?: ""

        val formattedDate = try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            parser.timeZone = TimeZone.getTimeZone("UTC")
            val date = parser.parse(rawDate)
            val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            formatter.format(date!!)
        } catch (e: Exception) {
            rawDate.replace("T", " ").replace("Z", "")
        }

        val htmlString = "<b>Kategori:</b> $category<br/>" +
                "<b>Kejadian:</b> $title<br/>" +
                "<b>Update:</b> $formattedDate"

        return Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
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

        // Reset all
        for (i in menus.indices) {
            menus[i].setBackgroundResource(0)
            icons[i].setColorFilter(ContextCompat.getColor(this, R.color.text_sub))
            texts[i].setTextColor(ContextCompat.getColor(this, R.color.text_sub))
        }

        // Set selected
        selectedMenu.setBackgroundResource(R.drawable.bg_footer_active)
        selectedIcon.setColorFilter(ContextCompat.getColor(this, R.color.md_theme_primary))
        selectedText.setTextColor(ContextCompat.getColor(this, R.color.md_theme_primary))
    }

    private fun showMainMenu(isShowing: Boolean) {
        if (isShowing) {
            binding.tvGreeting.visibility = View.VISIBLE
            binding.tvSubtitle.visibility = View.VISIBLE
            binding.scrollView.visibility = View.VISIBLE
            binding.fragmentLayout.visibility = View.GONE
        } else {
            binding.tvGreeting.visibility = View.GONE
            binding.tvSubtitle.visibility = View.GONE
            binding.scrollView.visibility = View.GONE
            binding.fragmentLayout.visibility = View.VISIBLE
        }
    }

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
