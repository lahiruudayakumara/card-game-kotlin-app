package com.example.cardgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    lateinit var inputText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val submitName = findViewById<Button>(R.id.btnSubmitName);

        inputText = findViewById(R.id.inputText);

        submitName.setOnClickListener {
            if (inputText?.text.isNullOrEmpty()) {
                Toast.makeText(this, "First Enter Name", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("UserName", inputText.text.toString())
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivities(arrayOf(intent));
            }
        }
    }
}