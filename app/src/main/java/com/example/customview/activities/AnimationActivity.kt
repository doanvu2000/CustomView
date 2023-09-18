package com.example.customview.activities

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.R
import com.example.customview.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAnimationBinding.inflate(layoutInflater)
    }
    private lateinit var animation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Animation"
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        initListener()
    }

    private fun initListener() {
        //Hiệu ứng mờ
        binding.btnFadeIn.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng mờ
        binding.btnFadeOut.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng nhấp nháy
        binding.btnBlink.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.blink)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng Zoom_In
        binding.btnZoomIn.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng Zoom_Out
        binding.btnZoomOut.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng Rotate
        binding.btnRotate.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng di chuyển
        binding.btnMove.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.move)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng trượt lên
        binding.btnSlideUp.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng trượt xuống
        binding.btnSlideDown.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng thực hiện tuần tự
        binding.btnSequential.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.sequential)
            binding.image.startAnimation(animation)
        }
        //Hiệu ứng thực hiện đồng thời
        binding.btnTogether.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.together)
            binding.image.startAnimation(animation)
        }

        //Cancel Animation
        binding.btnCancel.setOnClickListener {
            binding.image.clearAnimation()
        }
    }
}