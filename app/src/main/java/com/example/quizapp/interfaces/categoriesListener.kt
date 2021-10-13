package com.example.quizapp.interfaces

import com.example.quizapp.Models.CategoryModel

interface categoriesListener {
    fun onSuccess(categoriesList: List<CategoryModel>)
    fun onError(message: String)
}