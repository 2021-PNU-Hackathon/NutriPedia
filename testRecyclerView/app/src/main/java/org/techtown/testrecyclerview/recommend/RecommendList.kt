package org.techtown.testrecyclerview.recommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.testrecyclerview.R
import org.techtown.testrecyclerview.search.FoodAdapter
import org.techtown.testrecyclerview.search.FoodData

class RecommendList : AppCompatActivity() {
    var foodList = arrayListOf<FoodData>()
    lateinit var recyclerView : RecyclerView
    val mAdapter = RecommendFoodAdapter(this,foodList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_list)

        recyclerView = findViewById(R.id.mRecyclerView)
        fillData()
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        mAdapter.setItemClickListner(object : RecommendFoodAdapter.OnItemClickListner {
            override fun onClick(v: View, position: Int) {
                val intent = Intent(applicationContext, RecommendResult::class.java)
                startActivityForResult(intent, 101)
            }

        })
    }
        private fun fillData() {
            foodList.add(FoodData("gimchi",1000,100,100,100,100))
            foodList.add(FoodData("bob",1000,100,100,100,100))
            foodList.add(FoodData("banana",1000,100,100,100,100))
            foodList.add(FoodData("salad",1000,100,100,100,100))
            foodList.add(FoodData("bulgogi",1000,100,100,100,100))
            foodList.add(FoodData("chicken",1000,100,100,100,100))
            foodList.add(FoodData("pizza",2000,100,100,100,100))
            foodList.add(FoodData("bread",1000,100,100,100,100))
            foodList.add(FoodData("meat",1000,100,100,100,100))
        }
}