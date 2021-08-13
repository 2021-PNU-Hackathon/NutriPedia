package org.techtown.testrecyclerview.settings

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_setting.*
import org.techtown.testrecyclerview.DBHelper
import org.techtown.testrecyclerview.R

class SettingActivity : AppCompatActivity() {
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar?.setTitle("정보 수정")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dbHelper = DBHelper(this, "food.db", null, 1)
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
        if (dbHelper.getColValue(3)=="0")
            gender = "남자"
        else
            gender = "여자"

//        var informations = dbHelper.getColValue(4).toString() + "cm | " + gender + " | " + dbHelper.getColValue(2).toString() + "세"

        infoTv.text = dbHelper.getColValue(4) + "cm | " + gender + " | " + dbHelper.getColValue(2) + "세"
        cWeight.text = dbHelper.getColValue(0) + "kg"
        tWeight.text = dbHelper.getColValue(1) + "kg"

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.fixActionBtn) {
//            var privateIntent = Intent(applicationContext,::class.java)
//            startActivityForResult(,13)
        }
        else{}
        return super.onOptionsItemSelected(item)
    }
}

