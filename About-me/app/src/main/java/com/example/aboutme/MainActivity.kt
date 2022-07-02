package com.example.aboutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //lateinit var done:Button
    //lateinit var typeName:EditText
    //lateinit var showName:TextView
    private var myName: MyName = MyName("mohab")
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(R.layout.activity_main)

//        done = findViewById(R.id.done)
//        done.setOnClickListener {
//            addNicName(it)
//        }
        bind.myName = myName
        bind.done.setOnClickListener {
            addNicName(it)
        }
    }
    private fun addNicName(view:View){

        bind.apply {
            myName?.nickName = bind.nickName.text.toString()
            //bind.showName.text = bind.nickName.text
            invalidateAll()
            bind.nickName.visibility = View.GONE
            bind.done.visibility = View.GONE
            bind.showName.visibility = View.VISIBLE
        }
//        typeName = findViewById(R.id.nickName)
//        showName = findViewById(R.id.showName)
//
//        showName.text = typeName.text
//
//        typeName.visibility = View.GONE
//        view.visibility = View.GONE
//        showName.visibility = View.VISIBLE
    }
}