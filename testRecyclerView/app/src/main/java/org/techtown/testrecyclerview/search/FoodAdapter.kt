package org.techtown.testrecyclerview.search

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.testrecyclerview.R

class FoodAdapter (val context: Context, var foodList: ArrayList<FoodData>) :
    RecyclerView.Adapter<FoodAdapter.CustomViewHolder>() {
    private var searchList: ArrayList<FoodData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.bind(foodList[position], context)
        val item = foodList[position]

        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it,position)
        }
        holder.apply {
            bind(item,context)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    interface OnItemClickListner {
        fun onClick(v:View, position: Int)
    }
    private lateinit var itemClickListner: OnItemClickListner

    fun setItemClickListner(itemClickListner: OnItemClickListner) {
        this.itemClickListner = itemClickListner
    }




    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodTitle = itemView.findViewById<TextView>(R.id.titleTv)
        val nutri1 = itemView.findViewById<TextView>(R.id.nutri1)
        val nutri2 = itemView.findViewById<TextView>(R.id.nutri2)
        val nutri3 = itemView.findViewById<TextView>(R.id.nutri3)
        fun bind (foodData:FoodData, context: Context) {
            /* 나머지 TextView와 String 데이터를 연결한다. */
            foodTitle.text = foodData.foodName+"("+foodData.calorie+"kcal"+"/"+foodData.amount+"g"+")"
            nutri1.text = foodData.nutri1.toString()+"g"
            nutri2.text = foodData.nutri2.toString()+"g"
            nutri3.text = foodData.nutri3.toString()+"g"

        }
    }

    /*public fun filter(charSequence : CharSequence){
        var tempArrayList : ArrayList<FoodData> = object
        if (!TextUtils.isEmpty(charSequence)) {
            for (data in searchList!!) {
                if (data.foodName.toLowerCase().contains(charSequence) ) {
                    tempArrayList.add(data)
                }
            }
        }
    }*/
}