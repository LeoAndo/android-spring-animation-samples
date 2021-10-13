package com.example.basicappsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.basicappsample.helpers.AnimationHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textHello: TextView = findViewById(R.id.text_hello)
        textHello.text = "Hello, Android Kotlin"
        AnimationHelper.startAlphaAnimation(textHello)
    }
}