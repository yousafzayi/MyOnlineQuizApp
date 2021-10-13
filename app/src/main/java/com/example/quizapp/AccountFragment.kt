package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizapp.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth


class AccountFragment : Fragment() {

    //firebase Authentication
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }


        return (binding.root)
    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) {
            //user not null , user is logged in get user info
            val email = firebaseUser.email

            binding.email1.text = email

        }
        else
        {
            //user is null , user is not logged in , goto login activity
            val intent = Intent(activity, LoginForm::class.java)
            startActivity(intent)



        }
    }
}


