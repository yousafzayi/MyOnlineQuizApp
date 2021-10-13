package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.Adapter.QuestionAdapterList
import com.example.quizapp.databinding.FragmentQuizBinding
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class FragmentQuiz : Fragment() {

    private lateinit var binding: FragmentQuizBinding

    private lateinit var dbRef: DatabaseReference

    private lateinit var userArrayList: ArrayList<String>






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {

//Binding in Fragments
        binding = FragmentQuizBinding.inflate(layoutInflater)
        return binding.root



        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        return view
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

                        val question = questionSnapshot.child("question").value
                        Log.wtf("DBRef", "dbRef =>$question")


                        val a = questionSnapshot.child("a").value
                        Log.wtf("DBRef", "dbRef =>$a")

                        val b = questionSnapshot.child("b").value
                        Log.wtf("DBRef", "dbRef =>$b")

                        val c = questionSnapshot.child("c").value
                        Log.wtf("DBRef", "dbRef =>$c")

                        val d = questionSnapshot.child("d").value
                        Log.wtf("DBRef", "dbRef =>$d")

                        val Correct = questionSnapshot.child("Correct").value
                        Log.wtf("DBRef", "dbRef =>$Correct")





                        userArrayList.add("$i: ${question.toString()}")
                        i++
                    }
//                    //userArrayList.add("Sheeraz")
//                    questionAdapterList = QuestionAdapterList(userArrayList)
//                    userRecyclerView.adapter = questionAdapterList
//                    // Log.wtf("arrayList", "Size =>" + userArrayList.get(10))
//                    questionAdapterList.notifyDataSetChanged()
//                    userRecyclerView.adapter = QuestionAdapterList(userArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {

               // Toast.makeText(this@FragmentQuiz , "" + error.details, Toast.LENGTH_LONG).show()
          

            }
        })
    }
}






