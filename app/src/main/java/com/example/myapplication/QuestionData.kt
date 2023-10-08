package com.example.myapplication

/*
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
*/


import kotlin.random.Random


object QuestionData {
    fun getRandomQuestions(): List<Question> {
        val allQuestions = listOf(
            Question(
                "dSpc6I1jdtv",
                "정성적 예측 기법은 언제 가장 적합한 방법일까요?",
                2,
                listOf(
                    "과거 데이터가 많이 있을 때",
                    "미래 새로운 산업이나 시장에 대한 예측을 할 때",
                    "전문가의 지식이 부족한 경우",
                    "시계열 분석 결과를 보완할 때",
                    "인과모형이 실패한 경우"
                )
            ),
            Question(
                "bO08KIIFojD",
                "시계열 분석의 기본 가정은 무엇인가요?",
                5,
                listOf(
                    "수요와 환경적 요인 사이의 상관관계를 파악한다.",
                    "미래의 수요 패턴이 과거의 수요 패턴과 달라질 것이다.",
                    "전문가의 판단에 기초한다.",
                    "초기 수요예측치를 도출하는 데에 용이하다.",
                    "과거의 수요 패턴이 미래에도 지속될 것이라는 가정에 기초한다."
                )
            ),
            Question(
                "ObIOJM78qwp",
                "시계열 분석에서 이동평균법과 지수평활법은 각각 무엇을 의미하나요?",
                5,
                listOf(
                    "지속적으로 상승하는 수요 패턴에 적합한 방법",
                    "과거 수요 데이터를 직접 이용한 방법",
                    "시계열의 계절성을 반영한 방법",
                    "이동평균법은 지수평활법에 비해 미래 예측력이 뛰어나다.",
                    "이동평균법은 과거 수요 데이터의 평균을 구하는 방법, 지수평활법은 최근 데이터를 더 많이 반영하는 방법"
                )
            ),
            Question(
                "pYUyQGRE6R3",
                "인과모형에서 사용되는 회귀분석은 무엇인가요?",
                3,
                listOf(
                    "경제 여건, 이자율, 소득 등 다양한 환경적 요인에 대한 정보를 제공하는 방법",
                    "초기 수요예측치를 도출하는 방법",
                    "수요와 환경적 요인 사이의 상관관계를 분석하는 방법",
                    "주관적이고 인간의 판단에 의존하는 방법",
                    "과거의 수요 패턴을 기반으로 미래 수요를 예측하는 방법"
                )
            ),
            Question(
                "QGKtVUXlcRp",
                "이동평균법과 지수평활법의 차이점은 무엇인가요?",
                4,
                listOf(
                    "이동평균법은 최근 데이터를 더 많이 반영하는 방법, 지수평활법은 과거 수요 데이터의 평균을 구하는 방법",
                    "이동평균법은 계절성을 반영하여 적용하는 방법, 지수평활법은 계절성을 반영하지 않는 방법",
                    "이동평균법은 비선형적인 수요 패턴에 적합한 방법, 지수평활법은 선형적인 수요 패턴에 적합한 방법",
                    "이동평균법은 과거 수요 데이터의 평균을 구하는 방법, 지수평활법은 최근 데이터를 더 많이 반영하는 방법",
                    "이동평균법과 지수평활법은 거의 차이가 없는 방법"
                )
            )

        )

        val randomQuestions = allQuestions.shuffled().take(5) // 5개의 문제를 랜덤으로 선택
        return randomQuestions
    }
}

fun main() {
    val questions = QuestionData.getRandomQuestions()
    for (question in questions) {
        println("Question: ${question.question}")
        println("Options:")
        for ((index, option) in question.options.withIndex()) {
            println("${index + 1}. $option")
        }
        println("Answer: ${question.correct_answer}\n")
    }
}

