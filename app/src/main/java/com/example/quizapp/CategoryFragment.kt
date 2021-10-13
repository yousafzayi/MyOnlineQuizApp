package com.example.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.Adapters.CategoryAdapter
import com.example.quizapp.Models.CategoryModel
import com.example.quizapp.databinding.FragmentCategoryBinding
import com.example.quizapp.interfaces.categoriesListener

class CategoryFragment : Fragment(), categoriesListener {


    lateinit var binding: FragmentCategoryBinding


    private lateinit var categoryAdapter: CategoryAdapter
     var dataList = arrayListOf<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DbQuerry.getCategories(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater)


        //Toast.makeText(context, "hello from home", Toast.LENGTH_LONG).show()

        binding.recyclerView.layoutManager = GridLayoutManager(context,2)

        categoryAdapter = CategoryAdapter(inflater.context.applicationContext)


        return (binding.root)

    }

    override fun onSuccess(categoriesList: List<CategoryModel>) {
        for(i in 0..categoriesList.size-1){
            dataList.add(categoriesList[i])
        }

        binding.progressBar.visibility = View.GONE
        categoryAdapter.setDataList(dataList)
        binding.recyclerView.adapter = categoryAdapter
    }

    override fun onError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}