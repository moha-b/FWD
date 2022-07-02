package com.example.diceroller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var rollImage: ImageView
    lateinit var roll: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        roll = findViewById(R.id.roll_button)
        rollImage = findViewById(R.id.image)
        //roll.text = "Roll it"
        roll.setOnClickListener {
            //Toast.makeText(this,"U Clicked the Button",Toast.LENGTH_LONG).show()
            diceRoll()
        }
    }

    private fun diceRoll() {
        val rand =  Random().nextInt(6) + 1
        val res = when(rand){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_5
        }
        rollImage.setImageResource(res)
    }
}