package org.techtown.testrecyclerview.previous

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_one.*
import org.techtown.testrecyclerview.FragmentOne
import org.techtown.testrecyclerview.MainActivity
import org.techtown.testrecyclerview.R
import org.techtown.testrecyclerview.ViewPagerAdapter
import org.techtown.testrecyclerview.recommend.RecommendList
import org.techtown.testrecyclerview.result.FixItemActivity
import org.techtown.testrecyclerview.search.SearchList
import org.techtown.testrecyclerview.settings.SettingActivity

class PreviousActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous)
        supportActionBar!!.hide()

        var intent = getIntent()

        textView12.text = "${intent.getIntExtra("년",0)}년 ${intent.getIntExtra("월",0)}월 ${intent.getIntExtra("일",0)}일"

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview_pre) // recyclerview id

        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        var adapter = MainActivity.MyAdapter(MainActivity.gContext())
        recyclerView.adapter = adapter

        val viewAdapter= ViewPagerAdapterPre()
        val pagerTest = findViewById<ViewPager>(R.id.pager)
        pagerTest.adapter = viewAdapter
        pagerTest.pageMargin = 30




//        adapter.setItemClickListner(object : MainActivity.MyAdapter.OnItemClickListner{
//            override fun onClick(v: View, position: Int) {
//                val cardViewIntent = Intent(context, FixItemActivity::class.java)
//                startActivityForResult(cardViewIntent, 123)
//            }
//        })
//
//        recommendBtn.setOnClickListener {
//            var intentRecommend = Intent(context, RecommendList::class.java)
//            startActivity(intentRecommend)
//        }


    }



}

