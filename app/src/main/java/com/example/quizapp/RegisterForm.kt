package com.example.quizapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.Models.CategoryModel
import com.example.quizapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity(), MyCompleteListener {


    //binding
    private lateinit var binding: ActivityRegisterBinding

    //Action bar
    private lateinit var actionBar: ActionBar

    //progressdialog box
    private lateinit var progressDialog: ProgressDialog

    //firebase Authentication
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var name =" "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Action bar & enable back button
        actionBar = supportActionBar!!
        actionBar.title = "User Registeration form"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //progress dialog configuration
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please wait")
        progressDialog.setMessage("creating Account In. . .")
        progressDialog.setCanceledOnTouchOutside(false)

        //initialize firebase authentication
        firebaseAuth= FirebaseAuth.getInstance()
        //handle click on register user button
        binding.registerbtn.setOnClickListener {
            //validate data
            validateData()

        }




    }

    private fun validateData() {
        //get data
        email = binding.email.text.toString().trim()
        password=binding.password.text.toString().trim()
       name=binding.name.text.toString().trim()

        //validate data
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            binding.email.error= "invalid format"
//        }
        if(name.isEmpty())
        {
            binding.name.error=("please enter your name")
        }
         if(TextUtils.isEmpty(password)){
            binding.password.error= "Pleae enter password"
        }
        else if (password.length<6){
            binding.password.error="password length must be atleast 6 characters long"
        }
        else {
            //data is valid , continue for registeration
            firebaseRegister()
        }


    }

    private fun firebaseRegister() {
        //show progress
        progressDialog.show()

        // create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Registeration sucessfull
                progressDialog.dismiss()
                //get current user
                val firebaseUser= firebaseAuth.currentUser
                val email= firebaseUser!!.email
                Toast.makeText(this, " Account created with email $email", Toast.LENGTH_SHORT).show()
//querry for fetching user name and email and assigning them total score 0

                if (email != null) {
                    DbQuerry.createUserData(email,name,this)
                }


            }
            .addOnFailureListener { e->
                //Registeration failed
                progressDialog.dismiss()
                Toast.makeText(this, "Registeration failed ${e.message}", Toast.LENGTH_SHORT).show()



            }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()//go back to previous activity when back button is pressed
        return super.onSupportNavigateUp()
    }
//on sucess And on failure for total user count .
    override fun onSucess() {
        //open profile
        progressDialog.dismiss()
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCategoriesSuccess(categoriesList: List<CategoryModel>) {
        TODO("Not yet implemented")
    }

    override fun onFailure() {
     Toast.makeText(this,"Something went wrong try Again",Toast.LENGTH_SHORT).show()
        progressDialog.dismiss()
    }
}