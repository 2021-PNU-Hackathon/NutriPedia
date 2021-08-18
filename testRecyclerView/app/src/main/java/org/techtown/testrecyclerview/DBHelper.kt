package org.techtown.testrecyclerview

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    version: Int
) : SQLiteOpenHelper(context, name, null, version) {


    override fun onCreate(db: SQLiteDatabase) {
        var sql : String = "CREATE TABLE if not exists user_info (" +
                "current_weight integer," +
                "target_weight integer," +
                "age integer," +
                "sex integer," +
                "current_height integer);";

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql : String = "DROP TABLE if exists mytable"

        db.execSQL(sql)
        onCreate(db)
    }

    fun insertUserInfo(db: SQLiteDatabase) {
        var query = "INSERT INTO user_info('current_weight','target_weight','age','sex','current_height') values(0,0,0,0,0);"
        db.execSQL(query)
    }


}