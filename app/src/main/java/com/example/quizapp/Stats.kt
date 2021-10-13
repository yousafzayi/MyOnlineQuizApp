package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityStatsBinding

class Stats : AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_stats)
        supportActionBar?.hide()
        binding = ActivityStatsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val alstat: ArrayList<StatFeed> =ArrayList();

        alstat.add(StatFeed("Total Score", R.drawable.icon_15_trophy))
        alstat.add(StatFeed("Total Test", R.drawable.group_25))
        alstat.add(StatFeed("Previous Score", R.drawable.iconfinder_12))
        alstat.add(StatFeed("Time Taken", R.drawable.group_24))

        val simpleGrid: GridView
        simpleGrid = binding.simpleGridView
        simpleGrid.adapter = StatAdapter(this, alstat)


        binding.nxtBtn.setOnClickListener {
            val intent: Intent = Intent(this, Category::class.java)
            startActivity(intent)
            finish()
        }

    }


}