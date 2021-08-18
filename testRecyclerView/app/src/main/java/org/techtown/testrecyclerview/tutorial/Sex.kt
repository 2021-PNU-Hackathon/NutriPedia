package org.techtown.testrecyclerview.tutorial

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_sex.*
import org.techtown.testrecyclerview.DBHelper
import org.techtown.testrecyclerview.MainActivity
import org.techtown.testrecyclerview.R

class Sex : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sex)
        val db = DBHelper(this, "food.db", null, 1)


        val sex = findViewById<TextView>(R.id.infoTv)
        val manBtn = findViewById<AppCompatButton>(R.id.manBtn)
        val intentBtn = findViewById<Button>(R.id.intentBtn)
        intentBtn.text = "다음"

        var clicked : Boolean = false
        var idCheck : AppCompatButton?= null
        var gender : Int = 0

        manBtn.setOnClickListener {
            if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
                idCheck = manBtn
                clicked = true
                manBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                manBtn.setTextColor(Color.rgb(92,196,133))
            }
            else if (idCheck == manBtn) {
                clicked = false
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
            }
            else { // 다른거 클릭 되있음
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
                idCheck = manBtn
                manBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                manBtn.setTextColor(Color.rgb(92,196,133))
            }
        }

        womanBtn.setOnClickListener {
            if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
                idCheck = womanBtn
                clicked = true
                womanBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                womanBtn.setTextColor(Color.rgb(92,196,133))
            }
            else if (idCheck == womanBtn) {
                clicked = false
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
            }
            else { // 다른거 클릭 되있음
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
                idCheck = womanBtn
                womanBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                womanBtn.setTextColor(Color.rgb(92,196,133))
            }
        }
        if (idCheck == manBtn) {
            gender = 0
        }
        else if(idCheck == womanBtn) {
            gender = 1
        }

        intentBtn.setOnClickListener {
            db.updateUserInfo("sex", gender)
            db.close()
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
        }




    }
}