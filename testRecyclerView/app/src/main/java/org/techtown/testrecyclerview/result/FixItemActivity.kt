package org.techtown.testrecyclerview.result

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_fix_item.*
import org.techtown.testrecyclerview.MainActivity
import org.techtown.testrecyclerview.R
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FixItemActivity : AppCompatActivity() {

    val fixArray = ArrayList<FoodResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fix_item)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.title = "----년--월--일"                 //디비 날짜 불러오기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))


        val dbArray = ArrayList<FoodResult>()

        fixArray.clear()
        fixArray.addAll(dbArray)
        fixArray.add(fixArray.size,FoodResult("add",0,0,0,0,null,false))

        mainIv.setImageURI(fixArray[0].uri)
        fixArray.clear()

        foodTv1.text = fixArray[0].foodName
        kcalTv.text = fixArray[0].calorie.toString() + "Kcal"
        nutri1_Tv.text = fixArray[0].nutri1.toString() + "g"
        nutri2_Tv.text = fixArray[0].nutri2.toString() + "g"
        nutri3_Tv.text = fixArray[0].nutri3.toString() + "g"
        var total : Double = 0.0
        for (i in 0 until fixArray.size) {
            total += fixArray[i].calorie
        }
        totalCal.text = total.toString() + "Kcal"

        val mAdapter = ResultAdapter(this, fixArray)
        addRecyclerView.adapter = mAdapter

        var adapter = addRecyclerView.adapter
        addRecyclerView.invalidate()
        adapter!!.notifyDataSetChanged()
        val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        addRecyclerView.layoutManager = lm
        addRecyclerView.setHasFixedSize(true)

        addRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val child = addRecyclerView.findChildViewUnder(e.x, e.y)
                try {
                    val position : Int = addRecyclerView.getChildAdapterPosition(child!!)
                    if (position == fixArray.size-1) { // 마지막거

                    } else if (fixArray[position].uri != null) {
                        mainIv.setImageURI(fixArray[position].uri)
                        foodTv1.text = fixArray[position].foodName
                        kcalTv.text = fixArray[position].calorie.toString() + "Kcal"
                        nutri1_Tv.text = fixArray[position].nutri1.toString() + "g"
                        nutri2_Tv.text = fixArray[position].nutri2.toString() + "g"
                        nutri3_Tv.text = fixArray[position].nutri3.toString() + "g"
                        var total : Double = 0.0
                        for (i in 0 until fixArray.size) {
                            total += fixArray[i].calorie
                        }
                        totalCal.text = total.toString() + "Kcal"
                    } else
                    {
                        mainIv.setImageResource(R.drawable.ic_no_image)
                        foodTv1.text =fixArray[position].foodName
                        kcalTv.text = fixArray[position].calorie.toString() + "Kcal"
                        nutri1_Tv.text = fixArray[position].nutri1.toString() + "g"
                        nutri2_Tv.text = fixArray[position].nutri2.toString() + "g"
                        nutri3_Tv.text = fixArray[position].nutri3.toString() + "g"
                        var total : Double = 0.0
                        for (i in 0 until fixArray.size) {
                            total += fixArray[i].calorie
                        }
                        totalCal.text = total.toString() + "Kcal"
                    }
                } catch (e : NullPointerException) {

                }


                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }

        })
        button.setOnClickListener {

            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        var adapter = addRecyclerView.adapter
        addRecyclerView.invalidate()
        adapter!!.notifyDataSetChanged()


        mainIv.setImageURI(fixArray[0].uri)

        foodTv1.text = fixArray[0].foodName
        kcalTv.text = fixArray[0].calorie.toString() + "Kcal"
        nutri1_Tv.text = fixArray[0].nutri1.toString() + "g"
        nutri2_Tv.text = fixArray[0].nutri2.toString() + "g"
        nutri3_Tv.text = fixArray[0].nutri3.toString() + "g"
        var total : Double = 0.0
        for (i in 0 until fixArray.size) {
            total += fixArray[i].calorie
        }
        totalCal.text = total.toString() + "Kcal"


    }



}