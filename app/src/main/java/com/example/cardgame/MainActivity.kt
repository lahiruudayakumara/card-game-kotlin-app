package com.example.cardgame

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var iv_card1: ImageView
    lateinit var iv_card2: ImageView

    lateinit var tv_player1:TextView
    lateinit var tv_player2: TextView

    lateinit var tv_war: TextView

    lateinit var b_deal: Button
    lateinit var btnMain: Button
    lateinit var btnQuit: Button

    lateinit var random: Random

    lateinit var sharedPreferences: SharedPreferences

    var player1 = 0;
    var player2 = 0;

    var cardsAaaray = intArrayOf(
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card6,
        R.drawable.heart7,
        R.drawable.heart8,
        R.drawable.heart9,
        R.drawable.heart10,
        R.drawable.heart_j,
        R.drawable.heart_q,
        R.drawable.heart_k,
        R.drawable.heart_a,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        random = Random()
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val player1Name = sharedPreferences.getString("UserName", "")

        //init object
        iv_card1 = findViewById(R.id.iv_card1)
        iv_card2 = findViewById(R.id.iv_card2)
        tv_player1 = findViewById(R.id.tv_player1)
        tv_player2 = findViewById(R.id.tv_player2)
        tv_war = findViewById(R.id.tv_war)
        b_deal = findViewById(R.id.b_deal)

        // Restore player names from SharedPreferences if available
        tv_player1.text = "$player1Name : $player1"

        //add backcard
        iv_card1.setImageResource(R.drawable.card_back)
        iv_card2.setImageResource(R.drawable.card_back)

        tv_player1 = findViewById(R.id.tv_player1)
        tv_player2 = findViewById(R.id.tv_player2)

        tv_war = findViewById(R.id.tv_war)
        tv_war.visibility = View.INVISIBLE

        b_deal = findViewById(R.id.b_deal)
        b_deal.setOnClickListener {
            //hide war label
            tv_war.visibility = View.INVISIBLE

            //generate card
            val card1 = random.nextInt(cardsAaaray.size)
            val card2 = random.nextInt(cardsAaaray.size)

            //set image
            setCardImage(card1, iv_card1)
            setCardImage(card2, iv_card2)


            //compare teh cards
            if (card1 > card2) {
                player1++
                winCheck(player1, player2);
                tv_player1.text = "$player1Name : $player1"
            } else if (card1 < card2) {
                player2++
                winCheck(player1, player2);
                tv_player2.text = "Player 2: $player2"
            } else {
                tv_war.visibility = View.VISIBLE
            }
        }

        btnMain = findViewById(R.id.btnMain)
        btnMain.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivities(arrayOf(intent));
        }

        btnQuit = findViewById(R.id.btnQuit)
        btnQuit.setOnClickListener {
            finishAffinity()
        }
    }

    private fun setCardImage(number: Int, image: ImageView) {
        image.setImageResource(cardsAaaray[number])
    }

    private fun winCheck(player1:Int, player2: Int ) {
        if (player1 == 10 || player2 == 10) {
            if(player1 == 10) {
                val intent = Intent(this, WinActivity::class.java)
                startActivities(arrayOf(intent));
            } else {
                val intent = Intent(this, LoseActivity::class.java)
                startActivities(arrayOf(intent));
            }
        }
    }
}
