package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var boxOne:TextView
    private lateinit var boxTwo:TextView
    private lateinit var boxThree:TextView
    private lateinit var boxFour:TextView
    private lateinit var boxFive:TextView
    private lateinit var red_button:Button
    private lateinit var yellow_button:Button
    private lateinit var green_button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        boxOne = findViewById(R.id.box_one)
        boxTwo = findViewById(R.id.box_two)
        boxThree = findViewById(R.id.box_three)
        boxFour = findViewById(R.id.box_four)
        boxFive = findViewById(R.id.box_five)

        red_button = findViewById(R.id.red_button)
        yellow_button = findViewById(R.id.yellow_button)
        green_button = findViewById(R.id.green_button)
        val clickableView:List<View> = listOf(boxOne,boxTwo,boxThree,boxFour,boxFive,red_button,yellow_button,green_button)

        for (item in clickableView){
            item.setOnClickListener{
                makeColored(it)
            }
        }
    }


    private fun makeColored(view: View) {
        when (view.id) {

            // Boxes using Color class colors for background
            R.id.box_one -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two -> view.setBackgroundColor(Color.GRAY)

            // Boxes using Android color resources for background
            R.id.box_three -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.red_button -> boxThree.setBackgroundResource(R.color.my_red)
            R.id.yellow_button -> boxFour.setBackgroundResource(R.color.my_yellow)
            R.id.green_button -> boxFive.setBackgroundResource(R.color.my_green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}