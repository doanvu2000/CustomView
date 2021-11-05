package com.example.customview.viewcustom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.example.customview.R
import kotlinx.android.synthetic.main.edittextcustom.view.*
import top.defaults.colorpicker.ColorPickerPopup

@SuppressLint("AppCompatCustomView")
class EdittextCustom(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private var editText: EditText? = null
    private var colorPicker: View? = null

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.EdittextCustom)
        var cl = typedArray.getString(R.styleable.EdittextCustom_color)
        typedArray.recycle()
        if (cl == null) {
            cl = "#000000"
        }
        val inflater =
            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.edittextcustom, this, true)
        editText = findViewById(R.id.edtContent)
        colorPicker = findViewById(R.id.setColor)

        colorPicker?.setBackgroundColor(Color.parseColor(cl))
        editText?.setTextColor(Color.parseColor(cl))

        colorPicker?.setOnClickListener {
            ColorPickerPopup.Builder(context)
                .initialColor(Color.RED)
                .enableBrightness(true)
                .enableAlpha(true)
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(it, object : ColorPickerPopup.ColorPickerObserver {
                    override fun onColor(color: Int, fromUser: Boolean) {
                        //when selected color
                        editText?.setTextColor(color)
                        colorPicker?.setBackgroundColor(color)
                    }

                    override fun onColorPicked(color: Int) {
                        //when selecting color
                        editText?.setTextColor(color)
                        colorPicker?.setBackgroundColor(color)
                    }
                })
        }
    }

    fun getText(): String {
        return editText?.text.toString()
    }

}