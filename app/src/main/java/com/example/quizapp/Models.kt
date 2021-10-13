package com.example.quizapp

class StatFeed(
    val name: String,
    val image: Int
)

class ResultFeed(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answer: ArrayList<String>
)

class QuizResults(
    val result: String
)

class AllResult(
    val response_code: Int,
    val result: List<ResultFeed>
)

class JoinedFeed(
    val question: ArrayList<String>,
    val answers: ArrayList<ArrayList<String>>,
    val correct_answer: ArrayList<String>
)
class DoneFeed(
    val qNumbers: String,
    val qCorrectAnswers: String,
    val qAttempted: String,
    val qNegative: String
)