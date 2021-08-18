package org.techtown.testrecyclerview.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import org.techtown.testrecyclerview.MainActivity
import org.techtown.testrecyclerview.R

class Sex : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sex)



        val sex = findViewById<TextView>(R.id.infoTv)
        sex.text = "성별을 선택하세요"
        val intentBtn = findViewById<Button>(R.id.intentBtn)
        intentBtn.text = "다음"

        var sexRg = findViewById<RadioGroup>(R.id.sexRg)
        var result : String

        sexRg.setOnCheckedChangeListener { RadioGroup, i ->
            when (i) {
                R.id.manRb -> result = "남자"
                R.id.girlRb -> result = "여자"  // R.id.rb2 -> tv1.text = "여자"
            }
        }


        class RadioListener : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) { // p1 사용자가 선택한 라디오 버튼의 아이디값
                when (p0?.id) {
                    R.id.sexRg ->
                        when (p1) {
                            R.id.manRb -> result = "남자"
                            R.id.girlRb -> result = "여자"
                        }
                }
            }
        }


        intentBtn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
        }




    }
}