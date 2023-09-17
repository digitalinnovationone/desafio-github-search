package br.com.igorbag.githubsearch.ui.view

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import br.com.igorbag.githubsearch.R

class SplashScreenActivity : AppCompatActivity() {

    lateinit var img:ImageView
    lateinit var txt:TextView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        init()
        addAnimationY(img, -400f);
        addAnimationY(txt, -400f);

        Handler().postDelayed(Runnable {
            val i = Intent(applicationContext, MainActivity::class.java);
                                       startActivity(
                                           i
                                       )
            finish();
        }, 1500)
    }

    fun init(){
        img = findViewById(R.id.imageView)
        txt = findViewById(R.id.text_title)
    }

    fun addAnimationY(view: View, x: Float){
        ObjectAnimator.ofFloat(view, "translationY", x).apply {
            duration = 1000
            start()
        }
    }
}