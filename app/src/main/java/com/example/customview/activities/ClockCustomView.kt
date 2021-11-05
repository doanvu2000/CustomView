package com.example.customview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.customview.R
import com.example.customview.viewmodel.ClockViewModel
import kotlinx.android.synthetic.main.activity_clock_custom_view.*

class ClockCustomView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock_custom_view)
        Toast.makeText(this, "Clock", Toast.LENGTH_SHORT).show()
        supportActionBar?.title = "Clock"

        var clockViewModel = ViewModelProvider(this).get(ClockViewModel::class.java)
        var h = 0
        var m = 0
        btnSettime.setOnClickListener {
            try {
                h = edtHour.text.toString().toInt()
                m = edtMinutes.text.toString().toInt()
                if (clockViewModel.checkVal(h, m)) {
                    clock.setTimeClock(h, m)
                }
            } catch (ex: Exception) {
                h = 0
                m = 0
            }
        }
    }

    override fun onBackPressed() {
        finish()
//        super.onBackPressed()
    }
}