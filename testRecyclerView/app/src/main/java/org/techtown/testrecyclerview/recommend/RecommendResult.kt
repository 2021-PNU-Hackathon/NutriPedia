package org.techtown.testrecyclerview.recommend

import android.app.Dialog
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_recommend_result.*
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.activity_search_result.kcal
import kotlinx.android.synthetic.main.activity_search_result.nutri1_Tv
import kotlinx.android.synthetic.main.activity_search_result.nutri2_Tv
import kotlinx.android.synthetic.main.activity_search_result.nutri3_Tv
import kotlinx.android.synthetic.main.activity_search_result.some_id
import kotlinx.android.synthetic.main.activity_search_result.total
import kotlinx.android.synthetic.main.custom_dialog.view.*
import org.techtown.testrecyclerview.DBHelper
import org.techtown.testrecyclerview.R
import kotlin.math.roundToInt

class RecommendResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_result)
        var dbHelper = DBHelper(this, "food_nutri.db", null, 1)
        lateinit var db : SQLiteDatabase

        val name = intent.getStringExtra("name")
        var calorie = intent.getIntExtra("calorie", 0)
        var nutri1 = intent.getIntExtra("nutri1", 0)
        var nutri2 = intent.getIntExtra("nutri2", 0)
        var nutri3 = intent.getIntExtra("nutri3", 0)

        some_id.text = name
        kcal.text = calorie.toString() + "Kcal"
        nutri1_Tv.text = nutri1.toString() + "Kcal"
        nutri2_Tv.text = nutri2.toString() + "Kcal"
        nutri3_Tv.text = nutri3.toString() + "Kcal"
        total.text = calorie.toString() + "Kcal"

        var tempList: List<Int> = (50 downTo 5).toList()
        var gramList = ArrayList<Int>()
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
            kcal.text = ((num1*(50-newVal)/10).roundToInt()+(num2*(50-newVal)/10).roundToInt()+(num3*(50-newVal)/10).roundToInt()).toString() +"Kcal"
            total.text = kcal.text

            calorie = ((num1*(50-newVal)/10).roundToInt()+(num2*(50-newVal)/10).roundToInt()+(num3*(50-newVal)/10).roundToInt())
            nutri1 = (num1*(50-newVal)/10).roundToInt()
            nutri2 = (num2*(50-newVal)/10).roundToInt()
            nutri3 = (num3*(50-newVal)/10).roundToInt()
        }

        val registerBtn: Button = findViewById(R.id.button)
        registerBtn.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()
            mAlertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
            mAlertDialog.window!!.setGravity(Gravity.BOTTOM)
            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val breakfast = mDialogView.findViewById<AppCompatButton>(R.id.breakfast)
            val brunch = mDialogView.findViewById<AppCompatButton>(R.id.brunch)
            val lunch = mDialogView.findViewById<AppCompatButton>(R.id.lunch)
            val linner = mDialogView.findViewById<AppCompatButton>(R.id.linner)
            val dinner = mDialogView.findViewById<AppCompatButton>(R.id.dinner)
            val snack = mDialogView.findViewById<AppCompatButton>(R.id.snack)
            val register = mDialogView.findViewById<AppCompatButton>(R.id.registerBtn)

            var clicked : Boolean = false
            var idCheck : AppCompatButton ?= null
            breakfast.setOnClickListener {
                if (idCheck == null || clicked == false){ // ?????? ?????? ?????? ????????? ??????
                    idCheck = breakfast
                    clicked = true
                    breakfast.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    breakfast.setTextColor(Color.rgb(92,196,133))
                }
                else if (idCheck == breakfast){ // ????????? ?????? ?????????
                    clicked = false
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                }
                else { // ????????? ?????? ?????????
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                    idCheck = breakfast
                    breakfast.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    breakfast.setTextColor(Color.rgb(92,196,133))
                }
            }

            brunch.setOnClickListener {
                if (idCheck == null || clicked == false){ // ?????? ?????? ?????? ????????? ??????
                    idCheck = brunch
                    clicked = true
                    brunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    brunch.setTextColor(Color.rgb(92,196,133))
                }
                else if (idCheck == brunch){ // ????????? ?????? ?????????
                    clicked = false
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                }
                else { // ????????? ?????? ?????????
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                    idCheck = brunch
                    brunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    brunch.setTextColor(Color.rgb(92,196,133))
                }
            }

            lunch.setOnClickListener {
                if (idCheck == null || clicked == false){ // ?????? ?????? ?????? ????????? ??????
                    idCheck = lunch
                    clicked = true
                    lunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    lunch.setTextColor(Color.rgb(92,196,133))
                }
                else if (idCheck == lunch){ // ????????? ?????? ?????????
                    clicked = false
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                }
                else { // ????????? ?????? ?????????
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                    idCheck = lunch
                    lunch.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    lunch.setTextColor(Color.rgb(92,196,133))
                }
            }

            linner.setOnClickListener {
                if (idCheck == null || clicked == false){ // ?????? ?????? ?????? ????????? ??????
                    idCheck = linner
                    clicked = true
                    linner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    linner.setTextColor(Color.rgb(92,196,133))
                }
                else if (idCheck == linner){ // ????????? ?????? ?????????
                    clicked = false
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                }
                else { // ????????? ?????? ?????????
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                    idCheck = linner
                    linner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    linner.setTextColor(Color.rgb(92,196,133))
                }
            }

            dinner.setOnClickListener {
                if (idCheck == null || clicked == false){ // ?????? ?????? ?????? ????????? ??????
                    idCheck = dinner
                    clicked = true
                    dinner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    dinner.setTextColor(Color.rgb(92,196,133))
                }
                else if (idCheck == dinner){ // ????????? ?????? ?????????
                    clicked = false
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                }
                else { // ????????? ?????? ?????????
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                    idCheck = dinner
                    dinner.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    dinner.setTextColor(Color.rgb(92,196,133))
                }
            }

            snack.setOnClickListener {
                if (idCheck == null || clicked == false){ // ?????? ?????? ?????? ????????? ??????
                    idCheck = snack
                    clicked = true
                    snack.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    snack.setTextColor(Color.rgb(92,196,133))
                }
                else if (idCheck == snack){ // ????????? ?????? ?????????
                    clicked = false
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                }
                else { // ????????? ?????? ?????????
                    idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                    idCheck!!.setTextColor(Color.rgb(102,102,102))
                    idCheck = snack
                    snack.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                    snack.setTextColor(Color.rgb(92,196,133))
                }
            }

            register.setOnClickListener {
                mAlertDialog.dismiss()
                var mt: String? = null
                mt = when(idCheck) {
                    breakfast -> "??????"
                    brunch -> "??????"
                    lunch -> "??????"
                    linner -> "??????"
                    dinner -> "??????"
                    snack -> "??????"
                    else -> null
                }

                dbHelper.insertFoodRecord1(mt, name, 500-currentvalue*10, calorie, nutri1, nutri2, nutri3)
                finish()
            }


        }
    }
}