package com.example.application_flamingo

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeFullScreen()

        setContentView(R.layout.activity_splash)

        val textTitle: TextView = findViewById(R.id.title)
        val textShaderSeeAll: Shader = LinearGradient(
            250f,
            0f,
            0f,
            textTitle.paint.textSize,
            intArrayOf(Color.parseColor("#0B94EF"), Color.parseColor("#951bfe")),
            floatArrayOf(0.0f, 1f),
            Shader.TileMode.CLAMP
        )
        textTitle.paint.shader = textShaderSeeAll

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 3000)
    }
    private fun makeFullScreen(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
    }
}