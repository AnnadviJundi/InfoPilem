package com.jundi.infopilem.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jundi.infopilem.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 3000 // Waktu tunda dalam milidetik (3 detik dalam contoh ini)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Menunda navigasi ke aktivitas login menggunakan Handler
        Handler().postDelayed({
            navigateToLogin() // Memanggil metode untuk menavigasi ke aktivitas login
        }, SPLASH_DELAY)
    }

    // Metode untuk menavigasi ke aktivitas login
    private fun navigateToLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish() // Menutup aktivitas splash agar tidak bisa kembali ke sana menggunakan tombol kembali
    }
}
