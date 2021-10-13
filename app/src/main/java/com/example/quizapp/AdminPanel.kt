package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import com.example.quizapp.databinding.ActivityAdminPanelBinding
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class AdminPanel : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityAdminPanelBinding

    //firebase Authentication
    private lateinit var firebaseAuth: FirebaseAuth
    private var user = ""
    private var password = ""
    private lateinit var usersRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //initializing firebase
        firebaseAuth = FirebaseAuth.getInstance()

        usersRef = FirebaseDatabase.getInstance().getReference().child("admin")


        binding.loginbtn.setOnClickListener {

            //validateAdmin()
            user = binding.email.text.toString()

            password = binding.password.text.toString()

            usersRef.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.exists()) {
                        val name = snapshot.child("name").getValue().toString()
                        val pass = snapshot.child("password").value.toString()


                        Toast.makeText(this@AdminPanel, "" + name + "" + pass, Toast.LENGTH_LONG)
                            .show()

                        if (user.equals(name) && password.equals(pass)) {

                            startActivity(Intent(this@AdminPanel, QuestionBank::class.java))
                            finish()
                        }
                    } else {
                        Toast.makeText(this@AdminPanel,
                            "snapShot doesn't exists",
                            Toast.LENGTH_LONG).show()

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@AdminPanel, "" + error.details, Toast.LENGTH_LONG).show()

                }
            })
        }


    }
}

