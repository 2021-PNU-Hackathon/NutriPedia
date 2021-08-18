package org.techtown.testrecyclerview.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.techtown.testrecyclerview.R

class SearchResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val registerBtn : Button = findViewById(R.id.button)
        registerBtn.setOnClickListener {

            finish()
        }
    }
}