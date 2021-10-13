package com.example.quizapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.*
import com.example.quizapp.databinding.ActivityQuestionListBinding

class QuestionAdapterList(private val questionList: ArrayList<String>) :
    RecyclerView.Adapter<QuestionAdapterList.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_questions_list_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentQuestion = questionList[position]


        holder.tvQuestionNumber.text = currentQuestion

    }


    override fun getItemCount(): Int {
        return questionList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestionNumber: TextView = itemView.findViewById(R.id.tvQuestionNumber)}
    }



