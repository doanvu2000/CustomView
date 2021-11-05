package com.example.customview.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import kotlinx.android.synthetic.main.activity_edittext_custom.*
import top.defaults.colorpicker.ColorPickerPopup

class EdittextCustomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext_custom)
        supportActionBar?.title = "Custom Edittext"

    }
}