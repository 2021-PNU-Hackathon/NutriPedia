package org.techtown.testrecyclerview.settings

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_setting.*
import org.techtown.testrecyclerview.DBHelper
import org.techtown.testrecyclerview.R
import org.techtown.testrecyclerview.nutrientRate
import org.techtown.testrecyclerview.recommendedKcal

class SettingActivity : AppCompatActivity() {
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar?.setTitle("정보 수정")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dbHelper = DBHelper(this, "food_nutri.db", null, 1)
        database = dbHelper.writableDatabase

        tanDanJi.setOnClickListener {
            var tanDanJiIntent = Intent(applicationContext,TanDanJiSetting::class.java)
            startActivityForResult(tanDanJiIntent,10)
        }

        water.setOnClickListener{
            var waterIntent = Intent(applicationContext,WaterSetting::class.java)
            startActivityForResult(waterIntent,10)
        }

        var gender : String
        if (dbHelper.getColValue(3,"user_info")=="0")
            gender = "남자"
        else
            gender = "여자"

        infoTv.text = dbHelper.getColValue(4, "user_info") + "cm | " + gender + " | " + dbHelper.getColValue(2,"user_info") + "세"
        cWeight.text = dbHelper.getColValue(0,"user_info") + "kg"
        tWeight.text = dbHelper.getColValue(1,"user_info") + "kg"
        recokcal.text = recommendedKcal(dbHelper.getColValue(0, "user_info").toInt() ,
            dbHelper.getColValue(1, "user_info").toInt(),
            dbHelper.getColValue(4, "user_info").toInt()).toString() + "kcal"
        twater.text = dbHelper.getColValue(6,"user_info") + "ml"
        var recommendedKcal : Int = recommendedKcal(
            dbHelper.getColValue(0, "user_info").toInt(),
            dbHelper.getColValue(1, "user_info").toInt(),
            dbHelper.getColValue(4, "user_info").toInt()
        )
        var triple : Triple<Int, Int, Int> = nutrientRate(dbHelper.getColValue(0, "user_info").toInt(),
            dbHelper.getColValue(1, "user_info").toInt(),
            recommendedKcal)
        nrate.text = triple.first.toString() + ":" + triple.second.toString() + ":" + triple.third.toString()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.fixBtn) {
            var privateIntent = Intent(applicationContext,PrivateFix::class.java)
            startActivity(privateIntent)
        }
        else{}
        return super.onOptionsItemSelected(item)
    }
}

