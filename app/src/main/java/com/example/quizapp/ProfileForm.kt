package com.example.quizapp

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class Profile : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityProfileBinding

    //Action bar
    private lateinit var actionBar: ActionBar

    //progressdialog box
    private lateinit var progressDialog: ProgressDialog

    //firebase Authentication
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

//        //configure actionbar
//        actionBar = supportActionBar!!
//        actionBar.title = "profile"

        //initialize firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
//        checkUser()
        //click on logout button
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
//            checkUser()
        }

  //
        binding.startBtn1.setOnClickListener {

            val fragmentTrans=supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fragmetContainer,FragmentQuiz())
            fragmentTrans.commit()





        }


    }

//    private fun checkUser() {
//        //check user is logged in or not
//        val firebaseUser = firebaseAuth.currentUser
//
//        if (firebaseUser != null) {
//            //user not null , user is logged in get user info
//            val email = firebaseUser.email
//            binding.email1.text = email
//
//        }
//
//        else {
//            //user is null , user is not logged in , goto login activity
//            val intent = Intent(this, LoginForm::class.java)
//            startActivity(intent)
//            finish()
//
//        }
//    }
}