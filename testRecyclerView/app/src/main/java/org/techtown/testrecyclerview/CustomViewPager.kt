package org.techtown.testrecyclerview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.pagewater.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CustomViewPager : ViewPager {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    ) {}
    var splitArray = emptyArray<String>()
    val dbHelper = DBHelper(context, "food_nutri.db", null, 1)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        btn100.setOnClickListener {
            var now = LocalDate.now()
            var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            var setProg = calPb2.getProgress()
            setProg += 100
            calPb2.setProgress(setProg)
            splitArray = waterTv.text.split("/").toTypedArray()
            var water = dbHelper.getColValue(1, "water").toInt()
            water += 100
            waterTv.text = water.toString() + "/" + splitArray[1]
            dbHelper.updatewater("amount", dbHelper.getColValue(1, "water").toInt()+100, Strnow)
        }
        btn250.setOnClickListener {
            var now = LocalDate.now()
            var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            var setProg = calPb2.getProgress()
            setProg += 250
            calPb2.setProgress(setProg)
            splitArray = waterTv.text.split("/").toTypedArray()
            var water = dbHelper.getColValue(1, "water").toInt()
            water += 250
            waterTv.text = water.toString() + "/" + splitArray[1]
            dbHelper.updatewater("amount", dbHelper.getColValue(1, "water").toInt()+250, Strnow)

        }
        btn500.setOnClickListener {
            var now = LocalDate.now()
            var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            var setProg = calPb2.getProgress()
            setProg += 500
            calPb2.setProgress(setProg)
            splitArray = waterTv.text.split("/").toTypedArray()
            var water = dbHelper.getColValue(1, "water").toInt()
            water += 500
            waterTv.text = water.toString() + "/" + splitArray[1]
            dbHelper.updatewater("amount", dbHelper.getColValue(1, "water").toInt()+500, Strnow)

        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(ev)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec

        val mode = MeasureSpec.getMode(heightMeasureSpec)

        if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            var height = 0
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                child.measure(
                    widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                )
                val h = child.measuredHeight
                if (h > height) height = h
            }

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}