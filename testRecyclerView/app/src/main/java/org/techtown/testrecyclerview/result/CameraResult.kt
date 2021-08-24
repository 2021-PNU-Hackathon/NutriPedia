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


class CameraResult : AppCompatActivity(){

    companion object RVarray{
        var imageArray = arrayListOf<FoodResult>()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_result)
        //clickInit()
        supportActionBar?.setTitle("2021년 07월 27일")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))
        val uri : Uri? = intent.getParcelableExtra<Uri>("uri")

        mainIv.setImageURI(uri)

        imageArray.clear()

        imageArray.add(FoodResult("hyun",100,100,100,100,uri,true))
        imageArray.add(imageArray.size,FoodResult("add",0,0,0,0,null,false))

        foodTv1.text = imageArray[0].foodName
        kcalTv.text = imageArray[0].calorie.toString() + "Kcal"
        nutri1_Tv.text = imageArray[0].nutri1.toString() + "g"
        nutri2_Tv.text = imageArray[0].nutri2.toString() + "g"
        nutri3_Tv.text = imageArray[0].nutri3.toString() + "g"
        var total : Double = 0.0
        for (i in 0 until imageArray.size) {
            total += imageArray[i].calorie
        }
        totalCal.text = total.toString() + "Kcal"


        Log.e("size", "${imageArray.size}")
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
                val position = addRecyclerView.getChildAdapterPosition(child!!)
                if (position == imageArray.size-1) { // 마지막거

                } else if (imageArray[position].uri != null) {
                    mainIv.setImageURI(imageArray[position].uri)
                    foodTv1.text = imageArray[position].foodName
                    kcalTv.text = imageArray[position].calorie.toString() + "Kcal"
                    nutri1_Tv.text = imageArray[position].nutri1.toString() + "g"
                    nutri2_Tv.text = imageArray[position].nutri2.toString() + "g"
                    nutri3_Tv.text = imageArray[position].nutri3.toString() + "g"
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
                    nutri1_Tv.text = imageArray[position].nutri1.toString() + "g"
                    nutri2_Tv.text = imageArray[position].nutri2.toString() + "g"
                    nutri3_Tv.text = imageArray[position].nutri3.toString() + "g"
                    var total : Double = 0.0
                    for (i in 0 until imageArray.size) {
                        total += imageArray[i].calorie
                    }
                    totalCal.text = total.toString() + "Kcal"
                }

                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }

        })
    }

    override fun onResume() {
        super.onResume()
        var adapter = addRecyclerView.adapter
        addRecyclerView.invalidate()
        adapter!!.notifyDataSetChanged()
    }

//    private fun clickInit() {
////        smallIv2.setOnClickListener {
////            View1.visibility = View.INVISIBLE
//            View2.visibility = View.VISIBLE
//            View3.visibility = View.INVISIBLE
//        }
//        smallIv3.setOnClickListener {
//            View1.visibility = View.INVISIBLE
//            View2.visibility = View.INVISIBLE
//            View3.visibility = View.VISIBLE
//        }
//        smallIv1_2.setOnClickListener {
//            View1.visibility = View.VISIBLE
//            View2.visibility = View.INVISIBLE
//            View3.visibility = View.INVISIBLE
//        }
//        smallIv3_2.setOnClickListener {
//            View1.visibility = View.INVISIBLE
//            View2.visibility = View.INVISIBLE
//            View3.visibility = View.VISIBLE
//        }
//        smallIv1_3.setOnClickListener {
//            View1.visibility = View.VISIBLE
//            View2.visibility = View.INVISIBLE
//            View3.visibility = View.INVISIBLE
//        }
//        smallIv2_3.setOnClickListener {
//            View1.visibility = View.INVISIBLE
//            View2.visibility = View.VISIBLE
//            View3.visibility = View.INVISIBLE
//        }
//        smallIv4.setOnClickListener {
//            val intent = Intent(applicationContext,ResultSearchActivity::class.java)
//            startActivity(intent)
//        }
//        smallIv4_2.setOnClickListener {
//
//        }
//        smallIv4_3.setOnClickListener {
//
//        }
//        circleImageView.setOnClickListener {
//            var intent = Intent(applicationContext, SearchList::class.java)
//            startActivity(intent)
//        }
//        circleImageView_2.setOnClickListener {
//            var intent = Intent(applicationContext, SearchList::class.java)
//            startActivity(intent)
//        }
//        circleImageView_3.setOnClickListener {
//            var intent = Intent(applicationContext, SearchList::class.java)
//            startActivity(intent)
//        }
//
//        val registerBtn1= findViewById<Button>(R.id.button)
//        val registerBtn2= findViewById<Button>(R.id.button_2)
//        val registerBtn3= findViewById<Button>(R.id.button_3)
//
//        registerBtn1.setOnClickListener {
//            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
//            val mBuilder = AlertDialog.Builder(this)
//                .setView(mDialogView)
//            val mAlertDialog = mBuilder.show()
//            mAlertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
//            mAlertDialog.window!!.setGravity(Gravity.BOTTOM)
//            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//
//            val breakfast = mDialogView.findViewById<AppCompatButton>(R.id.breakfast)
//            val brunch = mDialogView.findViewById<AppCompatButton>(R.id.brunch)
//            val lunch = mDialogView.findViewById<AppCompatButton>(R.id.lunch)
//            val linner = mDialogView.findViewById<AppCompatButton>(R.id.linner)
//            val dinner = mDialogView.findViewById<AppCompatButton>(R.id.dinner)
//            val snack = mDialogView.findViewById<AppCompatButton>(R.id.snack)
//            val register = mDialogView.findViewById<AppCompatButton>(R.id.registerBtn)
//
//            var clicked : Boolean = false
//            var idCheck : AppCompatButton?= null
//            breakfast.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = breakfast
//                    clicked = true
//                    breakfast.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    breakfast.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == breakfast){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = breakfast
//                    breakfast.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    breakfast.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            brunch.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = brunch
//                    clicked = true
//                    brunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    brunch.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == brunch){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = brunch
//                    brunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    brunch.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            lunch.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = lunch
//                    clicked = true
//                    lunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    lunch.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == lunch){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = lunch
//                    lunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    lunch.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            linner.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = linner
//                    clicked = true
//                    linner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    linner.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == linner){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = linner
//                    linner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    linner.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            dinner.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = dinner
//                    clicked = true
//                    dinner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    dinner.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == dinner){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = dinner
//                    dinner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    dinner.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            snack.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = snack
//                    clicked = true
//                    snack.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    snack.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == snack){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = snack
//                    snack.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    snack.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            register.setOnClickListener {
//                mAlertDialog.dismiss()
//                finish()
//            }
//        }
//        registerBtn2.setOnClickListener {
//            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
//            val mBuilder = AlertDialog.Builder(this)
//                .setView(mDialogView)
//
//            val mAlertDialog = mBuilder.show()
//            mAlertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
//            mAlertDialog.window!!.setGravity(Gravity.BOTTOM)
//            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//            val breakfast = mDialogView.findViewById<AppCompatButton>(R.id.breakfast)
//            val brunch = mDialogView.findViewById<AppCompatButton>(R.id.brunch)
//            val lunch = mDialogView.findViewById<AppCompatButton>(R.id.lunch)
//            val linner = mDialogView.findViewById<AppCompatButton>(R.id.linner)
//            val dinner = mDialogView.findViewById<AppCompatButton>(R.id.dinner)
//            val snack = mDialogView.findViewById<AppCompatButton>(R.id.snack)
//            val register = mDialogView.findViewById<AppCompatButton>(R.id.registerBtn)
//
//            var clicked : Boolean = false
//            var idCheck : AppCompatButton?= null
//            breakfast.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = breakfast
//                    clicked = true
//                    breakfast.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    breakfast.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == breakfast){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = breakfast
//                    breakfast.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    breakfast.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            brunch.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = brunch
//                    clicked = true
//                    brunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    brunch.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == brunch){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = brunch
//                    brunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    brunch.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            lunch.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = lunch
//                    clicked = true
//                    lunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    lunch.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == lunch){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = lunch
//                    lunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    lunch.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            linner.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = linner
//                    clicked = true
//                    linner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    linner.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == linner){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = linner
//                    linner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    linner.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            dinner.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = dinner
//                    clicked = true
//                    dinner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    dinner.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == dinner){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = dinner
//                    dinner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    dinner.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            snack.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = snack
//                    clicked = true
//                    snack.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    snack.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == snack){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = snack
//                    snack.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    snack.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            register.setOnClickListener {
//                mAlertDialog.dismiss()
//                finish()
//            }
//        }
//        registerBtn3.setOnClickListener {
//            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
//            val mBuilder = AlertDialog.Builder(this)
//                .setView(mDialogView)
//
//            val mAlertDialog = mBuilder.show()
//            mAlertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
//            mAlertDialog.window!!.setGravity(Gravity.BOTTOM)
//            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//            val breakfast = mDialogView.findViewById<AppCompatButton>(R.id.breakfast)
//            val brunch = mDialogView.findViewById<AppCompatButton>(R.id.brunch)
//            val lunch = mDialogView.findViewById<AppCompatButton>(R.id.lunch)
//            val linner = mDialogView.findViewById<AppCompatButton>(R.id.linner)
//            val dinner = mDialogView.findViewById<AppCompatButton>(R.id.dinner)
//            val snack = mDialogView.findViewById<AppCompatButton>(R.id.snack)
//            val register = mDialogView.findViewById<AppCompatButton>(R.id.registerBtn)
//
//            var clicked : Boolean = false
//            var idCheck : AppCompatButton?= null
//            breakfast.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = breakfast
//                    clicked = true
//                    breakfast.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    breakfast.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == breakfast){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = breakfast
//                    breakfast.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    breakfast.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            brunch.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = brunch
//                    clicked = true
//                    brunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    brunch.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == brunch){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = brunch
//                    brunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    brunch.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            lunch.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = lunch
//                    clicked = true
//                    lunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    lunch.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == lunch){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = lunch
//                    lunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    lunch.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            linner.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = linner
//                    clicked = true
//                    linner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    linner.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == linner){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = linner
//                    linner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    linner.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            dinner.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = dinner
//                    clicked = true
//                    dinner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    dinner.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == dinner){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = dinner
//                    dinner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    dinner.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            snack.setOnClickListener {
//                if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
//                    idCheck = snack
//                    clicked = true
//                    snack.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    snack.setTextColor(Color.rgb(92,196,133))
//                }
//                else if (idCheck == snack){ // 자기가 클릭 되있음
//                    clicked = false
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                }
//                else { // 다른거 클릭 되있음
//                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
//                    idCheck!!.setTextColor(Color.rgb(102,102,102))
//                    idCheck = snack
//                    snack.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
//                    snack.setTextColor(Color.rgb(92,196,133))
//                }
//            }
//
//            register.setOnClickListener {
//                mAlertDialog.dismiss()
//                finish()
//            }
//        }
//    }

}