package com.solucioneshr.soft.testreign.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.solucioneshr.soft.testreign.R

class DataActivity : AppCompatActivity() {
    private lateinit var layout_progress: CardView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_data)

        layout_progress = findViewById(R.id.layoutProgressWeb)

        val bundle = intent.extras
        val URL = bundle?.getString("URL")

        var webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                layout_progress.visibility = View.INVISIBLE
            }
        }

        if (URL != null) {
            webView.loadUrl(URL)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }
}