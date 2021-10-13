package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityQuestionsBinding

class Questions : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionsBinding
    companion object {
        var allJoined: ArrayList<JoinedFeed> = ArrayList()
        var selectedAnswers: ArrayList<String> = ArrayList()
        var questionNr: Int = 0
        var isCorrect: Int = 0
        var isFailed: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val endpoint:String="https://opentdb.com/api.php?amount=10&difficulty=easy&type=multiple"
       // val questions:ArrayList<String>=ArrayList()

        val questions: ArrayList<String> = ArrayList();
        val allanswers: ArrayList<ArrayList<String>> = ArrayList();
        val allcorrectanswers: ArrayList<String> = ArrayList();
        val donelayout: ConstraintLayout = findViewById(R.id.done);
        donelayout.visibility = View.GONE
        supportActionBar?.hide();
        binding.imageView2.setOnClickListener{
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
            finish()

        }




    }
}