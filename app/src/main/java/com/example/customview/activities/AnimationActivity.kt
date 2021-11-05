package com.example.customview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.customview.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {
    private lateinit var animation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        supportActionBar?.title = "Animation"
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        clickButton()

    }

    private fun clickButton() {
        //Hiệu ứng mờ
        btnFadeIn.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            image.startAnimation(animation)
        }
        //Hiệu ứng mờ
        btnFadeOut.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            image.startAnimation(animation)
        }
        //Hiệu ứng nhấp nháy
        btnBlink.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.blink)
            image.startAnimation(animation)
        }
        //Hiệu ứng Zoom_In
        btnZoomIn.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
            image.startAnimation(animation)
        }
        //Hiệu ứng Zoom_Out
        btnZoomOut.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
            image.startAnimation(animation)
        }
        //Hiệu ứng Rotate
        btnRotate.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            image.startAnimation(animation)
        }
        //Hiệu ứng di chuyển
        btnMove.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.move)
            image.startAnimation(animation)
        }
        //Hiệu ứng trượt lên
        btnSlideUp.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
            image.startAnimation(animation)
        }
        //Hiệu ứng trượt xuống
        btnSlideDown.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
            image.startAnimation(animation)
        }
        //Hiệu ứng thực hiện tuần tự
        btnSequential.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.sequential)
            image.startAnimation(animation)
        }
        //Hiệu ứng thực hiện đồng thời
        btnTogether.setOnClickListener {
            animation = AnimationUtils.loadAnimation(this, R.anim.together)
            image.startAnimation(animation)
        }

        //Cancel Animation
        btnCancel.setOnClickListener {
            image.clearAnimation()
        }
    }
}