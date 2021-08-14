package org.techtown.testrecyclerview.settings

import androidx.appcompat.app.AppCompatActivity
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_private_fix.*
import org.techtown.testrecyclerview.DBHelper
import org.techtown.testrecyclerview.R


class PrivateFix : AppCompatActivity() {
    val db = DBHelper(this, "food.db", null, 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_fix)

        supportActionBar?.setTitle("내 정보 수정하기")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when(item.itemId){
            R.id.fixActionBtn -> {
                db.updateUserInfo("current_weight", cWeight_edit.text.toString().toInt())
                db.updateUserInfo("target_weight", tWeight_edit.text.toString().toInt())
                db.updateUserInfo("age", age_edit.text.toString().toInt())
                var cgender = 0
                if (gender_edit.text.toString() == "남자")
                    cgender = 0
                else
                    cgender = 1
                db.updateUserInfo("sex", cgender)
                db.updateUserInfo("current_height", cHeight_edit.text.toString().toInt())
                finish() }}
        return super.onOptionsItemSelected(item)
    }
}
