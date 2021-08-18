package org.techtown.testrecyclerview.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import org.techtown.testrecyclerview.R

class SearchResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val registerBtn: Button = findViewById(R.id.registerBtn)
        registerBtn.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()
            mAlertDialog.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            mAlertDialog.window!!.setGravity(Gravity.BOTTOM)
            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val breakfast = mDialogView.findViewById<AppCompatButton>(R.id.breakfast)
            val brunch = mDialogView.findViewById<AppCompatButton>(R.id.brunch)
            val lunch = mDialogView.findViewById<AppCompatButton>(R.id.lunch)
            val linner = mDialogView.findViewById<AppCompatButton>(R.id.linner)
            val dinner = mDialogView.findViewById<AppCompatButton>(R.id.dinner)
            val snack = mDialogView.findViewById<AppCompatButton>(R.id.snack)
            val register = mDialogView.findViewById<AppCompatButton>(R.id.registerBtn)

            var clicked: Boolean = false
            var idCheck: AppCompatButton? = null
            breakfast.setOnClickListener {
                if (idCheck == null || clicked == false) { // 아무 것도 클릭 안되어 있음
                    idCheck = breakfast
                    clicked = true
                    breakfast.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    breakfast.setTextColor(Color.rgb(92, 196, 133))
                } else if (idCheck == breakfast) { // 자기가 클릭 되있음
                    clicked = false
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                } else { // 다른거 클릭 되있음
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                    idCheck = breakfast
                    breakfast.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    breakfast.setTextColor(Color.rgb(92, 196, 133))
                }
            }

            brunch.setOnClickListener {
                if (idCheck == null || clicked == false) { // 아무 것도 클릭 안되어 있음
                    idCheck = brunch
                    clicked = true
                    brunch.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    brunch.setTextColor(Color.rgb(92, 196, 133))
                } else if (idCheck == brunch) { // 자기가 클릭 되있음
                    clicked = false
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                } else { // 다른거 클릭 되있음
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                    idCheck = brunch
                    brunch.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    brunch.setTextColor(Color.rgb(92, 196, 133))
                }
            }

            lunch.setOnClickListener {
                if (idCheck == null || clicked == false) { // 아무 것도 클릭 안되어 있음
                    idCheck = lunch
                    clicked = true
                    lunch.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    lunch.setTextColor(Color.rgb(92, 196, 133))
                } else if (idCheck == lunch) { // 자기가 클릭 되있음
                    clicked = false
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                } else { // 다른거 클릭 되있음
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                    idCheck = lunch
                    lunch.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    lunch.setTextColor(Color.rgb(92, 196, 133))
                }
            }

            linner.setOnClickListener {
                if (idCheck == null || clicked == false) { // 아무 것도 클릭 안되어 있음
                    idCheck = linner
                    clicked = true
                    linner.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    linner.setTextColor(Color.rgb(92, 196, 133))
                } else if (idCheck == linner) { // 자기가 클릭 되있음
                    clicked = false
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                } else { // 다른거 클릭 되있음
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                    idCheck = linner
                    linner.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    linner.setTextColor(Color.rgb(92, 196, 133))
                }
            }

            dinner.setOnClickListener {
                if (idCheck == null || clicked == false) { // 아무 것도 클릭 안되어 있음
                    idCheck = dinner
                    clicked = true
                    dinner.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    dinner.setTextColor(Color.rgb(92, 196, 133))
                } else if (idCheck == dinner) { // 자기가 클릭 되있음
                    clicked = false
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                } else { // 다른거 클릭 되있음
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                    idCheck = dinner
                    dinner.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    dinner.setTextColor(Color.rgb(92, 196, 133))
                }
            }

            snack.setOnClickListener {
                if (idCheck == null || clicked == false) { // 아무 것도 클릭 안되어 있음
                    idCheck = snack
                    clicked = true
                    snack.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    snack.setTextColor(Color.rgb(92, 196, 133))
                } else if (idCheck == snack) { // 자기가 클릭 되있음
                    clicked = false
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                } else { // 다른거 클릭 되있음
                    idCheck!!.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.eat_clicked
                        )
                    )
                    idCheck!!.setTextColor(Color.rgb(102, 102, 102))
                    idCheck = snack
                    snack.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.btn_background
                        )
                    )
                    snack.setTextColor(Color.rgb(92, 196, 133))
                }
            }

            register.setOnClickListener {
                mAlertDialog.dismiss()
                finish()
            }
        }
    }
}