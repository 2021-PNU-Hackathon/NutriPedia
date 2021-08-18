package org.techtown.testrecyclerview.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import org.techtown.testrecyclerview.R

class WaterSetting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_setting)

        supportActionBar?.setTitle("목표 물 섭취량")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_detail, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when(item.itemId){
            R.id.fixActionBtn -> { var fixActionBtn = findViewById<Button>(R.id.fixActionBtn)
                                        fixActionBtn.setOnClickListener {
                                            var intent = Intent(applicationContext,SettingActivity::class.java)
                                            startActivity(intent)
                                    }}

        }
        return super.onOptionsItemSelected(item)
    }

}