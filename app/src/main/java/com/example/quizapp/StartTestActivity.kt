package com.example.quizapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.DbQuerry.g_catList
import com.example.quizapp.DbQuerry.g_questionList
import com.example.quizapp.DbQuerry.g_selected_cat_Index
import com.example.quizapp.DbQuerry.g_selected_test_Index
import com.example.quizapp.DbQuerry.g_testList
import com.example.quizapp.DbQuerry.loadQuestion
import com.example.quizapp.Models.CategoryModel
import com.example.quizapp.databinding.ActivityStartTestBinding

class StartTestActivity : AppCompatActivity(), MyCompleteListener {
    private lateinit var binding: ActivityStartTestBinding

    private lateinit var progressDialog: ProgressDialog
    private lateinit var dialogTextView: TextView
    private lateinit var categoryName: TextView
    private lateinit var testNo: TextView
    private lateinit var totalQuestion: TextView
    private lateinit var bestScore: TextView
    private lateinit var time: TextView
    private lateinit var startTestBtn: Button
    private lateinit var backBtn: ImageView
    private var theCategoryName: String? = null
    private var myTestNo:String? = null
    lateinit var myScore:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityStartTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Initialization()
        progressDialog = ProgressDialog(this)
        progressDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        progressDialog.setTitle("please wait")
        progressDialog.setMessage("creating Account In. . .")
        progressDialog.setCanceledOnTouchOutside(false)
//loading Question
        loadQuestion(this)

        theCategoryName = intent.getStringExtra("categoryName").toString()
        myTestNo = intent.getStringExtra("testNo").toString()
        myScore = intent.getStringExtra("myScore").toString()

       // Toast.makeText(this, "This "+ myScore, Toast.LENGTH_LONG).show()



        if (theCategoryName == null)
        {
            Toast.makeText(this, "Category is null", Toast.LENGTH_LONG).show()
        }else
        {
            binding.startTestCategoryName.setText(theCategoryName)
        }

        if(myTestNo == null)
        {
            Toast.makeText(this, "Test No is null", Toast.LENGTH_LONG).show()

        }else
        {
            binding.startTestNo.setText(myTestNo)
        }

        if(myScore == null)
        {
            Toast.makeText(this, "Score is null", Toast.LENGTH_LONG).show()

        }
        else
        {
            binding.myScore.setText(myScore)
        }


    }


    private fun Initialization() {
        categoryName = binding.startTestCategoryName
        testNo = binding.startTestNo
        totalQuestion = binding.sartTestTotalQuestion
        bestScore = binding.startTestBestScore
        time = binding.startTestTime
        startTestBtn = binding.startTestButton
        backBtn = binding.startTestBackButton
// closing the current activity and moving to back
        backBtn.setOnClickListener {
            this@StartTestActivity.finish()
        }

// by clicking on the start button test will be started
        startTestBtn.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
            finish()

        }


    }

    override fun onSucess() {
       setData()
        progressDialog.dismiss()
    }

    private fun setData() {
        categoryName.setText(g_catList.get(g_selected_cat_Index).name)

        testNo.setText("Test No" + java.lang. String.valueOf(g_selected_test_Index + 1))

        totalQuestion.setText(java.lang.String.valueOf(g_questionList.size))

        bestScore.setText(java.lang.String.valueOf(g_testList.get(g_selected_test_Index).topScore))

        time.setText(java.lang.String.valueOf(g_testList.get(g_selected_test_Index).time))

    }

    override fun onCategoriesSuccess(categoriesList: List<CategoryModel>) {
        TODO("Not yet implemented")
    }

    override fun onFailure() {
        progressDialog.dismiss()
        Toast.makeText(this, "Something Went Wrong ! please Try Again", Toast.LENGTH_LONG).show()



    }
}