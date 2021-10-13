package com.example.quizapp.Models


data class QuestionModel(
    var question: String,
    var optionA: String,
    var optionB: String,
    var optionC: String,
    var optionD: String,
    var correctAnswer: Int,
)