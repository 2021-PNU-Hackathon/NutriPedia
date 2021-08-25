package org.techtown.testrecyclerview

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.renderscript.Sampler
import android.util.Log
import android.view.*
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.page.view.*
import org.techtown.testrecyclerview.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class ViewPagerAdapter: PagerAdapter() {
    private var mContext: Context?=null

    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase




    //position에 해당하는 페이지 생성
    lateinit var view:View
    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        dbHelper = DBHelper(container.context, "food_nutri.db", null, 1)
        database = dbHelper.writableDatabase

        var now = LocalDate.now()
        var strnow :String = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        if (position == 0) {
            view = LayoutInflater.from(container.context).inflate(R.layout.page,container,false)
            val title = view.findViewById<TextView>(R.id.title)
            title.text = dbHelper.getColValue(0,"user_info") + "kg"
            title.setOnClickListener {
                val mDialogView = LayoutInflater.from(container.context).inflate(R.layout.activity_current_weight, null)
                val mBuilder = AlertDialog.Builder(container.context)
                    .setView(mDialogView)
                val mAlertDialog = mBuilder.show()
                mAlertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                mAlertDialog.window!!.setGravity(Gravity.BOTTOM)
//            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

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

        }

        else {
            view = LayoutInflater.from(container.context).inflate(R.layout.pagewater,container,false)
            val waterTv = view.findViewById<TextView>(R.id.waterTv)
            waterTv.text = dbHelper.getWater().toString() + "/" + dbHelper.getColValue(6, "user_info") + "ml"
        }


//        tanPb.progress = value

//        danPb.progress = value

//        giPb.progress = value

        container.addView(view)
        return view
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return 2
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}