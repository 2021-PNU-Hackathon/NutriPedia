package org.techtown.testrecyclerview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_setting.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MyBroadcastReceiver : BroadcastReceiver() {
    lateinit var dbHelper : DBHelper
    lateinit var database : SQLiteDatabase
    lateinit var db : SQLiteDatabase

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        dbHelper = DBHelper(context, "food_nutri.db", null, 1)
        database = dbHelper.writableDatabase
//        db = dbHelper.readableDatabase
        if(Intent.ACTION_DATE_CHANGED == intent!!.action) {

            var targetKcal : Int = recommendedKcal(dbHelper.getColValue(0, "user_info").toInt() ,
                dbHelper.getColValue(1, "user_info").toInt(),
                dbHelper.getColValue(4, "user_info").toInt())

            var now = LocalDate.now()
            var yearMont:String = now.format(DateTimeFormatter.ofPattern("yyyy-MM"))
            var day:String = (now.format(DateTimeFormatter.ofPattern("dd")).toInt() -1).toString()
            var date:String = yearMont+"-"+day
            var kcal : Int = dbHelper.getKcal(date)


            dbHelper.insertWater()
            if (kcal == targetKcal) dbHelper.insertSuccess(date,1)
            else dbHelper.insertSuccess(date,0)

        }
    }
}