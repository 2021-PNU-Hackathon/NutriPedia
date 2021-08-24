package org.techtown.testrecyclerview.result

import android.net.Uri

data class FoodResult(var foodName: String, var calorie: Double, var amount: Int,
                      var nutri1: Double, var nutri2: Double, var nutri3: Double, var uri: Uri?,val check : Boolean)
