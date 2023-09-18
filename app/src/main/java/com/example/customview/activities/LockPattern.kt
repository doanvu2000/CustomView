package com.example.customview.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.R

class LockPattern : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_pattern)
        supportActionBar?.title = "Lock Pattern"
    }
}