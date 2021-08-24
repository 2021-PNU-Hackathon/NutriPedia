package org.techtown.testrecyclerview.previous

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_one.*
import org.techtown.testrecyclerview.*
import org.techtown.testrecyclerview.recommend.RecommendList
import org.techtown.testrecyclerview.result.FixItemActivity
import org.techtown.testrecyclerview.search.SearchList
import org.techtown.testrecyclerview.settings.SettingActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList

class PreviousActivity : AppCompatActivity() {

    var mealtime = arrayOf("아침","아점","점심","점저","저녁","간식")
    lateinit var dbHelper : DBHelper
    lateinit var db : SQLiteDatabase
    var foodList = arrayListOf<RecordFoodData>()
    val displayList = ArrayList<RecordFoodData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous)
        supportActionBar!!.hide()

        var intent = getIntent()
        var now : String = "${intent.getIntExtra("년",0)}-${intent.getStringExtra("월")}-${intent.getStringExtra("일")}"
        textView12.text = "${intent.getIntExtra("년",0)}년 ${intent.getStringExtra("월")}월 ${intent.getStringExtra("일")}일"

        dbHelper = DBHelper(this, "food_nutri.db", null, 1)
        db = dbHelper.readableDatabase

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview_pre) // recyclerview id

        fillFoodData(now)

        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        recyclerView.setHasFixedSize(true)
        displayList.addAll(foodList)

        var adapter = PreMyAdapter(this,displayList)
        recyclerView.adapter = adapter


        val viewAdapter= ViewPagerAdapterPre(now)
        val pagerTest = findViewById<ViewPager>(R.id.pager)
        pagerTest.adapter = viewAdapter
        pagerTest.pageMargin = 30



//        adapter.setItemClickListner(object : MainActivity.MyAdapter.OnItemClickListner{
//            override fun onClick(v: View, position: Int) {
//                val cardViewIntent = Intent(context, FixItemActivity::class.java)
//                startActivityForResult(cardViewIntent, 123)
//            }
//        })


    }

    fun fillFoodData(time: String) {
        for(i in 0..5) {
            Log.d("Log1",time)
            Log.d("Log1",mealtime[i])
            var cursor: Cursor = db.rawQuery("SELECT * FROM record where date = '${time}' and mealtime = '${mealtime[i]}'", null)
            var mealKcal : Int = 0
            var mealCab : Int =0
            var mealPro:Int =0
            var mealFat:Int =0
            var cnt : Int = 0
            var names = arrayListOf<String>()
            while (cursor.moveToNext()) {
                mealKcal += cursor.getString(6).toInt()
                mealCab += cursor.getString(7).toInt()
                mealPro += cursor.getString(8).toInt()
                mealFat += cursor.getString(9).toInt()
                names.add(cursor.getString(2))
                cnt++
            }
            if (cnt>0) {
                Log.d("Log1","good")
                var nameStr: String = ""
                for (i in 0..cnt - 2) {
                    nameStr += names[i]
                    nameStr += ","
                }
                nameStr += names[cnt - 1]
                //            if (nameStr.length >15)
                foodList.add(
                    RecordFoodData(
                        mealtime[i],
                        nameStr,
                        "1",
                        mealKcal,
                        mealCab,
                        mealPro,
                        mealFat
                    )
                )
            }
        }
    }

}

