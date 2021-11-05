package com.example.customview.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnClock.setOnClickListener {
            startActivity(Intent(this, ClockCustomView::class.java))
        }
        btnLockParttern.setOnClickListener {
            startActivity(Intent(this, LockPattern::class.java))
        }
        btnEdittextCustom.setOnClickListener {
            startActivity(Intent(this, EdittextCustomActivity::class.java))
        }
        btnAnimation.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }

    }
}