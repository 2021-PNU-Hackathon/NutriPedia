package org.techtown.testrecyclerview.calendar

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity


import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.item_schedule.*
import org.techtown.testrecyclerview.FragmentTwo
import org.techtown.testrecyclerview.R
import org.techtown.testrecyclerview.previous.PreviousActivity
import org.techtown.testrecyclerview.settings.SettingActivity
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter(val mainActivity: FragmentTwo) : RecyclerView.Adapter<ViewHolderHelper>() {

    val baseCalendar = BaseCalendar()

    init {
        baseCalendar.initBaseCalendar {
            refreshView(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHelper {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolderHelper(view)
    }

    override fun getItemCount(): Int {
        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: ViewHolderHelper, position: Int) {

        val todayFormat = SimpleDateFormat("dd", Locale.KOREAN)
        var todayDay= todayFormat.format(Calendar.getInstance().time).toInt()

        if (baseCalendar.data[position] == todayDay && mainActivity.displayMon == mainActivity.todayMon && position >= baseCalendar.prevMonthTailOffset
            && position < baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
            holder.tv_date.setTextColor(Color.parseColor("#FFFFFF"))
            holder.tv_date.setBackgroundColor(Color.parseColor("#1075C0"))
            holder.dateBox.setBackgroundColor(Color.parseColor("#1075C0"))
        }
        else holder.tv_date.setTextColor(Color.parseColor("#000000"))

        if (position < baseCalendar.prevMonthTailOffset
            || position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
            holder.tv_date.alpha = 0.3f
        } else {
            holder.tv_date.alpha = 1f
        }
        holder.tv_date.text = baseCalendar.data[position].toString()

        if (baseCalendar.data[position] == todayDay && mainActivity.displayMon == mainActivity.todayMon && position >= baseCalendar.prevMonthTailOffset
            && position < baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) holder.tv_test.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
//        else if (baseCalendar.data[position] > todayDay ) holder.tv_test.setCardBackgroundColor(Color.parseColor("#1075C0"))
        else if (baseCalendar.data[position] % 2 == 0 ) holder.tv_test.setCardBackgroundColor(Color.parseColor("#1075C0"))
        else holder.tv_test.setCardBackgroundColor(Color.parseColor("#DF4943"))

        //  오늘 기준으로 오늘보다 나중이면 빨강 오늘은 하양 오늘보다 앞이면 파랑
        //  근데 나중에는 오늘보다 이후면 백그라운드 색 이번달 오늘만 하양 그담에 성공이면 파랑 실패 빨강
//        if (baseCalendar.data[position] < todayDay ) holder.tv_test.setCardBackgroundColor(Color.parseColor("#1075C0"))
//        else if (baseCalendar.data[position] == todayDay ) holder.tv_test.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
//        else holder.tv_test.setCardBackgroundColor(Color.parseColor("#DF4943"))

        holder.dateBox.setOnClickListener {
            var month : String
            var day : String
            if (baseCalendar.data[position] < 10) day = "0"+baseCalendar.data[position].toString()
            else day = baseCalendar.data[position].toString()

            var previousIntent: Intent = Intent( mainActivity.context , PreviousActivity::class.java)
            previousIntent.putExtra("년", mainActivity.displayYear)
            var box : Int
            if (position < baseCalendar.prevMonthTailOffset) {
                box = mainActivity.displayMon-1
            }
            else if(position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
                box = mainActivity.displayMon + 1
            }
            else {
                box = mainActivity.displayMon
            }
            if (box < 10) month = "0"+box.toString()
            else month = box.toString()
            previousIntent.putExtra("월",month )
            previousIntent.putExtra("일", day)
            mainActivity.startActivity(previousIntent)

        }
    }

    fun changeToPrevMonth() {
        baseCalendar.changeToPrevMonth {
            refreshView(it)
        }
    }

    fun changeToNextMonth() {
        baseCalendar.changeToNextMonth {
            refreshView(it)
        }
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        mainActivity.refreshCurrentMonth(calendar)
    }
}





