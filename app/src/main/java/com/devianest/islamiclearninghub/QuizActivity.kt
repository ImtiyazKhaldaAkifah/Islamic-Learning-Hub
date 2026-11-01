package com.devianest.islamiclearninghub

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private data class Question(
        val questionText: String,
        val optionA: String,
        val optionB: String,
        val optionC: String,
        val optionD: String,
        val correctAnswerIndex: Int
    )

    private val questions = listOf(
        Question(
            "Apa yang dimaksud dengan Thaharah dalam ajaran Islam?",
            "A. Bersuci dari hadas dan najis",
            "B. Menghafal ayat Al-Qur'an",
            "C. Melakukan puasa sunnah",
            "D. Bersedekah kepada anak yatim",
            0
        ),
        Question(
            "Cara mensucikan diri dari Hadas Kecil adalah dengan...",
            "A. Mandi Wajib",
            "B. Wudu atau Tayammum",
            "C. Membaca Istighfar",
            "D. Mencuci pakaian",
            1
        ),
        Question(
            "Asmaul Husna 'Al-Karim' memiliki arti...",
            "A. Allah Maha Kokoh",
            "B. Allah Maha Adil",
            "C. Allah Maha Pemurah",
            "D. Allah Maha Mengetahui",
            2
        ),
        Question(
            "Berikut ini adalah contoh sikap menghormati guru di sekolah, KECUALI...",
            "A. Mendengarkan saat dijelaskan",
            "B. Mengucapkan salam saat bertemu",
            "C. Memotong pembicaraan guru",
            "D. Mengerjakan tugas tepat waktu",
            2
        ),
        Question(
            "Mengapa kita harus menjaga kebersihan badan sebelum melaksanakan Salat?",
            "A. Agar dipuji teman sekelas",
            "B. Karena suci dari najis adalah syarat sah salat",
            "C. Supaya salat terasa cepat selesai",
            "D. Hanya sebagai formalitas sekolah",
            1
        )
    )

    private var currentQuestionIndex = 0
    private var score = 0

    // 🎵 Variable Backsound Musik
    private var bgmPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // 🎼 Inisialisasi & Jalankan Musik Backsound
        try {
            bgmPlayer = MediaPlayer.create(this, R.raw.quiz_bgm)
            bgmPlayer?.isLooping = true // Musik muter terus pas ngerjain kuis
            bgmPlayer?.setVolume(1.0f, 1.0f) // Volume FULL 100%
            bgmPlayer?.start()
        } catch (e: Exception) {
            Log.e("QuizActivity", "Gagal memutar audio: ${e.message}")
        }

        val tvQuestionNumber = findViewById<TextView>(R.id.tvQuestionNumber)
        val progressBarQuiz = findViewById<ProgressBar>(R.id.progressBarQuiz)
        val tvQuestionText = findViewById<TextView>(R.id.tvQuestionText)
        val rgQuizOptions = findViewById<RadioGroup>(R.id.rgQuizOptions)
        val rbOptionA = findViewById<RadioButton>(R.id.rbOptionA)
        val rbOptionB = findViewById<RadioButton>(R.id.rbOptionB)
        val rbOptionC = findViewById<RadioButton>(R.id.rbOptionC)
        val rbOptionD = findViewById<RadioButton>(R.id.rbOptionD)
        val btnNextQuestion = findViewById<Button>(R.id.btnNextQuestion)

        fun displayQuestion() {
            val q = questions[currentQuestionIndex]
            tvQuestionNumber.text = "Pertanyaan ${currentQuestionIndex + 1} dari ${questions.size}"
            progressBarQuiz.progress = currentQuestionIndex + 1
            tvQuestionText.text = q.questionText

            rbOptionA.text = q.optionA
            rbOptionB.text = q.optionB
            rbOptionC.text = q.optionC
            rbOptionD.text = q.optionD

            rgQuizOptions.clearCheck()

            if (currentQuestionIndex == questions.size - 1) {
                btnNextQuestion.text = "Lihat Hasil Kuis 🏁"
            } else {
                btnNextQuestion.text = "Lanjut Soal Berikutnya ➔"
            }
        }

        displayQuestion()

        btnNextQuestion.setOnClickListener {
            val selectedOptionId = rgQuizOptions.checkedRadioButtonId

            if (selectedOptionId == -1) {
                Toast.makeText(this, "Pilih salah satu jawaban dulu ya!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedIndex = when (selectedOptionId) {
                R.id.rbOptionA -> 0
                R.id.rbOptionB -> 1
                R.id.rbOptionC -> 2
                R.id.rbOptionD -> 3
                else -> -1
            }

            if (selectedIndex == questions[currentQuestionIndex].correctAnswerIndex) {
                score += 20
            }

            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            } else {
                // Matikan musik kalau kuis selesai
                bgmPlayer?.stop()

                val intentToScore = Intent(this, ScoreActivity::class.java).apply {
                    putExtra("EXTRA_SCORE", score)
                    putExtra("TOTAL_QUESTIONS", questions.size)
                }
                startActivity(intentToScore)
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (bgmPlayer?.isPlaying == true) {
            bgmPlayer?.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (bgmPlayer != null && !bgmPlayer!!.isPlaying) {
            bgmPlayer?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bgmPlayer?.stop()
        bgmPlayer?.release()
    }
}