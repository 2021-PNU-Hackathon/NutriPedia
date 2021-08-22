package org.techtown.testrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.pagewater.view.*

class CustomViewPager : ViewPager {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    ) {}
    var splitArray = emptyArray<String>()

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        btn100.setOnClickListener {
            var setProg = calPb2.progress
            setProg += 100
            calPb2.progress = setProg
            splitArray = waterTv.text.split("/").toTypedArray()
            var water = splitArray[0].toInt()
            water += 100
            waterTv.text = water.toString() + "/" +splitArray[1]
        }
        btn250.setOnClickListener {
            var setProg = calPb2.progress
            setProg += 250
            calPb2.setProgress(setProg)
            splitArray = waterTv.text.split("/").toTypedArray()
            var water = splitArray[0].toInt()
            water += 250
            waterTv.text = water.toString() + "/" +splitArray[1]
        }
        btn500.setOnClickListener {
            var setProg = calPb2.progress
            setProg += 500
            calPb2.progress = setProg
            splitArray = waterTv.text.split("/").toTypedArray()
            var water = splitArray[0].toInt()
            water += 500
            waterTv.text = water.toString() + "/" +splitArray[1]
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