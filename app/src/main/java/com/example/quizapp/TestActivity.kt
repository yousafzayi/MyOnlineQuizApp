package com.example.quizapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.Adapters.TestAdapter
import com.example.quizapp.Models.CategoryModel
import com.example.quizapp.Models.TestModel
import com.example.quizapp.databinding.ActivityTestBinding
import java.util.*

class TestActivity : AppCompatActivity(), MyCompleteListener {

    private lateinit var binding: ActivityTestBinding
    private var testView: RecyclerView? = null
    private lateinit var toolbar: Toolbar
    private var testList: List<TestModel>? = null
    private var myCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        toolbar = binding.toolbar
//        this@TestActivity.setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(true)

        val cat_index = getIntent().getIntExtra("cat_index", 0)

            myCategory = intent.getStringExtra("category").toString()

        //Toast.makeText(this, ""+myCategory, Toast.LENGTH_LONG).show()

//CategoryFragment().dataList[cat_index].name
//            supportActionBar?.setTitle(DbQuerry.g_catList.get(cat_index).name)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        testList = ArrayList<TestModel>()
        DbQuerry.loadTestData(this)

        testView =binding.testRecyclerView

        val layoutManager = LinearLayoutManager(this)

        layoutManager.orientation = RecyclerView.VERTICAL
        this.testView?.layoutManager = layoutManager


        testList = DbQuerry.loadTestData(this)

        val adapter = TestAdapter(this, testList, myCategory)
        testView?.adapter = adapter
       // Toast.makeText(this, "List: "+testList, Toast.LENGTH_LONG).show()
//        val adapter = TestAdapter(testList)
//        testView?.adapter = adapter

    }

    override fun onSucess() {


        Toast.makeText(this@TestActivity, "Query Successful", Toast.LENGTH_LONG).show()



    }

    override fun onCategoriesSuccess(categoriesList: List<CategoryModel>) {
        Toast.makeText(this@TestActivity, "Category Successful", Toast.LENGTH_LONG).show()
    }

    override fun onFailure() {
        Toast.makeText(this@TestActivity, "Query Failed", Toast.LENGTH_LONG).show()
    }
    // now we can make this funtion in DbQuerry class and fetch the data from firestore


//  fun loadTestData() {
//
//        (testList as ArrayList<TestModel>).add(TestModel("1", 50, 20))
//        (testList as ArrayList<TestModel>).add(TestModel("2", 60, 15))
//        (testList as ArrayList<TestModel>).add(TestModel("3", 80, 10))
//        (testList as ArrayList<TestModel>).add(TestModel("5", 90, 20))
//
//    }
}