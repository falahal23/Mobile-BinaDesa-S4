package com.example.falahal_wrold

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast // Tambahkan Toast untuk notifikasi
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Penanganan System Bars (Edge-to-Edge)
        // Pastikan di XML-mu, Root Layout memiliki android:id="@+id/main"
        val mainView = findViewById<android.view.View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Inisialisasi View
        val inputAlas = findViewById<EditText>(R.id.inputAlas)
        val inputTinggi = findViewById<EditText>(R.id.inputTinggi)
        val btnHitungSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)

        val inputPanjang = findViewById<EditText>(R.id.inputPanjang)
        val inputLebar = findViewById<EditText>(R.id.inputLebar)
        val inputTinggiBalok = findViewById<EditText>(R.id.inputTinggiBalok)
        val btnHitungBalok = findViewById<Button>(R.id.btnHitungBalok)

        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // Logika Hitung Segitiga
        btnHitungSegitiga.setOnClickListener {
            val sAlas = inputAlas.text.toString()
            val sTinggi = inputTinggi.text.toString()

            if (sAlas.isEmpty() || sTinggi.isEmpty()) {
                Toast.makeText(this, "Alas dan Tinggi harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val alas = sAlas.toDouble()
                val tinggi = sTinggi.toDouble()
                val hasil = 0.5 * alas * tinggi

                tvHasil.text = "Luas Segitiga: $hasil"
                Log.d("HASIL_HITUNG", "Menghitung Segitiga: Alas=$alas, Tinggi=$tinggi, Hasil=$hasil")
            }
        }

        // Logika Hitung Balok
        btnHitungBalok.setOnClickListener {
            val sP = inputPanjang.text.toString()
            val sL = inputLebar.text.toString()
            val sT = inputTinggiBalok.text.toString()

            if (sP.isEmpty() || sL.isEmpty() || sT.isEmpty()) {
                Toast.makeText(this, "Panjang, Lebar, dan Tinggi Balok harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val p = sP.toDouble()
                val l = sL.toDouble()
                val t = sT.toDouble()
                val hasil = p * l * t

                tvHasil.text = "Volume Balok: $hasil"
                Log.d("HASIL_HITUNG", "Menghitung Balok: P=$p, L=$l, T=$t, Hasil=$hasil")
            }
        }
    }
}