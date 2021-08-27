package org.techtown.testrecyclerview.settings

import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_private_fix.*
import kotlinx.android.synthetic.main.activity_private_fix.manBtn
import kotlinx.android.synthetic.main.activity_private_fix.womanBtn
import org.techtown.testrecyclerview.DBHelper
import org.techtown.testrecyclerview.R


class PrivateFix : AppCompatActivity() {
    val db = DBHelper(this, "food_nutri.db", null, 1)
    var clicked : Boolean = false
    var idCheck : AppCompatButton?= null
    var gender : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_fix)

        supportActionBar?.title ="내 정보 수정하기"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        manBtn.setOnClickListener {
            if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
                idCheck = manBtn
                clicked = true
                manBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                manBtn.setTextColor(Color.rgb(92,196,133))
            }
            else if (idCheck == manBtn) {
                clicked = false
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
            }
            else { // 다른거 클릭 되있음
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
                idCheck = manBtn
                manBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                manBtn.setTextColor(Color.rgb(92,196,133))
            }
        }

        womanBtn.setOnClickListener {
            if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
                idCheck = womanBtn
                clicked = true
                womanBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                womanBtn.setTextColor(Color.rgb(92,196,133))
            }
            else if (idCheck == womanBtn) {
                clicked = false
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
            }
            else { // 다른거 클릭 되있음
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.eat_clicked))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
                idCheck = womanBtn
                womanBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                womanBtn.setTextColor(Color.rgb(92,196,133))
            }
        }
        if (idCheck == manBtn) {
            gender = 0
        }
        else if(idCheck == womanBtn) {
            gender = 1
        }
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
                var cgender = gender

                db.updateUserInfo("sex", cgender)
                db.updateUserInfo("current_height", cHeight_edit.text.toString().toInt())
                finish() }}
        return super.onOptionsItemSelected(item)
    }
}
