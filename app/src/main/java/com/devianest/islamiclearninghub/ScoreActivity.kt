package com.devianest.islamiclearninghub // Sesuaikan dengan nama paket project kamu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Hubungkan ke ID komponen yang ada di activity_score.xml
        val tvFinalScore = findViewById<TextView>(R.id.tvFinalScore)
        val tvCorrectCount = findViewById<TextView>(R.id.tvCorrectCount)
        val tvWrongCount = findViewById<TextView>(R.id.tvWrongCount)
        val tvMotivationText = findViewById<TextView>(R.id.tvMotivationText)
        val btnExportPdf = findViewById<Button>(R.id.btnExportPdf)
        val btnBackToHome = findViewById<Button>(R.id.btnBackToHome)

        // Ambil data skor dari QuizActivity
        val finalScore = intent.getIntExtra("EXTRA_SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 5)

        val correctAnswers = finalScore / 20
        val wrongAnswers = totalQuestions - correctAnswers

        // Tampilkan data ke UI
        tvFinalScore.text = "$finalScore / 100"
        tvCorrectCount.text = "$correctAnswers Soal"
        tvWrongCount.text = "$wrongAnswers Soal"

        // Pesan motivasi berdasarkan nilai
        if (finalScore >= 80) {
            tvMotivationText.text = "MasyaAllah! Pemahaman kamu sangat baik! 🌟"
        } else if (finalScore >= 60) {
            tvMotivationText.text = "Bagus! Pertahankan dan pelajari lagi materi yang kurang ya! 👍"
        } else {
            tvMotivationText.text = "Jangan menyerah! Yuk baca lagi materinya dan coba lagi! 💪"
        }

        // Fitur Simulation Export PDF
        btnExportPdf.setOnClickListener {
            Toast.makeText(this, "📄 Laporan Hasil Kuis berhasil di-export ke PDF!", Toast.LENGTH_LONG).show()
        }

        // Tombol Kembali ke Beranda (MainActivity)
        btnBackToHome.setOnClickListener {
            val intentToHome = Intent(this, MainActivity::class.java)
            intentToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intentToHome)
            finish()
        }
    }
}