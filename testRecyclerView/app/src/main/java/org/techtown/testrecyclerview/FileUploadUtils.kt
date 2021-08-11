package org.techtown.testrecyclerview

import android.graphics.Color
import android.util.Log
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException

public class FileUploadUtils {

    fun send2Server(file : File){
        var requestBody:RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file",file.name, RequestBody.create(MediaType.parse("image/*"),file))
//          .addFormDataPart("image","androidFlask.jpg",RequestBody.create(MediaType.parse("image/*jpg"), byteArray ))
            .build()

        var request = Request.Builder()
            .url("http://49.50.165.171:5000")
            .post(requestBody)
            .build()

        var client : OkHttpClient = OkHttpClient()
        client.newCall(request).enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("TEST1 ", response.body()!!.string())

            }

        })
    }

//    fun receiveFromServer() : ArrayList<ServerData> {
//        val url = "http://49.50.165.171:5000"
//        var arrayList_items : ArrayList<ServerData> = ArrayList()
//        var request = Request.Builder().url(url).build()
//        var client = OkHttpClient()
//        client.newCall(request)
//        client.newCall(request).enqueue(object: Callback {
//            override fun onFailure(call: Call, e: IOException){
//                //에러 메세지 출력
//                if(arrayList_items == null) {
//                    Log.e("starterr", "errstart")
//                }
//            }
//            override fun onResponse(call: Call, response: Response) {
//                //println(response.body()?.string())
//
//                var str_response = response.body()!!.string()
//                //데이터가 제대로 전달 되지 않았을 때 예외처리 코드 필요할듯?
//                //이부분은 좀더 간편한 Gson 라이브러리를 사용하면 좋겠다.
//                //create json object
//                val jsonobj: JSONObject = JSONObject(str_response)
//                //create json array
//                var json_array: JSONArray = jsonobj.getJSONArray("info")
//                var i : Int = 0
//                var size: Int = json_array.length()
//
////                for (i in 0.. size-1){
////                    var json_objdetail: JSONObject = json_array.getJSONObject(i)
////                    if (json_objdetail == null) break
////                    var serverData = ServerData(
////                        json_objdetail.getString("ididid"),
////                        json_objdetail.getInt("foodid"),
////                        json_objdetail.getInt("fid"),
////                        json_objdetail.getInt("foodname"),
////                        json_objdetail.getInt("position")
////                    )
////                    arrayList_items.add(serverData)
////
////                }
//                Log.e("errCheck", str_response)
//
//            }
//        })
//        return arrayList_items
//    }

    //Data model
    data class ServerData(var ididid : String ,var foodid: ArrayList<Int>, var fid: ArrayList<Int>, var foodname: ArrayList<Int>, var position: ArrayList<Int>){

    }
}