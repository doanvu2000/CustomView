package com.example.customview.viewcustom

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class Clock(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    companion object {
        var w = 0
        var h = 0
        var padding = 0
        var fontSize = 0
        var numeralSpacing = 0
        var handTruncation = 0
        var hourHandTruncation = 0
        var radius = 0
        var paint = Paint()
        var isInit = false
        var numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        var rect = Rect()
        var calendar: Calendar? = null

    }

    private fun initClock() {
        h = height
        w = width
        padding = numeralSpacing + 50
        fontSize =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13f, resources.displayMetrics)
                .toInt()
        val m = min(w, h)
        radius = m / 2 - padding
        handTruncation = m / 20
        hourHandTruncation = m / 7
        paint = Paint()
        calendar = Calendar.getInstance()
        isInit = true
        var mHandler = Handler(Looper.getMainLooper())
        mHandler.postDelayed(object : Runnable {
            override fun run() {
                calendar?.add(Calendar.SECOND, 1)
                mHandler.postDelayed(this, 1000)
            }
        }, 1000)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isInit) {
            initClock()
        }
        canvas?.drawColor(Color.BLACK)
        drawCircle(canvas)
        drawCenter(canvas)
        drawNumeral(canvas)
        drawHands(canvas)
//        Handler(Looper.getMainLooper()).postDelayed({
//            calendar?.add(Calendar.SECOND, 1)
//        }, 1000)
        postInvalidateDelayed(1000)
        invalidate()
    }


    private fun drawHand(canvas: Canvas?, loc: Double, isHour: Boolean, color: Int) {
        paint.color = color
        var angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius =
            if (isHour) (radius - handTruncation - hourHandTruncation) else (radius - handTruncation)
        canvas?.drawLine(
            w / 2f,
            h / 2f,
            (w / 2 + cos(angle) * handRadius).toFloat(),
            (h / 2 + sin(angle) * handRadius).toFloat(),
            paint
        )
    }


    private fun drawHands(canvas: Canvas?) {
        var hour = calendar?.get(Calendar.HOUR_OF_DAY)
        var minutes = calendar?.get(Calendar.MINUTE)
        hour = if (hour!! > 12) (hour - 12) else hour
        drawHand(
            canvas,
            ((hour + calendar?.get(Calendar.MINUTE)!! / 60) * 5).toDouble(),
            true, Color.YELLOW
        )
        drawHand(canvas, minutes!!.toDouble(), false, Color.BLUE)
        drawHand(canvas, calendar?.get(Calendar.SECOND)!!.toDouble(), false, Color.RED)

    }

    private fun drawNumeral(canvas: Canvas?) {
        paint.textSize = fontSize.toFloat()
        for (number in numbers) {
            var tmp = "$number"
            paint.getTextBounds(tmp, 0, tmp.length, rect)
            val angle = Math.PI / 6 * (number - 3)
            val x = (w / 2 + cos(angle) * radius - rect.width() / 2)
            val y = (h / 2 + sin(angle) * radius + rect.width() / 2)
            canvas?.drawText(tmp, x.toFloat(), y.toFloat(), paint)
        }
    }

    private fun drawCenter(canvas: Canvas?) {
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(w / 2f, h / 2f, 12f, paint)
    }


    private fun drawCircle(canvas: Canvas?) {
        paint.reset()
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.isAntiAlias = true
        canvas?.drawCircle(w / 2f, h / 2f, radius + padding - 10f, paint)
    }

    fun setTimeClock(h: Int, m: Int) {
        Log.d("Activity-Clock", "set $h - $m ")
        calendar?.set(Calendar.HOUR_OF_DAY, h)
        calendar?.set(Calendar.MINUTE, m)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

}

