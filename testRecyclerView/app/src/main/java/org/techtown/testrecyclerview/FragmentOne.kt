package org.techtown.testrecyclerview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.page.*
import kotlinx.android.synthetic.main.search_bar.*
import kotlinx.android.synthetic.main.search_bar.view.*
import org.techtown.testrecyclerview.recommend.RecommendList
import org.techtown.testrecyclerview.recommend.RecommendResult
import org.techtown.testrecyclerview.result.CameraResult
import org.techtown.testrecyclerview.result.FixItemActivity
import org.techtown.testrecyclerview.search.FoodData
import org.techtown.testrecyclerview.search.SearchList
import org.techtown.testrecyclerview.settings.SettingActivity
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.fixedRateTimer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOne : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val REQUEST_IMAGE_CAPTURE = 1 //카메라 사진촬영 요청코드
    lateinit var curPhotoPath: String //문자열 형태의 사진 경로 값(초기값을 null로 시작하고 싶을 때)
    var mealtime = arrayOf("아침","아점","점심","점저","저녁","간식")
    lateinit var dbHelper : DBHelper
    lateinit var db : SQLiteDatabase
    var foodList = arrayListOf<RecordFoodData>()
    val displayList = ArrayList<RecordFoodData>()
    lateinit var v : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_one,container,false)

        dbHelper = DBHelper(context, "food_nutri.db", null, 1)
        db = dbHelper.readableDatabase

        var recyclerView = v.findViewById<RecyclerView>(R.id.recyclerview_main) // recyclerview id

        var now = LocalDate.now()
        var strnow :String = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        fillFoodData(strnow)

        var recommendBtn = v.findViewById<Button>(R.id.recommendBtn)
        var layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

//        var remainTv = v.findViewById<TextView>(R.id.textView12)
        var calTv = v.findViewById<TextView>(R.id.textView14)
        var recommendedKcal : Int = recommendedKcal(
            dbHelper.getColValue(0, "user_info").toInt(),
            dbHelper.getColValue(1, "user_info").toInt(),
            dbHelper.getColValue(4, "user_info").toInt()
        )

        calTv.text = "${recommendedKcal - dbHelper.getKcal(strnow)}Kcal"

        recyclerView.setHasFixedSize(true)
        displayList.addAll(foodList)

        var adapter = MainActivity.MyAdapter(MainActivity.gContext(),displayList)
        recyclerView.adapter = adapter


        val viewAdapter= ViewPagerAdapter()
        val pagerTest = v.findViewById<ViewPager>(R.id.pager)
        pagerTest.adapter = viewAdapter
        val dapter = pagerTest.adapter
        pagerTest.pageMargin = 30

        pagerTest.addOnAdapterChangeListener { viewPager, oldAdapter, newAdapter ->  }
//        pagerTest.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            val act = activity as MainActivity
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//            }
//            override fun onPageSelected(position: Int) {
//                pagerTest.adapter!!.notifyDataSetChanged()
//                pagerTest.invalidate()
//                act.test()
//            }
//
//        })


        var searchView = v.findViewById<View>(R.id.search_bar1)
        var searchTv = searchView.findViewById<TextView>(R.id.search_tv)
        var searchIv = searchView.findViewById<ImageView>(R.id.search_image)
        var settingBtn = searchView.findViewById<ImageView>(R.id.setting)
//        searchTv.auto_tv.setAdapter(arrayAdapter)
//        searchTv.auto_tv.threshold = 0

//        searchIv.setOnClickListener {
//            //if
//            searchTv.auto_tv.setText("")
//        }
        settingBtn.setOnClickListener {
            var settingIntent: Intent = Intent(context, SettingActivity::class.java)
            startActivity(settingIntent)
        }

//        searchTv.auto_tv.setOnItemClickListener { parent, view, position, id ->
//            val setName : String ?= arrayAdapter.getItem(position)
//            searchTv.auto_tv.setText("${setName}")
//        }
        searchTv.setOnClickListener {
            val intent = Intent(context,SearchList::class.java)
//            intent.putExtra("send", auto_tv.text)
            startActivity(intent)
        }

        adapter.setItemClickListner(object : MainActivity.MyAdapter.OnItemClickListner{
            override fun onClick(v: View, position: Int) {
                val cardViewIntent = Intent(context, FixItemActivity::class.java)
                startActivityForResult(cardViewIntent, 123)
            }
        })

        recommendBtn.setOnClickListener {
            var intentRecommend = Intent(context, RecommendList::class.java)
            startActivity(intentRecommend)
        }
        return v
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        var recyclerView = v.findViewById<RecyclerView>(R.id.recyclerview_main) // recyclerview id
        var now = LocalDate.now()
        var strnow :String = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        fillFoodData(strnow)

        var layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        recyclerView.setHasFixedSize(true)
        displayList.addAll(foodList)

        var adapter = MainActivity.MyAdapter(MainActivity.gContext(),displayList)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
        recyclerView.invalidate()

        val viewAdapter= ViewPagerAdapter()
        val pagerTest = v.findViewById<ViewPager>(R.id.pager)
        pagerTest.adapter = viewAdapter
        val dapter = pagerTest.adapter
        pagerTest.pageMargin = 30

        dapter!!.notifyDataSetChanged()
        pagerTest.invalidate()

        super.onResume()
    }


    fun fillFoodData(time: String) {
        for(i in 0..5) {
            Log.d("Log1",time)
            Log.d("Log1",mealtime[i])
            var cursor: Cursor = db.rawQuery("SELECT * FROM record where date = '${time}' and mealtime = '${mealtime[i]}'", null)
            var mealKcal : Int = 0
            var mealCab : Int =0
            var mealPro:Int =0
            var mealFat:Int =0
            var cnt : Int = 0
            var names = arrayListOf<String>()
            while (cursor.moveToNext()) {
                mealKcal += cursor.getString(6).toInt()
                mealCab += cursor.getString(7).toInt()
                mealPro += cursor.getString(8).toInt()
                mealFat += cursor.getString(9).toInt()
                names.add(cursor.getString(2))
                cnt++
            }
            if (cnt>0) {
                Log.d("Log1","good")
                var nameStr: String = ""
                for (i in 0..cnt - 2) {
                    nameStr += names[i]
                    nameStr += ","
                }
                nameStr += names[cnt - 1]
                //            if (nameStr.length >15)
                foodList.add(
                    RecordFoodData(
                        mealtime[i],
                        nameStr,
                        "1",
                        mealKcal,
                        mealCab,
                        mealPro,
                        mealFat
                    )
                )
            }
        }
    }

    fun takeCapture() { //카메라 촬영
        // 기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                val photofile: File? =try {
                    createImageFile()
                } catch(ex: IOException) {
                    null
                }
                photofile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "org.techtown.testrecyclerview.fileprovider", //보안 서명
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    public fun createImageFile(): File { // 이미지파일 생성
        val timeStamp: String = SimpleDateFormat("yyyy-MM-dd-HHmmss").format(Date())
        val storageDir: File? = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_",".jpg",storageDir)
            .apply { curPhotoPath = absolutePath }

    }

    /*
    테드 퍼미션 설정
     */
    public fun setPermission() {
        val permission = object : PermissionListener {
            override fun onPermissionGranted() { // 설정해놓은 권한 들이 허용 되었을 경우
                Toast.makeText(requireContext(), "권한이 허용 되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { // 설정해 놓은 권한들을 거부한 경우
                Toast.makeText(requireContext(), "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        TedPermission.with(requireContext())
            .setPermissionListener(permission)
            .setRationaleMessage("영양피디아를 사용하시려면 권한을 허용해주세요.")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요.")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA)
            .check()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //startActivityForResult를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과값
        super.onActivityResult(requestCode, resultCode, data)
        //사진을 성공적으로 가져 온 경우
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            //val ivPhoto : ImageView = findViewById(R.id.ivPhoto)
            val bitmap: Bitmap
            val file = File(curPhotoPath) // 절대 경로인 사진이 저장된 값
            if (Build.VERSION.SDK_INT < 28) { // 안드로이드9.0(PIE) 버전보다 낮을 경우
                bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, Uri.fromFile(file))
                // 끌어온 비트맵을 넣음
            } else { //PIE버전 이상인 경우
                val decode = ImageDecoder.createSource( //변환을 해서 가져옴
                    requireActivity().contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                //ivPhoto.setImageBitmap(bitmap)
            }
            savePhoto(bitmap)
        }

    }

    public fun savePhoto(bitmap: Bitmap) {
        val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/" //사진 폴더에 저장 경로 선언
        val timeStamp: String = SimpleDateFormat("yyyy-MM-dd-HHmmss").format(Date())
        val fileName = "${timeStamp}.jpeg"
        val folder = File(folderPath)
        if (!folder.isDirectory) { // 현재 해당 경로에 폴더가 존재하지 않는다면
            folder.mkdirs()
        }
        //실제적인 저장처리
        val out = FileOutputStream(folderPath + fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,out)
        Toast.makeText(requireContext(), "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show()
        var cameraIntent = Intent(requireContext(), CameraResult::class.java)
        startActivity(cameraIntent)
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentOne.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                FragmentOne().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}