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

            val kcalPb = view.findViewById<ProgressBar>(R.id.calPb)
            val cabPb = view.findViewById<ProgressBar>(R.id.tanPb)
            val proPb = view.findViewById<ProgressBar>(R.id.danPb)
            val fatPb = view.findViewById<ProgressBar>(R.id.giPb)

            val kcalTv = view.findViewById<TextView>(R.id.calTv3)
            val tanTv = view.findViewById<TextView>(R.id.tanTv)
            val danTv = view.findViewById<TextView>(R.id.danTv)
            val giTv = view.findViewById<TextView>(R.id.giTv)

            kcalPb.progress = dbHelper.getKcal(strnow)
            cabPb.progress = dbHelper.getNutri(7,strnow)
            proPb.progress = dbHelper.getNutri(8,strnow)
            fatPb.progress = dbHelper.getNutri(9,strnow)

            kcalTv.text = dbHelper.getKcal(strnow).toString()
            tanTv.text = dbHelper.getNutri(7,strnow).toString()
            danTv.text = dbHelper.getNutri(8,strnow).toString()
            giTv.text = dbHelper.getNutri(9,strnow).toString()

        }

        else {
            view = LayoutInflater.from(container.context).inflate(R.layout.pagewater,container,false)
            val waterTv = view.findViewById<TextView>(R.id.waterTv)
            waterTv.text = dbHelper.getWater().toString() + "/" + dbHelper.getColValue(6, "user_info") + "ml"

            val calPb2 = view.findViewById<ProgressBar>(R.id.calPb2)
            calPb2.progress = dbHelper.getWater()

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