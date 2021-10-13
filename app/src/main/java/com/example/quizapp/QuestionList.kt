package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.Adapter.Question1
import com.example.quizapp.Adapter.QuestionAdapterList
import com.example.quizapp.databinding.ActivityQuestionListBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class QuestionList : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionListBinding

    private lateinit var dbRef: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<String>
    private lateinit var questionAdapterList: QuestionAdapterList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRecyclerView = findViewById(R.id.questionList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = mutableListOf<String>() as ArrayList<String>

        questionAdapterList = QuestionAdapterList(userArrayList)
        userRecyclerView.adapter = questionAdapterList

        getUserData()

    }

    private fun getUserData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Questions")

        dbRef.get().addOnSuccessListener {  }
        dbRef.addValueEventListener(object : ValueEventListener {


            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    userArrayList.clear()
                    var i = 1
                    for (questionSnapshot in snapshot.children) {
                        Log.wtf("TAG", questionSnapshot.child("question").value.toString())
                        val question =
                            questionSnapshot.child("question").value
                        Log.wtf("DBRef", "dbRef =>$question")

                        userArrayList.add("$i: ${question.toString()}")
                        i++
                    }
                    //userArrayList.add("Sheeraz")
                    questionAdapterList = QuestionAdapterList(userArrayList)
                    userRecyclerView.adapter = questionAdapterList
                   // Log.wtf("arrayList", "Size =>" + userArrayList.get(10))
                    questionAdapterList.notifyDataSetChanged()
                   userRecyclerView.adapter = QuestionAdapterList(userArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@QuestionList, "" + error.details, Toast.LENGTH_LONG).show()

            }
        })
    }
}