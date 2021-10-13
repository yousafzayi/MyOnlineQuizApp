 package com.example.quizapp

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity




class QuestionActivity : AppCompatActivity() {

    private  var my_time: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        my_time = findViewById(R.id.my_time)


        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val minutes: Long = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000 % 60)

                my_time!!.setText(seconds.toString())
                //my_time!!.setText("Mins:"+minutes.toString()+" Sec:"+seconds.toString())
            }

            override fun onFinish() {

                Toast.makeText(this@QuestionActivity, "Time Over", Toast.LENGTH_LONG).show()

            }
        }
        timer.start()

    }

}