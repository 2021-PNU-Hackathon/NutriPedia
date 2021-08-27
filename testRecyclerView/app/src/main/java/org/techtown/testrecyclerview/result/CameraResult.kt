package org.techtown.testrecyclerview.result

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_camera_result.*
import org.techtown.testrecyclerview.MainActivity
import org.techtown.testrecyclerview.R
import org.techtown.testrecyclerview.ServerData
import org.techtown.testrecyclerview.search.SearchList
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*


class CameraResult : AppCompatActivity(){

    companion object RVarray{
        var imageArray = arrayListOf<FoodResult>()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_result)
        //clickInit()
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일")
        val now = System.currentTimeMillis()
        val date = Date(now)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.title = sdf.format(date)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))

        imageArray.clear()
        imageArray.addAll(MainActivity.arrayUse)
        imageArray.add(imageArray.size,FoodResult("add",0,0,0,0,null,false))
        Log.e("size","${imageArray.size}")

        mainIv.setImageURI(imageArray[0].uri)
        MainActivity.arrayUse.clear()

        foodTv1.text = imageArray[0].foodName
        kcalTv.text = imageArray[0].calorie.toString() + "Kcal"
        nutri1_Tv.text = imageArray[0].nutri1.toString() + "Kcal"
        nutri2_Tv.text = imageArray[0].nutri2.toString() + "Kcal"
        nutri3_Tv.text = imageArray[0].nutri3.toString() + "Kcal"
        var total : Double = 0.0
        for (i in 0 until imageArray.size) {
            total += imageArray[i].calorie
        }
        totalCal.text = total.toString() + "Kcal"

        val mAdapter = ResultAdapter(this,imageArray)
        addRecyclerView.adapter = mAdapter

        var adapter = addRecyclerView.adapter
        addRecyclerView.invalidate()
        adapter!!.notifyDataSetChanged()
        val lm = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        addRecyclerView.layoutManager = lm
        addRecyclerView.setHasFixedSize(true)

        addRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val child = addRecyclerView.findChildViewUnder(e.x, e.y)
                try {
                    val position : Int = addRecyclerView.getChildAdapterPosition(child!!)
                    if (position == imageArray.size-1) { // 마지막거

                    } else if (imageArray[position].uri != null) {
                        mainIv.setImageURI(imageArray[position].uri)
                        foodTv1.text = imageArray[position].foodName
                        kcalTv.text = imageArray[position].calorie.toString() + "Kcal"
                        nutri1_Tv.text = imageArray[position].nutri1.toString() + "Kcal"
                        nutri2_Tv.text = imageArray[position].nutri2.toString() + "Kcal"
                        nutri3_Tv.text = imageArray[position].nutri3.toString() + "Kcal"
                        var total : Double = 0.0
                        for (i in 0 until imageArray.size) {
                            total += imageArray[i].calorie
                        }
                        totalCal.text = total.toString() + "Kcal"
                    } else
                    {
                        mainIv.setImageResource(R.drawable.ic_no_image)
                        foodTv1.text = imageArray[position].foodName
                        kcalTv.text = imageArray[position].calorie.toString() + "Kcal"
                        nutri1_Tv.text = imageArray[position].nutri1.toString() + "Kcal"
                        nutri2_Tv.text = imageArray[position].nutri2.toString() + "Kcal"
                        nutri3_Tv.text = imageArray[position].nutri3.toString() + "Kcal"
                        var total : Double = 0.0
                        for (i in 0 until imageArray.size) {
                            total += imageArray[i].calorie
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
        fix_search.setOnClickListener {
            val intent = Intent(applicationContext,FixSearchResult::class.java)
            var fixPosition : Int = 1000 // 1000은 예외처리
            for (i in 0 until imageArray.size) {
                if (foodTv1.text == imageArray[i].foodName) {
                    fixPosition = i
                    break
                }
            }
            intent.putExtra("fixPosition",fixPosition)
            startActivity(intent)
        }
        button.setOnClickListener {

            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        var adapter = addRecyclerView.adapter
        addRecyclerView.invalidate()
        adapter!!.notifyDataSetChanged()


        mainIv.setImageURI(imageArray[0].uri)

        foodTv1.text = imageArray[0].foodName
        kcalTv.text = imageArray[0].calorie.toString() + "Kcal"
        nutri1_Tv.text = imageArray[0].nutri1.toString() + "Kcal"
        nutri2_Tv.text = imageArray[0].nutri2.toString() + "Kcal"
        nutri3_Tv.text = imageArray[0].nutri3.toString() + "Kcal"
        var total : Double = 0.0
        for (i in 0 until imageArray.size) {
            total += imageArray[i].calorie
        }
        totalCal.text = total.toString() + "Kcal"


    }


}