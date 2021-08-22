package org.techtown.testrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase) {
        var sql1 : String = "CREATE TABLE if not exists user_info (" +
                "current_weight integer," +
                "target_weight integer," +
                "age integer," +
                "sex integer," +
                "current_height integer," +
                "idx integer," +
                "target_water INT" +
                ");"


        var sql2 : String = "CREATE TABLE if not exists record (" +
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

        var sql3 : String = "CREATE TABLE if not exists water (" +
                "date DATE," +
                "amount INT" +
                ");"

        db.execSQL(sql1)
        db.execSQL(sql2)
        db.execSQL(sql3)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql1 : String = "DROP TABLE if exists user_info"
        val sql2 : String = "DROP TABLE if exists record"
        val sql3 : String = "DROP TABLE if exists water"
        db.execSQL(sql1)
        db.execSQL(sql2)
        db.execSQL(sql3)
        onCreate(db)
    }


    fun insertUserInfo() {
        var db: SQLiteDatabase = writableDatabase
        var query = "INSERT INTO user_info VALUES ('0', '0', '0', '0', '0', '0', 2000);"
        db.execSQL(query)
    }

    fun insertRecord() {
        var db: SQLiteDatabase = writableDatabase
        var query = "INSERT INTO record VALUES ((SELECT date('now','localtime')), NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0);"
        db.execSQL(query)
    }

    fun insertWater() {
        var db: SQLiteDatabase = writableDatabase
        var query = "INSERT INTO water VALUES ((SELECT date('now','localtime')), 0);"
        db.execSQL(query)
    }

    fun updateUserInfo(field: String, value: Int
    ) {
        var db: SQLiteDatabase = writableDatabase

        db.execSQL(
            "UPDATE user_info SET " + field + "= " + value  + " WHERE idx = " + 0 + ";"
        )

        db.close()
    }

    fun updatewater(field: String, value: Int, date: String
    ) {
        var db: SQLiteDatabase = writableDatabase

        db.execSQL(
            "UPDATE water SET " + field + " = " + value  + " WHERE date = '" + date + "';"
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

        while(cursor.moveToNext()) {
            if (cursor.getString(0) == Strnow) {
                returnvalue = cursor.getString(1).toInt()
                exist = 1
            }
        }

        if (exist == 0) {
            insertWater()
            var cursor1: Cursor = db.rawQuery(query, null)
            while(cursor1.moveToNext()) {
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

    fun getColValue(colindex: Int, tablename: String): String {
        var db: SQLiteDatabase = readableDatabase
        val query = "SELECT * FROM " + tablename
        var cursor: Cursor = db.rawQuery(query, null)
        var returnvalue = ""

        while(cursor.moveToNext()) {
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


}