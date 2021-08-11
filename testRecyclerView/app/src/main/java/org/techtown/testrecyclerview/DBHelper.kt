package org.techtown.testrecyclerview

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase) {
        var sql : String = "CREATE TABLE if not exists user_info (" +
                "current_weight integer," +
                "target_weight integer," +
                "age integer," +
                "sex integer," +
                "current_height integer," +
                "idx integer);";

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql : String = "DROP TABLE if exists user_info"
        db.execSQL(sql)
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

    fun updateUserInfo(field: String, value: Int
    ) {
        var db: SQLiteDatabase = writableDatabase

        db.execSQL(
            "UPDATE user_info SET " + field + "= " + value  + " WHERE idx = " + 0 + ";"
        )

        db.close()
    }

    fun selectUserInfo(field: String) {
        var db: SQLiteDatabase = writableDatabase
        db.execSQL(
            "SELECT user_info." + field + " from user_info WHERE idx = " + 0 + ";"
        )
    }


}