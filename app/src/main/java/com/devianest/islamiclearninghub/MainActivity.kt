package com.devianest.islamiclearninghub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var rvBab: RecyclerView
    private lateinit var babAdapter: BabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi RecyclerView untuk Daftar Materi
        rvBab = findViewById(R.id.rvBab)
        rvBab.layoutManager = LinearLayoutManager(this)

        // Data bab materi PAI
        val dataBab = listOf(
            Bab(
                id = "1",
                judul = "Bab 1: Memahami Thaharah & Bersuci",
                deskripsi = "Fikih - Bersuci dari Hadas dan Najis",
                kategori = "📖 FIKIH & IBADAH",
                isiMateri = "• Thaharah artinya bersuci dari hadas dan najis.\n• Hadas Kecil: Disucikan dengan wudu/tayammum.\n• Hadas Besar: Disucikan dengan mandi wajib.\n• Syarat Sah Salat: Tempat, badan, dan pakaian harus suci!",
                funFact = "Mencuci tangan sebelum wudu membantu membunuh kuman sebelum menyentuh area wajah."
            ),
            Bab(
                id = "2",
                judul = "Bab 2: Meneladani Asmaul Husna",
                deskripsi = "Akidah - Mengenal sifat-sifat Allah SWT",
                kategori = "🕌 AKIDAH",
                isiMateri = "• Al-Karim: Allah Maha Pemurah.\n• Al-Mu'min: Allah Maha Memberi Rasa Aman.\n• Al-Wakil: Allah Maha Memelihara.\n• Al-Matin: Allah Maha Kokoh.",
                funFact = "Menerapkan sifat Al-Karim bisa dimulai dari hal simpel: tidak pelit ilmu ke teman sekelas!"
            ),
            Bab(
                id = "3",
                judul = "Bab 3: Indahnya Hormat Kepada Orang Tua & Guru",
                deskripsi = "Akhlak - Berbakti dan Menjaga Adab",
                kategori = "💚 AKHLAK",
                isiMateri = "• Ridha Allah tergantung ridha kedua orang tua.\n• Menghormati guru adalah kunci utama keberkahan ilmu.\n• Adab berbicara: Bicara santun, tidak memotong pembicaraan.",
                funFact = "Senyum dan salam saat bertemu guru di koridor sekolah termasuk sedekah sederhana!"
            )
        )

        babAdapter = BabAdapter(dataBab) { babDipilih ->
            // Buka DetailActivity saat bab diklik
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_JUDUL", babDipilih.judul)
                putExtra("EXTRA_KATEGORI", babDipilih.kategori)
                putExtra("EXTRA_MATERI", babDipilih.isiMateri)
                putExtra("EXTRA_FACT", babDipilih.funFact)
            }
            startActivity(intent)
        }

        rvBab.adapter = babAdapter

        // 2. Inisialisasi & Logika Dashboard Navigasi Bawah
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.selectedItemId = R.id.nav_home

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Posisi sedang di Home
                    true
                }
                R.id.nav_quiz -> {
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
    }
}