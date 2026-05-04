package com.example.falahal_wrold.pertemuan7

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.falahal_wrold.R

class SevenActivity : AppCompatActivity() {

    private lateinit var menuHome: LinearLayout
    private lateinit var menuAbout: LinearLayout
    private lateinit var menuProfile: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seven)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // FOOTER MENU
        menuHome = findViewById(R.id.menuHome)
        menuAbout = findViewById(R.id.menuAbout)
        menuProfile = findViewById(R.id.menuProfile)

        // DEFAULT FRAGMENT
        replaceFragment(HomeFragment())

        // MENU HOME
        menuHome.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        // MENU ABOUT
        menuAbout.setOnClickListener {
            replaceFragment(AboutFragment())
        }

        // MENU PROFILE
        menuProfile.setOnClickListener {
            replaceFragment(ProfileFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}
