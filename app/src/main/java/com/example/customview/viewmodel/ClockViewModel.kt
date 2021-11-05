package com.example.customview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ClockViewModel : ViewModel() {
    var cal = MutableLiveData<Calendar>()

    fun checkVal(h: Int, m: Int): Boolean = h < 24 && m < 60
}
