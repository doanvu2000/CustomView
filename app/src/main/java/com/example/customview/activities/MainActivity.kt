package com.example.customview.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        binding.btnClock.setOnClickListener {
            startActivity(Intent(this, ClockCustomView::class.java))
        }
        binding.btnLockParttern.setOnClickListener {
            startActivity(Intent(this, LockPattern::class.java))
        }
        binding.btnEdittextCustom.setOnClickListener {
            startActivity(Intent(this, EdittextCustomActivity::class.java))
        }
        binding.btnAnimation.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }
    }
}