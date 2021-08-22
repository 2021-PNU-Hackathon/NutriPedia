package org.techtown.testrecyclerview

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.renderscript.Sampler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import org.techtown.testrecyclerview.R


class ViewPagerAdapter: PagerAdapter() {
    private var mContext: Context?=null

    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase




    //position에 해당하는 페이지 생성
    lateinit var view:View
    @RequiresApi(Build.VERSION_CODES.O)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        dbHelper = DBHelper(container.context, "food_nutri.db", null, 1)
        database = dbHelper.writableDatabase


        if (position == 0) {
            view = LayoutInflater.from(container.context).inflate(R.layout.page,container,false)
            val title = view.findViewById<TextView>(R.id.title)
            title.text = dbHelper.getColValue(0,"user_info") + "kg"
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