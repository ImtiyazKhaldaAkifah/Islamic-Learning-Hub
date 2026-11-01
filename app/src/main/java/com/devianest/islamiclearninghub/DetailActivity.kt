package com.devianest.islamiclearninghub // Sesuaikan nama paket kamu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1. Hubungkan variabel dengan ID yang ada di activity_detail.xml
        val tvKategori = findViewById<TextView>(R.id.tvDetailCategory)
        val tvJudul = findViewById<TextView>(R.id.tvDetailTitle)
        val tvMateri = findViewById<TextView>(R.id.tvDetailContent)
        val btnKuis = findViewById<Button>(R.id.btnStartQuiz)

        // 2. Ambil data yang dikirim dari MainActivity
        val judulExtra = intent.getStringExtra("EXTRA_JUDUL") ?: "Detail Bab"
        val kategoriExtra = intent.getStringExtra("EXTRA_KATEGORI") ?: "📖 FIKIH & IBADAH"
        val materiExtra = intent.getStringExtra("EXTRA_MATERI") ?: "Materi belum tersedia."

        // 3. Tampilkan data ke layar (Gunakan try-catch agar aman dari crash)
        try {
            if (tvKategori != null) tvKategori.text = kategoriExtra
            if (tvJudul != null) tvJudul.text = judulExtra
            if (tvMateri != null) tvMateri.text = materiExtra
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 4. Klik tombol untuk berpindah ke QuizActivity
        btnKuis?.setOnClickListener {
            val intentToQuiz = Intent(this, QuizActivity::class.java)
            startActivity(intentToQuiz)
        }
    }
}