package org.techtown.testrecyclerview.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.testrecyclerview.R
import android.content.Intent
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class SearchList : AppCompatActivity() {

    var foodList = arrayListOf<FoodData>()
    lateinit var recyclerView : RecyclerView
    val displayList = ArrayList<FoodData>()
    val mAdapter = FoodAdapter(this,displayList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_list)

        recyclerView = findViewById(R.id.mRecyclerView)
        fillData()

        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.setHasFixedSize(true)
        displayList.addAll(foodList)

        mAdapter.setItemClickListner(object : FoodAdapter.OnItemClickListner{
            override fun onClick(v: View, position: Int) {
                val intent = Intent(applicationContext,SearchResult::class.java)
                startActivityForResult(intent,101)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu!!.findItem(R.id.action_search)

        if(menuItem != null) {
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        foodList.forEach {
                            if (it.foodName.toLowerCase(Locale.getDefault()).contains(search)) {
                                displayList.add(it)
                            }
                        }
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    else {
                        displayList.clear()
                        displayList.addAll(foodList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }

                    return true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }


}