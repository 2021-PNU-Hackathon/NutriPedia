package org.techtown.testrecyclerview

import android.content.Context

import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.viewpager.widget.ViewPager
import com.gun0912.tedpermission.TedPermissionActivity.startActivity
import kotlinx.android.synthetic.main.activity_age.view.*
import kotlinx.android.synthetic.main.page.view.*
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


        title.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.activity_current_weight, null)
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mAlertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            mAlertDialog.window!!.setGravity(Gravity.BOTTOM)

            var weightList: List<Int> = (150 downTo 35).toList()
            var weightStrConvertList = weightList.map { it.toString() }

            val changeNp = mDialogView.findViewById<NumberPicker>(R.id.infoNp)
            val fix = mDialogView.findViewById<AppCompatButton>(R.id.intentBtn)

            changeNp.maxValue = weightStrConvertList.size - 1
            changeNp.wrapSelectorWheel = true
            changeNp.displayedValues = weightStrConvertList.toTypedArray()

            changeNp.value = 150 - (dbHelper.getColValue(0,"user_info").toInt())
            var currentvalue = 150 - (dbHelper.getColValue(0,"user_info").toInt())
            changeNp.setOnValueChangedListener { picker, oldVal, newVal ->
                currentvalue = newVal
            }
            fix.setOnClickListener {
                if (dbHelper.isEmpty("change")) dbHelper.insertChange()
                dbHelper.updateUserInfo("current_weight",150 - currentvalue)
                dbHelper.updateChange(150 - currentvalue)
                dbHelper.close()
                mAlertDialog.dismiss()
            }
        }

        waterTv.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.fix_edittext_water, null)
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mAlertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            mAlertDialog.window!!.setGravity(Gravity.CENTER)

            val fix = mDialogView.findViewById<AppCompatButton>(R.id.intentBtn)

//            var imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.showSoftInput(waterInput,0)
//            waterInput.requestFocus()


            fix.setOnClickListener {
//                dbHelper.updateWater(waterInput.text.toString().toInt())
                mAlertDialog.dismiss()
            }
        }

        btn100.setOnClickListener {
            var now = LocalDate.now()
            var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            var setProg = calPb2.getProgress()
            
            setProg += 100
            calPb2.progress = setProg
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

