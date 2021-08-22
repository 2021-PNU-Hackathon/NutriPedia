package org.techtown.testrecyclerview

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_two.*
import kotlinx.android.synthetic.main.pagewater.view.*
import org.techtown.testrecyclerview.calendar.BaseCalendar
import org.techtown.testrecyclerview.calendar.OnSwipeTouchListener
import org.techtown.testrecyclerview.calendar.RecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTwo.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTwo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var scheduleRecyclerViewAdapter: RecyclerViewAdapter
    lateinit var linelist: ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData

    var displayMon = 0
    var displayYear = 0

    var isset = 0
    var todayMon =0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var current_month : TextView

    lateinit var schedule : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var V2 : View = inflater.inflate(R.layout.fragment_two, container, false)
        current_month = V2.findViewById<TextView>(R.id.tv_current_month)

        schedule = V2.findViewById<RecyclerView>(R.id.rv_schedule)
        var linechart = V2.findViewById<LineChart>(R.id.lineChart)
        initView()

//        schedule.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
//            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//                schedule.setOnTouchListener(object :
//                    OnSwipeTouchListener(requireContext()) {   // 캘린더 날짜 부분 스와이프 리스너
//                    override fun onSwipeLeft() {
//        //                  왼쪽에서 오른쪽으로 스와이프 이전달로
//                        scheduleRecyclerViewAdapter.changeToNextMonth()
//                    }
//
//                    override fun onSwipeRight() {
//        //                  오른쪽에서 왼쪽으로 스와이프 다음달로
//                        scheduleRecyclerViewAdapter.changeToPrevMonth()
//                    }
//                })
//                return onInterceptTouchEvent(rv,e)
//            }
//
//            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//
//            }
//
//            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//
//            }
//
//        })


        linelist = ArrayList()
        linelist.add(Entry(6f, 100f))
        linelist.add(Entry(11f, 300f))
        linelist.add(Entry(16f, 200f))
        linelist.add(Entry(21f, 600f))
        linelist.add(Entry(26f, 500f))
        linelist.add(Entry(31f, 900f))

        lineDataSet = LineDataSet(linelist, "Weight")
        lineData = LineData(lineDataSet)
        linechart.data = lineData


        lineDataSet.color = Color.parseColor("#5CC485")
//        lineDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = 16f
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = Color.parseColor("#DEF3E7")
        lineDataSet.lineWidth = 4f


        linechart.run {
            data = lineData
            description.isEnabled = false // 하단 Description Label 제거함
            invalidate() // refresh
        }

        val maxLine = LimitLine(900f).apply {
            lineWidth = 1.5F
            isEnabled = true
            lineColor = Color.DKGRAY
        }

        val minLine = LimitLine(100f).apply {
            lineWidth = 1.5F
            isEnabled = true
            lineColor = Color.DKGRAY
        }

        val averageLine = LimitLine(500f).apply {
            lineWidth = 1.5F
            isEnabled = true
            lineColor = Color.DKGRAY

//            label =
        }

        linechart.apply {
            setTouchEnabled(false)
        }

        // 범례
        linechart.legend.apply {
            isEnabled = false // 사용하지 않음
        }
        // Y 축
        linechart.axisLeft.apply {
            // 라벨, 축라인, 그리드 사용하지 않음
            setDrawLabels(false)
            setDrawAxisLine(false)
            setDrawGridLines(false)

            // 한계선 추가
            removeAllLimitLines()
            addLimitLine(averageLine)
            addLimitLine(maxLine)
            addLimitLine(minLine)


            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            labelCount = 2

        }
        linechart.axisRight.apply {
            // 우측 Y축은 사용하지 않음
            isEnabled = false
        }
        var yAxis: YAxis = linechart.getAxisLeft()
        yAxis.axisMaximum = 900f
        yAxis.axisMinimum = 100f

        val testToday = 31

        // X 축
        linechart.xAxis.apply {
            // x축 값은 투명으로
            textColor = Color.BLACK
            // 축라인, 그리드 사용하지 않음
            setDrawLabels(false)
            setDrawAxisLine(true)
            setDrawGridLines(false)
            position = XAxis.XAxisPosition.BOTTOM

        }

        return V2
    }

    fun initView() {
        scheduleRecyclerViewAdapter = RecyclerViewAdapter(this)
        schedule.layoutManager = GridLayoutManager(context, BaseCalendar.DAYS_OF_WEEK)
        schedule.adapter = scheduleRecyclerViewAdapter


        schedule.setOnTouchListener(object :
            OnSwipeTouchListener(requireContext()) {   // 캘린더 날짜 부분 스와이프 리스너
            override fun onSwipeLeft() {
//                  왼쪽에서 오른쪽으로 스와이프 이전달로
                scheduleRecyclerViewAdapter.changeToNextMonth()
            }

            override fun onSwipeRight() {
//                  오른쪽에서 왼쪽으로 스와이프 다음달로
                scheduleRecyclerViewAdapter.changeToPrevMonth()
            }
        })

    }

//        fun testText(str: String) {
//            test.text = str
//        }

        fun refreshCurrentMonth(calendar: Calendar) {
            val sdf = SimpleDateFormat("yyyy년 MM월", Locale.KOREAN)
            current_month.text = sdf.format(calendar.time)
            val sdfMon = SimpleDateFormat("MM", Locale.KOREAN)
            displayMon = sdfMon.format(calendar.time).toInt()
            val sdfYear = SimpleDateFormat("yyyy", Locale.KOREAN)
            displayYear = sdfYear.format(calendar.time).toInt()
            if (isset == 0 ) {
                todayMon = displayMon
                isset = 1
            }
//            Tvtest.text = "${displayMon} / ${todayMon}"   //  현재 달 페이지 상 달 테스트 출력
        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentTwo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}