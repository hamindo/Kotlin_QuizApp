package com.example.myapplication

object QuestionData {
    fun getQuestion(): ArrayList<Question>{

        val queList: ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "1 + 1 ?",
            "1",
            "2",
            "3",
            "4",
            2
        )
        val q2 = Question(
            2,
            "2 + 1 ?",
            "1",
            "2",
            "3",
            "4",
            3
        )
        val q3 = Question(
            3,
            "5 - 1 ?",
            "1",
            "2",
            "3",
            "4",
            4
        )
        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }
}