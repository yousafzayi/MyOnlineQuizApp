package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityStartTestBinding

class StartTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartTestBinding

    private lateinit var categoryName: TextView
    private lateinit var testNo: TextView
    private lateinit var totalQuestion: TextView
    private lateinit var bestScore: TextView
    private lateinit var time: TextView
    private lateinit var startTestBtn: Button
    private lateinit var backBtn: ImageView
    private var theCategoryName: String? = null
    private var myTestNo:String? = null
    lateinit var myScore:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityStartTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
          Initialization()

        theCategoryName = intent.getStringExtra("categoryName").toString()
        myTestNo = intent.getStringExtra("testNo").toString()
        myScore = intent.getStringExtra("myScore").toString()

       // Toast.makeText(this, "This "+ myScore, Toast.LENGTH_LONG).show()



        if (theCategoryName == null)
        {
            Toast.makeText(this, "Category is null", Toast.LENGTH_LONG).show()
        }else
        {
            binding.startTestCategoryName.setText(theCategoryName)
        }

        if(myTestNo == null)
        {
            Toast.makeText(this, "Test No is null", Toast.LENGTH_LONG).show()

        }else
        {
            binding.startTestNo.setText(myTestNo)
        }

        if(myScore == null)
        {
            Toast.makeText(this, "Score is null", Toast.LENGTH_LONG).show()

        }
        else
        {
            binding.myScore.setText(myScore)
        }


    }


    private fun Initialization() {
        categoryName = binding.startTestCategoryName
        testNo = binding.startTestNo
        totalQuestion = binding.sartTestTotalQuestion
        bestScore = binding.startTestBestScore
        time = binding.startTestTime
        startTestBtn = binding.startTestButton
        backBtn = binding.startTestBackButton
// closing the current activity and moving to back
        backBtn.setOnClickListener {
            this@StartTestActivity.finish()
        }

// by clicking on the start button test will be started
        startTestBtn.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
            finish()

        }


    }


}