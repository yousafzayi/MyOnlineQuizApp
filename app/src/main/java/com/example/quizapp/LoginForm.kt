package com.example.quizapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginForm : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityLoginBinding

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
        //binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Action bar
        actionBar = supportActionBar!!
        actionBar.title = "Login"

        //progress dialog configuration
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please wait")
        progressDialog.setMessage("Logging In. . .")
        progressDialog.setCanceledOnTouchOutside(false)

        //initializing firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checKUser()

        //event handler on login click
        binding.signIn.setOnClickListener {
            //before signinig valdate the user data
            validateData()

        }
        //event handler for not having accout click
        binding.register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()


        }


    }

    private fun validateData() {
        //get data from the user that he is registered ? if yes than data will be checked in the database
        email = binding.emailadress.text.toString().trim()
        password = binding.password1.text.toString().trim()

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //Invalid format
            binding.emailadress.error = "Invalid Format"
        } else if (TextUtils.isEmpty(password)) {
            //if password not entered
            binding.password1.error = "please entered password"
        } else {

            //data is validated begin
            fireBaselogin()

        }


    }

    private fun fireBaselogin() {
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //login sucess
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()

                //open profile
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
                    finish()


            }
            .addOnFailureListener { e ->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }

    private fun checKUser() {
        //if user is already login in go to profile activity
        val firebaseuser = firebaseAuth.currentUser
        if (firebaseuser != null) {
            //user is already login
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}