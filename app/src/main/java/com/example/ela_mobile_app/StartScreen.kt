package com.example.ela_mobile_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartScreen : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener {
            val intent = Intent(this, WordsList::class.java)
            startActivity(intent)
        }
    }
}