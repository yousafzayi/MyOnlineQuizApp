package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityQuestionBankBinding

class QuestionBank : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBankBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.backbtn2.setOnClickListener{

            val intent= Intent(this,AdminPanel::class.java)
            startActivity(intent)
            finish()
        }

        binding.btn1.setOnClickListener {
            val intent= Intent(this,QuestionList::class.java)
            startActivity(intent)
            finish()
        }

        binding.btn2.setOnClickListener {
            val intent= Intent(this,AddNewQuestions::class.java)
            startActivity(intent)
            finish()

        }


    }
}