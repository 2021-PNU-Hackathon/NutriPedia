package org.techtown.testrecyclerview.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_setting.*
import org.techtown.testrecyclerview.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar?.setTitle("정보 수정")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tanDanJi.setOnClickListener {
            var tanDanJiIntent = Intent(applicationContext,TanDanJiSetting::class.java)
            startActivityForResult(tanDanJiIntent,10)
        }

        water.setOnClickListener{
            var waterIntent = Intent(applicationContext,WaterSetting::class.java)
            startActivityForResult(waterIntent,10)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.fixBtn) {
            var privateIntent = Intent(applicationContext,PrivateFix::class.java)
            startActivity(privateIntent)
        }
        else{}
        return super.onOptionsItemSelected(item)
    }
}