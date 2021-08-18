package org.techtown.testrecyclerview.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import org.techtown.testrecyclerview.R

class CurrentWeight : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_weight)


        val currentWeight = findViewById<TextView>(R.id.infoTv)
        currentWeight.text = "현재 몸무게를 입력하세요"
        val intentBtn = findViewById<Button>(R.id.intentBtn)
        intentBtn.text = "다음"

        var weightList: List<Int> = (150 downTo 35).toList()
        var weightStrConvertList = weightList.map { it.toString() }

        val currentNp = findViewById<NumberPicker>(R.id.infoNp)
//        np.minValue = 0
        currentNp.maxValue = weightStrConvertList.size - 1
        currentNp.wrapSelectorWheel = true
        currentNp.displayedValues = weightStrConvertList.toTypedArray()
        currentNp.value = 90


//        fun saveData() {
//            val currentWeightSave = getSharedPreferences()
//        }


        intentBtn.setOnClickListener {

            val intent = Intent( this, TargetWeight::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)

        }

    }


}