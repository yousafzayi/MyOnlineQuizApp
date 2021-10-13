package com.example.quizapp

import com.example.quizapp.Models.CategoryModel

interface MyCompleteListener {
    fun onSucess()
    fun onCategoriesSuccess(categoriesList: List<CategoryModel>)
    fun onFailure()

}
