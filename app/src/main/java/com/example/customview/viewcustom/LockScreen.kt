package com.example.customview.viewcustom

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import kotlin.math.min
import kotlin.math.pow

class LockScreen(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var listPoint = ArrayList<Point>()
    private var passFromUser = ArrayList<Int>()
    private var fontSize = 0
    private var paint: Paint = Paint()
    private var padding = 0f
    private var startPoint: Point? = null
    private var endPoint: Point? = null
    private var isInit = false
    private var isDrawing = false
    private var isCorrect = false
    private var isCheck = false
    private var firstTime = true
    private var passSaved = ArrayList<Int>()
    private var share = context.getSharedPreferences("LockPattern", MODE_PRIVATE)

    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            init()
            if (firstTime) {
                paint.color = Color.BLUE
                canvas.drawText("Tạo mật khẩu!", width / 2f, (padding + 100) / 2, paint)
            } else {
                paint.color = Color.BLUE
                canvas.drawText("Vẽ để mở khóa!", width / 2f, (padding + 100) / 2, paint)
            }
        }
        drawPoint(canvas)
        userDrawPass(canvas)
        if (firstTime) {
            if (isCheck) {
                if (isCorrect) {
                    paint.color = Color.GREEN
                    canvas.drawText(
                        "Thiết lập mật khẩu thành công",
                        width / 2f,
                        (padding + 100) / 2,
                        paint
                    )
                    firstTime = false
                    share.edit().putBoolean("firstTime", false).apply()
                } else {
                    paint.color = Color.RED
                    canvas.drawText(
                        "Mật khẩu tối thiểu 4 kí tự",
                        width / 2f,
                        (padding + 100) / 2,
                        paint
                    )
                }
            }
            isCheck = false
        } else {
            if (isCheck) {
                if (isCorrect) {
                    paint.color = Color.GREEN
                    canvas.drawText("Mật khẩu chính xác", width / 2f, (padding + 100) / 2, paint)
                } else {
                    paint.color = Color.RED
                    canvas.drawText("Sai mật khẩu", width / 2f, (padding + 100) / 2, paint)
                }
                isCheck = false
            }
        }
    }

    private fun init() {
        padding = min(width, height) / 6f
        fontSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 20f,
            resources.displayMetrics
        ).toInt()
        paint.textSize = fontSize.toFloat()
        paint.color = Color.WHITE
        paint.textAlign = Paint.Align.CENTER

        listPoint.add(Point(padding.toInt(), padding.toInt() + 100))
        listPoint.add(Point(width / 2, padding.toInt() + 100))
        listPoint.add(Point(width - padding.toInt(), padding.toInt() + 100))

        listPoint.add(Point(padding.toInt(), height / 2 + 50))
        listPoint.add(Point(width / 2, height / 2 + 50))
        listPoint.add(Point(width - padding.toInt(), height / 2 + 50))

        listPoint.add(Point(padding.toInt(), height - padding.toInt()))
        listPoint.add(Point(width / 2, height - padding.toInt()))
        listPoint.add(Point(width - padding.toInt(), height - padding.toInt()))

        firstTime = share.getBoolean("firstTime", true)

        val size = share.getInt("size", 0)

        for (i in 0 until size)
            passSaved.add(share.getInt("key$i", 0))
        isInit = true
    }

    private fun drawPoint(canvas: Canvas) {
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL

        for (i in listPoint) {
            canvas.drawCircle(i.x.toFloat(), i.y.toFloat(), 15f, paint)
        }
    }

    private fun userDrawPass(canvas: Canvas) {
        if (isDrawing && endPoint != null) {
            paint.color = Color.WHITE
            paint.strokeWidth = 5f
            paint.style = Paint.Style.STROKE
            for (i in 0..passFromUser.size) {
                if (i < passFromUser.size - 1) {
                    canvas.drawLine(
                        listPoint[passFromUser[i]].x.toFloat(),
                        listPoint[passFromUser[i]].y.toFloat(),
                        listPoint[passFromUser[i + 1]].x.toFloat(),
                        listPoint[passFromUser[i + 1]].y.toFloat(),
                        paint
                    )
                }
            }
            canvas.drawLine(
                startPoint!!.x.toFloat(),
                startPoint!!.y.toFloat(), endPoint!!.x.toFloat(), endPoint!!.y.toFloat(), paint
            )
            startPoint = endPoint
            endPoint = null
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                for (i in listPoint) {
                    val x = (event.x - i.x).toDouble().pow(2.0)
                    val y = (event.y - i.y).toDouble().pow(2.0)
                    if (x + y < 85.0.pow(2.0) && !passFromUser.contains(listPoint.indexOf(i))
                    ) {
                        passFromUser.add(listPoint.indexOf(i))
                        startPoint = i
                        isDrawing = true
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (startPoint != null) {
                    for (i in listPoint) {
                        val x = (event.x - i.x).toDouble().pow(2.0)
                        val y = (event.y - i.y).toDouble().pow(2.0)
                        if (x + y < 70.0.pow(2.0) && !passFromUser.contains(listPoint.indexOf(i))
                        ) {
                            endPoint = i
                            passFromUser.add(listPoint.indexOf(i))
                            invalidate()
                        }
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                startPoint = null
                endPoint = null
                isDrawing = false
                isCheck = true
                if (!firstTime)
                    checkPassword()
                else
                    savePassword()
                invalidate()
            }
        }
        return true
    }

    private fun savePassword() {
        isCorrect = if (passFromUser.size < 4) {
            passFromUser.clear()
            false
        } else {
            passSaved.clear()
            passSaved.addAll(passFromUser)
            share.edit().putInt("size", passSaved.size).apply()
            for (i in passSaved.indices) {
                share.edit().putInt("key$i", passSaved[i]).apply()
            }
            passFromUser.clear()
            true
        }
    }

    private fun checkPassword() {
        if (passFromUser.size < 4 || passFromUser.size != passSaved.size) {
            isCorrect = false
            passFromUser.clear()
            return
        }
        for (i in passSaved.indices) {
            if (passFromUser[i] != passSaved[i]) {
                isCorrect = false
                passFromUser.clear()
                return
            }
        }
        passFromUser.clear()
        isCorrect = true
    }

}