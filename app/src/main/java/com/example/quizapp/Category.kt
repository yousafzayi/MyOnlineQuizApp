package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityCategoryBinding
import com.example.quizapp.databinding.ActivityMainBinding

class Category : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        //setContentView(R.layout.activity_category)
        binding = ActivityCategoryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            val intent: Intent = Intent(this, Questions::class.java)
            startActivity(intent)
            finish()
        }
        binding.imageView4.setOnClickListener{
            val intent: Intent = Intent(this, Stats::class.java)
            startActivity(intent)
            finish()


        }
    }
}