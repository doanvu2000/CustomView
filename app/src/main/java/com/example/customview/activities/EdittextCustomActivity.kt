package com.example.customview.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.R

class EdittextCustomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext_custom)
        supportActionBar?.title = "Custom Edittext"

    }
}