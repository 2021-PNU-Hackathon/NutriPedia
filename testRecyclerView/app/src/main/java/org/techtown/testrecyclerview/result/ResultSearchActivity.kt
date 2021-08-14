package org.techtown.testrecyclerview.result

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.search_bar.*
import kotlinx.android.synthetic.main.search_bar.view.*
import org.techtown.testrecyclerview.R
import org.techtown.testrecyclerview.settings.SettingActivity

class ResultSearchActivity : AppCompatActivity() {

    val wordList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_search)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, wordList)
        var searchView = findViewById<View>(R.id.result_search_bar)
        var searchTv = searchView.findViewById<TextView>(R.id.auto_tv)
        var searchIv = searchView.findViewById<ImageView>(R.id.search_image)
        var settingBtn = searchView.findViewById<ImageView>(R.id.setting)
        searchTv.auto_tv.setAdapter(arrayAdapter)
        searchTv.auto_tv.threshold = 0
        fillData()

        searchIv.setOnClickListener {
            //if
            searchTv.auto_tv.setText("")
        }
        settingBtn.setOnClickListener {
            var settingIntent: Intent = Intent(this, SettingActivity::class.java)
            startActivity(settingIntent)
        }

        searchTv.auto_tv.setOnItemClickListener { parent, view, position, id ->
            //if
            searchTv.auto_tv.setText("")
        }
        search_send.setOnClickListener {
            //코딩 해야함 ****일치 시 있는 데이터 자료 띄움 없을 시 토스트 메세지 출력
            finish()
        }
    }

    private fun fillData() {
        wordList.add("김치")
        wordList.add("밥")
        wordList.add("국")
        wordList.add("멸치")
        wordList.add("꽁치")
        wordList.add("소고기")
        wordList.add("돼지고기")
        wordList.add("콩나물")
        wordList.add("제육볶음")
        wordList.add("라볶이")
        wordList.add("라면")

    }

}