package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMainBinding
import org.w3c.dom.Text

//MainActivity.kt 코드

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var currentPosition: Int = 1   //현재 몇 번째 문제인지를 담을 변수
    private var selectedOption: Int = 0   //선택 답변 값을 담을 변수
    private var score: Int = 0           //점수를 담을 변수
    private lateinit var questionList: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //선택한 난이도를 가져오기
        val selectedLevel = intent.getIntExtra("selectedLevel", 0)
        // 화면 설정
        getQuestionData(selectedLevel)
        binding.option1Text.setOnClickListener(this)
        binding.option2Text.setOnClickListener(this)
        binding.option3Text.setOnClickListener(this)
        binding.option4Text.setOnClickListener(this)
        binding.option5Text.setOnClickListener(this)
        // 답변 체크 이벤트
        binding.submitBtn.setOnClickListener {
            if (selectedOption != 0) {
                val question = questionList[currentPosition - 1]
                // 정답 체크(선택 답변과 정답을 비교)
                if (selectedOption != question.correct_answer) {
                    // 오답 처리
                    setColor(selectedOption, R.drawable.wrong_option_background)
                    callDialog("오답", "정답 ${question.correct_answer}")
                } else {
                    score++
                }
                setColor(question.correct_answer, R.drawable.correct_option_background)

                if (currentPosition == questionList.size) {
                    binding.submitBtn.text = getString(R.string.submit, "끝")
                } else {
                    binding.submitBtn.text = getString(R.string.submit, "다음")
                }
            } else {
                // 위치값 상승
                currentPosition++
                when {
                    // 전체 문제 숫자가 현재 위치보다 크면 다음 문제로 설정
                    currentPosition <= questionList.size -> {
                        // 다음 문제 설정
                        getQuestionData(selectedLevel)
                    }

                    else -> {
                        // 결과 액티비티로 이동
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        intent.putExtra("score", score)
                        intent.putExtra("totalSize", questionList.size)
                        startActivity(intent)
                        //finish()
                    }
                }
            }
            // 선택값 초기화
            selectedOption = 0
        }
    }
    /**
     * 답변 배경색상 변경
     */
    private fun setColor(opt: Int, color: Int){
        when(opt){
            1 -> binding.option1Text.background = ContextCompat.getDrawable(this, color)
            2 -> binding.option2Text.background = ContextCompat.getDrawable(this, color)
            3 -> binding.option3Text.background = ContextCompat.getDrawable(this, color)
            4 -> binding.option4Text.background = ContextCompat.getDrawable(this, color)
            5 -> binding.option5Text.background = ContextCompat.getDrawable(this, color)

        }
    }

    /**
     * 정답 확인 다이얼로그
     */
    private fun callDialog(alertTitle: String, correctAnswer: String){
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("정답: $correctAnswer")
            .setPositiveButton("OK"){
                    dialogInterface, i ->
                dialogInterface.dismiss() //창 닫기
            }
        
            //오답 알림창 삭제
            //.setCancelable(false)
            //.show()
    }

    /**
     * 문제 셋팅
     */
    private fun getQuestionData(selectedLevel: Int){

        //답변 설정 초기화
        setOptionStyle()

        questionList = when (selectedLevel) {
            1 -> QuestionData1.getRandomQuestions()
            2 -> QuestionData2.getRandomQuestions()
            3 -> QuestionData3.getRandomQuestions()
            else -> emptyList() // 선택한 난이도에 해당하는 데이터가 없는 경우 빈 리스트 반환
        }



        //질문 변수에 담기
        val question = questionList[currentPosition-1]
        //담은 데이터의 첫번쨰 값을 question이라는 변수에 넣음

        //상태바 위치
        binding.progressBar.progress = currentPosition
        //binding 객체를 통해 progressBar에 바로 접근 가능.
        //progressBar 위치를 currentPosition으로 설정.

        //상태바 최대값
        binding.progressBar.max = questionList.size
        //progressBar의 최대값을 리스트 값의 사이즈로 설정.

        //현재 위치 표시
        binding.progressText.text = getString(R.string.count_label, currentPosition, questionList.size)
        //progressText에는 string.xml에 추가한 count_label을 가져와서 currentPostion과 questionList의 사이즈를 넣음.
        //currentPosition, questionList.size 두 값이 각각 count_label %d/%d에 들어가게 됨.eiwiew
        //질문 표시
        binding.questionText.text = question.question
        //questionText에는 question값을 넣음

        //답변 표시
        binding.option1Text.text = question.options[0]
        binding.option2Text.text = question.options[1]
        binding.option3Text.text = question.options[2]
        binding.option4Text.text = question.options[3]
        binding.option5Text.text = question.options[4]
        //각 답변 1~5의 텍스트뷰에 question의 옵션 1~5의 값이 들어감.

        setSubmitBtn("제출")
    }



    //제출 버튼 텍스트 설정
    private fun setSubmitBtn(name: String){

        binding.submitBtn.text = getString(R.string.submit, name)
        //여기서도 getstring으로 submit을 가져와서 name값에 넣어줌.
        // 이 값이 중복으로 들어가기 떄문에 함수로 만들어줌.
    }

    /**
     *  답변 스타일 설정
     */
    private fun setOptionStyle(){

        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(binding.option1Text)
        optionList.add(binding.option2Text)
        optionList.add(binding.option3Text)
        optionList.add(binding.option4Text)
        optionList.add(binding.option5Text)

        //답변 텍스트뷰 설정
        for(op in optionList){
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(this, R.drawable.option_background)
            op.typeface = Typeface.DEFAULT

        }
    }

    /**
     * 답변 선택 이벤트
     */
    private fun selectedOptionStyle(view: TextView, opt: Int){

        setOptionStyle()
        //옵션 초기화


        //위치 담기
        selectedOption = opt

        view.setTextColor((Color.parseColor("#5F00FF")))
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option_background)
        view.typeface = Typeface.DEFAULT_BOLD
    }

    //클릭 이벤트 재정의
    override fun onClick(view: View) {
        when(view.id){
            R.id.option1_text -> selectedOptionStyle(binding.option1Text, 1)
            R.id.option2_text -> selectedOptionStyle(binding.option2Text, 2)
            R.id.option3_text -> selectedOptionStyle(binding.option3Text, 3)
            R.id.option4_text -> selectedOptionStyle(binding.option4Text, 4)
            R.id.option5_text -> selectedOptionStyle(binding.option5Text, 5)
        }
    }}
