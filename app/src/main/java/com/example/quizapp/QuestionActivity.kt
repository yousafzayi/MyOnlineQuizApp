package com.example.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import android.widget.LinearLayout.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class QuestionActivity : AppCompatActivity() {

    private var my_time: TextView? = null
    private var questionNumber: TextView? = null
    private var submitBtn: Button? = null
    private var questionRecycler: RecyclerView? = null
    private var preQuestionBtn: ImageButton? = null
    private var nextQuestionBtn: ImageButton? = null


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        Initialization()
            



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

    private fun Initialization() {
        questionNumber = findViewById(R.id.question_no)
        submitBtn = findViewById(R.id.submit_btn)
        questionRecycler = findViewById(R.id.questionRecycler)
        preQuestionBtn = findViewById(R.id.back_btn)
        nextQuestionBtn = findViewById(R.id.forward_btn)
    }

}