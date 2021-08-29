package org.techtown.testrecyclerview.result

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.NumberPicker
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_camera_result.*
import kotlinx.android.synthetic.main.activity_fix_item.*
import kotlinx.android.synthetic.main.activity_fix_item.addRecyclerView
import kotlinx.android.synthetic.main.activity_fix_item.button
import kotlinx.android.synthetic.main.activity_fix_item.foodTv1
import kotlinx.android.synthetic.main.activity_fix_item.kcalTv
import kotlinx.android.synthetic.main.activity_fix_item.mainIv
import kotlinx.android.synthetic.main.activity_fix_item.nutri1_Tv
import kotlinx.android.synthetic.main.activity_fix_item.nutri2_Tv
import kotlinx.android.synthetic.main.activity_fix_item.nutri3_Tv
import kotlinx.android.synthetic.main.activity_fix_item.totalCal
import kotlinx.android.synthetic.main.activity_search_result.*
import org.techtown.testrecyclerview.DBHelper
import org.techtown.testrecyclerview.FragmentOne
import org.techtown.testrecyclerview.MainActivity
import org.techtown.testrecyclerview.R
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class FixItemActivity : AppCompatActivity() {

    val fixArray = ArrayList<FoodResult>()
    var pos = 0
    var mt: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fix_item)
        var dbHelper = DBHelper(this, "food_nutri.db", null, 1)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.title = "----년--월--일"                 //디비 날짜 불러오기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))

        var tempList: List<Int> = (50 downTo 5).toList()
        var gramList = java.util.ArrayList<Int>()
        for (i in 0 until tempList.size) {
            gramList.add(tempList[i]*10)
        }
        var gramStrConvertList = gramList.map { it.toString() }


        val currentNp = findViewById<NumberPicker>(R.id.np_gram)

        currentNp.maxValue = gramStrConvertList.size - 1
        currentNp.wrapSelectorWheel = true
        currentNp.displayedValues = gramStrConvertList.toTypedArray()
        currentNp.value = 40
        var currentvalue = 40
        mt = FragmentOne.resultList[MainActivity.pos].mealTime

        fixArray.clear()
        for (i in 0 until FragmentOne.resultList.size) {
            if (FragmentOne.resultList[i].mealTime == FragmentOne.resultList[MainActivity.pos].mealTime) {
                var uri : Uri?
                if (FragmentOne.resultList[i].picture != null) {
                    uri = Uri.parse(FragmentOne.resultList[i].picture)
                } else {
                    uri = null
                }
                fixArray.add(
                    FoodResult(
                        FragmentOne.resultList[i].foodName,
                        FragmentOne.resultList[i].calorie,
                        FragmentOne.resultList[i].nutri1,
                        FragmentOne.resultList[i].nutri2,
                        FragmentOne.resultList[i].nutri3,
                        uri,
                        true
                    )
                )
            }
        }




        if(fixArray.size != 0) {
            if (fixArray[0].uri != null) {
                mainIv.setImageURI(fixArray[0].uri)
            } else {
                mainIv.setImageResource(R.drawable.ic_no_image)
            }
            foodTv1.text = fixArray[0].foodName
            kcalTv.text = fixArray[0].calorie.toString() + "Kcal"
            nutri1_Tv.text = fixArray[0].nutri1.toString() + "Kcal"
            nutri2_Tv.text = fixArray[0].nutri2.toString() + "Kcal"
            nutri3_Tv.text = fixArray[0].nutri3.toString() + "Kcal"
            var total : Double = 0.0
            for (i in 0 until fixArray.size) {
                total += fixArray[i].calorie
            }
            totalCal.text = total.toString() + "Kcal"
        }

        val mAdapter = ResultAdapter(this, fixArray)
        addRecyclerView.adapter = mAdapter

        var adapter = addRecyclerView.adapter
        addRecyclerView.invalidate()
        adapter!!.notifyDataSetChanged()
        val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        addRecyclerView.layoutManager = lm
        addRecyclerView.setHasFixedSize(true)

        var splitArray = nutri1_Tv.text.split("K") as MutableList<String>
        val num1 = splitArray[0].toDouble()
        splitArray.removeAll(splitArray)
        splitArray = nutri2_Tv.text.split("K") as MutableList<String>
        val num2 = splitArray[0].toDouble()
        splitArray = nutri3_Tv.text.split("K") as MutableList<String>
        val num3 = splitArray[0].toDouble()

        currentNp.setOnValueChangedListener { picker, oldVal, newVal ->
            currentvalue = newVal
            Log.e("change","$newVal")
            nutri1_Tv.text = (num1*(50-newVal)/10).roundToInt().toString() + "Kcal"
            nutri2_Tv.text = (num2*(50-newVal)/10).roundToInt().toString() + "Kcal"
            nutri3_Tv.text = (num3*(50-newVal)/10).roundToInt().toString() + "Kcal"
            kcalTv.text = ((num1*(50-newVal)/10).roundToInt()+(num2*(50-newVal)/10).roundToInt()+(num3*(50-newVal)/10).roundToInt()).toString() +"Kcal"
            totalCal.text = kcalTv.text

        }

        addRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

                try {
                    val child = addRecyclerView.findChildViewUnder(e.x, e.y)
                    val position : Int = addRecyclerView.getChildAdapterPosition(child!!)
                    if (fixArray[position].uri != null) {
                        pos = position
                        mainIv.setImageURI(fixArray[position].uri)
                        foodTv1.text = fixArray[position].foodName
                        kcalTv.text = fixArray[position].calorie.toString() + "Kcal"
                        nutri1_Tv.text = fixArray[position].nutri1.toString() + "Kcal"
                        nutri2_Tv.text = fixArray[position].nutri2.toString() + "Kcal"
                        nutri3_Tv.text = fixArray[position].nutri3.toString() + "Kcal"
                        var total : Double = 0.0
                        for (i in 0 until fixArray.size) {
                            total += fixArray[i].calorie
                        }
                        totalCal.text = total.toString() + "Kcal"
                    } else
                    {
                        pos = position
                        mainIv.setImageResource(R.drawable.ic_no_image)
                        foodTv1.text =fixArray[position].foodName
                        kcalTv.text = fixArray[position].calorie.toString() + "Kcal"
                        nutri1_Tv.text = fixArray[position].nutri1.toString() + "Kcal"
                        nutri2_Tv.text = fixArray[position].nutri2.toString() + "Kcal"
                        nutri3_Tv.text = fixArray[position].nutri3.toString() + "Kcal"
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

        if(fixArray.size != 0) {
            if (fixArray[0].uri != null) {
                mainIv.setImageURI(fixArray[0].uri)
            } else {
                mainIv.setImageResource(R.drawable.ic_no_image)
            }


            foodTv1.text = fixArray[0].foodName
            kcalTv.text = fixArray[0].calorie.toString() + "Kcal"
            nutri1_Tv.text = fixArray[0].nutri1.toString() + "Kcal"
            nutri2_Tv.text = fixArray[0].nutri2.toString() + "Kcal"
            nutri3_Tv.text = fixArray[0].nutri3.toString() + "Kcal"
            var total : Double = 0.0
            for (i in 0 until fixArray.size) {
                total += fixArray[i].calorie
            }
            totalCal.text = total.toString() + "Kcal"
        }
    }




}