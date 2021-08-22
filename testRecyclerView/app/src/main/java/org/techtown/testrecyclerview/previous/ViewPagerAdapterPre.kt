package org.techtown.testrecyclerview.previous

import android.content.Context
import android.renderscript.Sampler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import org.techtown.testrecyclerview.R


class ViewPagerAdapterPre: PagerAdapter() {
    private var mContext: Context?=null

    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }

    //position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = if (position == 0) { LayoutInflater.from(container.context).inflate(R.layout.page,container,false) }
                else { LayoutInflater.from(container.context).inflate(R.layout.pagewater_pre,container,false) }
        val calPb = view.findViewById<ProgressBar>(R.id.calPb)
        val tanPb = view.findViewById<ProgressBar>(R.id.tanPb)
        val danPb = view.findViewById<ProgressBar>(R.id.danPb)
        val giPb = view.findViewById<ProgressBar>(R.id.giPb)
        val value = 30 * position

//        calPb.progress = value

//        tanPb.progress = value

//        danPb.progress = value

//        giPb.progress = value

        container.addView(view)
        return view
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return 2
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}