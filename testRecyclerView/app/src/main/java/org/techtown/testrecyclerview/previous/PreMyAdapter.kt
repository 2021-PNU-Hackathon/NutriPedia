package org.techtown.testrecyclerview.previous

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.testrecyclerview.MainActivity
import org.techtown.testrecyclerview.R
import org.techtown.testrecyclerview.RecordFoodData
import java.util.ArrayList

class PreMyAdapter(val context: Context, var foodList: ArrayList<RecordFoodData>): RecyclerView.Adapter<PreMyAdapter.PreMyViewHolder>(){

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): PreMyViewHolder {
        var v: View = LayoutInflater.from(viewgroup.context).inflate(R.layout.pre_card_layout, viewgroup, false)
        return PreMyViewHolder(v)
    }

    override fun onBindViewHolder(holder: PreMyViewHolder, position: Int) {

        val item = foodList[position]
        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it,position)
        }
//        holder.cameraIb.setOnClickListener {
//            var activity : MainActivity = MainActivity.instance!!
//            activity.takeCapture()
//        }

        holder.apply {
            bind(item,context)
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
    interface OnItemClickListner {
        fun onClick(v: View, position: Int)
    }
    private lateinit var itemClickListner: OnItemClickListner

    fun setItemClickListner(itemClickListner: OnItemClickListner) {
        this.itemClickListner = itemClickListner
    }

    class PreMyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var itemimage: ImageView = itemview.findViewById(R.id.item_image)
        var itemtitle: TextView = itemview.findViewById(R.id.item_title)
        var itemdetail: TextView = itemview.findViewById(R.id.item_detail)

        var cardTanTv: TextView = itemview.findViewById(R.id.cardTanTv)
        var cardDanTv: TextView = itemview.findViewById(R.id.cardDanTv)
        var cardJiTv: TextView = itemview.findViewById(R.id.cardJiTv)
        fun bind (foodData: RecordFoodData, context: Context) {
            itemtitle.text = foodData.mealTime +" | "+foodData.calorie.toString()+"Kcal"
            itemdetail.text = foodData.foodName
            itemimage.setImageResource(R.drawable.ic_launcher_foreground)
            cardTanTv.text = "탄 "+foodData.nutri1.toString()+"g"
            cardDanTv.text = "단 "+foodData.nutri2.toString()+"g"
            cardJiTv.text = "지 "+foodData.nutri3.toString()+"g"
        }
    }
}