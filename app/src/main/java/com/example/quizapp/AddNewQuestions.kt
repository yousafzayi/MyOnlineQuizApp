package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.Adapter.AddNewQuestion
import com.example.quizapp.databinding.ActivityAddNewQuestionsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewQuestions : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewQuestionsBinding
    private lateinit var dbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityAddNewQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbtn.setOnClickListener{

            val intent= Intent(this,QuestionBank::class.java)
            startActivity(intent)
            finish()
        }

        binding.submitQuestion.setOnClickListener {

            val optionA = binding.optionA.text.toString()
            val optionB = binding.optionB.text.toString()
            val optionC = binding.optionC.text.toString()
            val optionD = binding.optionD.text.toString()
            val question = binding.question.text.toString()

            dbRef = FirebaseDatabase.getInstance().getReference("Questions")
            val addNewQuestion = AddNewQuestion(optionA, optionB, optionC, optionD, question)

            dbRef.push().setValue(addNewQuestion).addOnSuccessListener{
                binding.optionA.text.clear()
                binding.optionB.text.clear()
                binding.optionC.text.clear()
                binding.optionD.text.clear()
                binding.question.text.clear()
                Toast.makeText(this, "You have successfully added a question", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this, "Question not sucessfully added ", Toast.LENGTH_SHORT).show()

            }
        }


    }
}

