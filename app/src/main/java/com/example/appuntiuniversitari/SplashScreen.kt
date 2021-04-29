package com.example.appuntiuniversitari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val fadeinAnimation = AnimationUtils.loadAnimation(this,R.anim.splash_animation)

        val splashImage = findViewById<ImageView>(R.id.splash_image)
        val splashTitle = findViewById<TextView>(R.id.splash_title)

        splashImage.animation = fadeinAnimation
        splashTitle.animation = fadeinAnimation


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2500)


    }
}