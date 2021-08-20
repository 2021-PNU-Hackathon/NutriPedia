package org.techtown.testrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
                "idx integer);";

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
                ");";

        db.execSQL(sql1)
        db.execSQL(sql2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql1 : String = "DROP TABLE if exists user_info"
        val sql2 : String = "DROP TABLE if exists Record"
        db.execSQL(sql1)
        db.execSQL(sql2)
        onCreate(db)
    }

    /* fun insertUserInfo(db: SQLiteDatabase) {
        var query = "INSERT INTO user_info('current_weight','target_weight','age','sex','current_height','idx') values(0,0,0,0,0,0);"
        db.execSQL(query)
    } */

    fun insertUserInfo() {
        var db: SQLiteDatabase = writableDatabase
        var query = "INSERT INTO user_info VALUES ('0', '0', '0', '0', '0', '0');"
        db.execSQL(query)
    }

    fun insertRecord() {
        var db: SQLiteDatabase = writableDatabase
        var query = "INSERT INTO Record VALUES ((SELECT date('now','localtime')), NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0);"
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

    fun selectUserInfo(field: String, tablename: String) {
        var db: SQLiteDatabase = writableDatabase
        db.execSQL(
            "SELECT user_info." + field + " from " + tablename + " WHERE idx = " + 0 + ";"
        )
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