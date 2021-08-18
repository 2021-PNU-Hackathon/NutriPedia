package org.techtown.testrecyclerview.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import org.techtown.testrecyclerview.R

class TanDanJiSetting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tan_dan_ji_setting)

        supportActionBar?.setTitle("내 정보 수정하기")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val input1 = findViewById<EditText>(R.id.input1)
        var imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(input1,0)
        input1.requestFocus()

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when(item.itemId){
            R.id.fixActionBtn -> { finish() }}
        return super.onOptionsItemSelected(item)
    }
}