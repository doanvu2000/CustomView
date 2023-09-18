package com.example.customview.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.customview.databinding.ActivityClockCustomViewBinding
import com.example.customview.viewmodel.ClockViewModel

class ClockCustomView : AppCompatActivity() {

    private val binding by lazy {
        ActivityClockCustomViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Toast.makeText(this, "Clock", Toast.LENGTH_SHORT).show()
        supportActionBar?.title = "Clock"
        setupBackPressed()
        val clockViewModel = ViewModelProvider(this).get(ClockViewModel::class.java)
        var h = 0
        var m = 0
        binding.btnSettime.setOnClickListener {
            try {
                h = binding.edtHour.text.toString().toInt()
                m = binding.edtMinutes.text.toString().toInt()
                if (clockViewModel.checkVal(h, m)) {
                    binding.clock.setTimeClock(h, m)
                }
            } catch (ex: Exception) {
                h = 0
                m = 0
            }
        }
    }

    private fun setupBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}