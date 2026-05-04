package com.example.falahal_wrold.pertemuan6

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.falahal_wrold.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnBack: ImageView

    private val PREF_NAME = "MyPrefs"
    private val KEY_URL = "last_url"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view)

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        btnBack = findViewById(R.id.btnBack)

        val sharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        val urlFromIntent = intent.getStringExtra("url")
        val savedUrl = sharedPref.getString(KEY_URL, "https://falahal.alwaysdata.net/")
        val finalUrl = urlFromIntent ?: savedUrl ?: "https://falahal.alwaysdata.net/"

        webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            settings.cacheMode = WebSettings.LOAD_DEFAULT

            webViewClient = WebViewClient()

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress

                    if (newProgress == 100) {
                        progressBar.visibility = View.GONE
                    }
                }
            }

            loadUrl(finalUrl)
        }

        // tombol back di atas
        btnBack.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                finish()
            }
        }

        // back HP
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        val currentUrl = webView.url
        if (currentUrl != null) {
            getSharedPreferences(PREF_NAME, MODE_PRIVATE)
                .edit()
                .putString(KEY_URL, currentUrl)
                .apply()
        }
    }
}