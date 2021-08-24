package org.techtown.testrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.techtown.testrecyclerview.result.FoodResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase) {
        var sql1: String = "CREATE TABLE if not exists user_info (" +
                "current_weight integer," +
                "target_weight integer," +
                "age integer," +
                "sex integer," +
                "current_height integer," +
                "idx integer," +
                "target_water integer," +
                "first_weight integer" +
                ");"


        var sql2: String = "CREATE TABLE if not exists record (" +
                "date DATE," +
                "mealtime TEXT," +
                "foodname TEXT," +
                "imgPath TEXT," +
                "photoGuide TEXT," +
                "amount INT," +
                "kcal DOUBLE," +
                "cab DOUBLE," +
                "pro DOUBLE," +
                "fat DOUBLE" +
                ");"

        var sql3: String = "CREATE TABLE if not exists water (" +
                "date DATE," +
                "amount INT" +
                ");"

        var sql4 : String = "CREATE TABLE if not exists success (" +
                "date DATE," +
                "issuccess INT" +
                ");"

        var sql5 : String = "CREATE TABLE if not exists change (" +
                "date DATE," +
                "weight INT" +
                ");"

        db.execSQL(sql1)
        db.execSQL(sql2)
        db.execSQL(sql3)
        db.execSQL(sql4)
        db.execSQL(sql5)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql1: String = "DROP TABLE if exists user_info"
        val sql2: String = "DROP TABLE if exists record"
        val sql3: String = "DROP TABLE if exists water"
        db.execSQL(sql1)
        db.execSQL(sql2)
        db.execSQL(sql3)
        onCreate(db)
    }

    fun isEmpty(tablename : String): Boolean {
        var db: SQLiteDatabase = writableDatabase
        var query ="SELECT * FROM "+tablename+" where date = (SELECT date('now','localtime'))"
        var cursor: Cursor = db.rawQuery(query, null)
        var cnt = 0
        var ret = true
        while(cursor.moveToNext()) {
            cnt++
        }

        cursor.close()
        db.close()
        if (cnt > 0) ret = false
        return ret
    }

    fun insertChange() {
        var db: SQLiteDatabase = writableDatabase
        var query = "INSERT INTO change VALUES ((SELECT date('now','localtime')), 0);"
        db.execSQL(query)
    }

    fun insertUserInfo() {
        var db: SQLiteDatabase = writableDatabase
        var query = "INSERT INTO user_info VALUES ('0', '0', '0', '0', '0', '0', 2000,'0');"
        db.execSQL(query)
    }

    fun insertRecord() {
        var db: SQLiteDatabase = writableDatabase
        var query =
            "INSERT INTO record VALUES ((SELECT date('now','localtime')), NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0);"
        db.execSQL(query)
    }

    fun insertFoodRecord(_mealtime: String?, _foodname: String) {
        var db: SQLiteDatabase = writableDatabase
        var query =
            "INSERT INTO record VALUES ((SELECT date('now','localtime')), _mealtime, _foodname, NULL, NULL, 0, 0, 0, 0, 0);"
        db.execSQL(query)
    }

    fun insertWater() {
        var db: SQLiteDatabase = writableDatabase
        var query = "INSERT INTO water VALUES ((SELECT date('now','localtime')), 0);"
        db.execSQL(query)
    }

    fun insertSuccess(time : String, value: Int) {
        var db: SQLiteDatabase = writableDatabase


        var query = "INSERT INTO success VALUES ('${time}',${value});"
        Log.d("check",query)
        db.execSQL(query)
    }

    fun updateChange( value: Int) {
        var db: SQLiteDatabase = writableDatabase

        db.execSQL(
            "UPDATE change SET weight = " + value  + " WHERE date = (SELECT date('now','localtime'));"
        )

        db.close()
    }

    fun updateUserInfo(field: String, value: Int) {
        var db: SQLiteDatabase = writableDatabase

        db.execSQL(
            "UPDATE user_info SET " + field + "= " + value + " WHERE idx = " + 0 + ";"
        )

        db.close()
    }

    fun updatewater(
        field: String, value: Int, date: String
    ) {
        var db: SQLiteDatabase = writableDatabase

        db.execSQL(
            "UPDATE water SET " + field + " = " + value + " WHERE date = '" + date + "';"
        )

        db.close()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWater(): Int {
        var now = LocalDate.now()
        var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        var db: SQLiteDatabase = readableDatabase

        val query = "SELECT * FROM water"
        var cursor: Cursor = db.rawQuery(query, null)
        var returnvalue = 0
        var exist = 0

        while (cursor.moveToNext()) {
            if (cursor.getString(0) == Strnow) {
                returnvalue = cursor.getString(1).toInt()
                exist = 1
            }
        }

        if (exist == 0) {
            insertWater()
            var cursor1: Cursor = db.rawQuery(query, null)
            while (cursor1.moveToNext()) {
                if (cursor1.getString(0) == Strnow) {
                    returnvalue = cursor1.getString(1).toInt()
                    exist = 1
                }
            }
            cursor1.close()
        }

        cursor.close()
        db.close()
        return returnvalue
    }

    fun getPreWeight(date: String): Int {
        var db: SQLiteDatabase = readableDatabase
        val query = "SELECT * FROM change where date <= '${date}' ORDER by date desc"
        var cursor: Cursor = db.rawQuery(query, null)
        var returnvalue :Int = 0

        while(cursor.moveToNext()) {
            returnvalue += cursor.getString(1).toInt()
            break
        }

        cursor.close()
        db.close()
        return returnvalue
    }

    fun getSuccess(year:String, month:String) :Int {
        var db: SQLiteDatabase = readableDatabase
        val query = "SELECT * FROM success where date BETWEEN '${year}-${month}-01' AND '${year}-${month}-31'"
        var cursor: Cursor = db.rawQuery(query, null)
        var returnvalue :Int = 0

        while(cursor.moveToNext()) {
            if (cursor.getInt(1) == 1) returnvalue++
        }

        cursor.close()
        db.close()
        return returnvalue
    }

    fun getKcal(date: String): Int {
        var db: SQLiteDatabase = readableDatabase
        val query = "SELECT * FROM record where date = '${date}'"
        var cursor: Cursor = db.rawQuery(query, null)
        var returnvalue :Int = 0

        while(cursor.moveToNext()) {
            returnvalue += cursor.getString(6).toInt()
        }

        cursor.close()
        db.close()
        return returnvalue
    }

    fun getColValue(colindex: Int, tablename: String): String {
        var db: SQLiteDatabase = readableDatabase
        val query = "SELECT * FROM " + tablename
        var cursor: Cursor = db.rawQuery(query, null)
        var returnvalue = ""

        while (cursor.moveToNext()) {
            returnvalue = cursor.getString(colindex)
        }

        cursor.close()
        db.close()
        return returnvalue
    }

    /////////// test
    fun getColValueTest(columnIndex: Int, colindex: Int, tablename: String): String {
        var db: SQLiteDatabase = readableDatabase
        val query = "SELECT * FROM " + tablename
        var cursor: Cursor = db.rawQuery(query, null)
        var returnvalue = ""

//        while(cursor.moveToNext()) {
//            returnvalue = cursor.getString(colindex)
//        }

        for (i in 0..columnIndex) {
            cursor.moveToNext()
            returnvalue = cursor.getString(colindex)
        }
        cursor.close()
        db.close()
        return returnvalue
    }

    // 좋아요 기능(START)
    // name은 해당하는 음식의 이름
    fun updatePriorityUp(name: String) {
        var db: SQLiteDatabase = writableDatabase
        var query = "SELECT * FROM real_nutri_91"
        var cursor: Cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (name == cursor.getString(1)) {
                val value = cursor.getString(6).toInt() + 1
                db.execSQL(
                    "UPDATE real_nutri_91 SET " + "priority" + " = " + value + " WHERE name = '" + name + "';"
                )
                break
            }
        }
        cursor.close()
        db.close()
    }
    // 좋아요 기능(FINISH)

    // 싫어요 기능(START)
    fun updatePriorityDown(name: String) {
        var db: SQLiteDatabase = writableDatabase
        var query = "SELECT * FROM real_nutri_91"
        var cursor: Cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (name == cursor.getString(1)) {
                val value = cursor.getString(6).toInt() - 1
                db.execSQL(
                    "UPDATE real_nutri_91 SET " + "priority" + " = " + value + " WHERE name = '" + name + "';"
                )
                break
            }
        }
        cursor.close()
        db.close()
    }
    // 싫어요 기능(FINISH)

    fun getFoodInfo(name: String): FoodResult {
        var db: SQLiteDatabase = readableDatabase
        var query = "SELECT * FROM real_nutri_91"
        var cursor: Cursor = db.rawQuery(query, null)
        var retoutput = FoodResult("name", 0, 0, 0, 0, null, true)
        while (cursor.moveToNext()) {
            if (name == cursor.getString(1)) {
                retoutput = FoodResult(
                    cursor.getString(1),
                    cursor.getString(2).toInt(),
                    cursor.getString(3).toInt(),
                    cursor.getString(4).toInt(),
                    cursor.getString(5).toInt(),
                    null,
                    true
                )
            }
        }
        cursor.close()
        db.close()
        return retoutput
    }
}