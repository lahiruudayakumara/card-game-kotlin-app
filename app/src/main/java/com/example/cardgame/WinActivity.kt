package com.example.cardgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WinActivity : AppCompatActivity() {

    lateinit var btn_win_close: Button
    lateinit var btn_win_quit: Button

    lateinit var displayMsg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_win)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val player = sharedPreferences.getString("UserName", "")

        displayMsg = findViewById(R.id.tv_dialog_message)

        displayMsg.text = "Congratulations $player"

        btn_win_close = findViewById(R.id.btn_win_close)
        btn_win_close.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivities(arrayOf(intent));
        }

        btn_win_quit = findViewById(R.id.btn_win_quit)
        btn_win_quit.setOnClickListener {
            finishAffinity()
        }

    }
}