package com.example.quizapp.Adapter

data class Question1(var question: String? = null) {

}

data class AddNewQuestion(
    val A: String? = null,
    val B: String? = null, val C: String? = null,
    val D: String? = null, val question: String? = null,
)

data class GetQuestions(var question : String?= null, var A : String?= null,
                        var B : String?= null, var C : String?= null,
                        var D : String?= null, var Correct  : String?= null)

