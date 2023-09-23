package com.example.myapplication

data class Question(
    var id: Int,
    var question: String,
    var option_one: String,
    var option_two: String,
    var option_three: String,
    var option_four: String,
    var correct_answer: Int
)
//Quiz 클래스 Question.kt
// id와 question(퀴즈 문제 담는 변수), option one~four(답변1~4 담는 변수), correct_answer(정답 번호 담는 변수)