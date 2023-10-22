package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityQuestionLevelBinding
import com.example.myapplication.databinding.ActivityResultBinding

class QuestionLevelActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuestionLevelBinding

    // levelselectedOption 변수를 이 클래스 내에서 직접 사용합니다.
    private var levelselectedOption: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.level1Text.setOnClickListener(this)
        binding.level2Text.setOnClickListener(this)
        binding.level3Text.setOnClickListener(this)

        // 선택 버튼 눌렀을 때 메인 화면(문제 창)으로 이동하게 버튼 클릭 이벤트 만들기
        binding.choiceBtn.setOnClickListener {

            when (levelselectedOption) {
                1 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                3 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    // levelselectedOption 변수에 대한 getter 메서드
    fun getLevelSelectedOption(): Int {
        return levelselectedOption
    }

    // 클릭 이벤트 재정의
    override fun onClick(view: View) {
        when (view.id) {
            R.id.level1_text -> selectedOptionStyle(binding.level1Text, 1)
            R.id.level2_text -> selectedOptionStyle(binding.level2Text, 2)
            R.id.level3_text -> selectedOptionStyle(binding.level3Text, 3)
        }
    }


    /**
     * 답변 배경색상 변경
     */
    private fun setColor(opt: Int, color: Int){
        when(opt){
            1 -> binding.level1Text.background = ContextCompat.getDrawable(this, color)
            2 -> binding.level2Text.background = ContextCompat.getDrawable(this, color)
            3 -> binding.level3Text.background = ContextCompat.getDrawable(this, color)
        }
    }

    /**
     *  답변 스타일 설정
     */
    private fun setOptionStyle(){

        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(binding.level1Text)
        optionList.add(binding.level2Text)
        optionList.add(binding.level3Text)

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
        levelselectedOption = opt

        view.setTextColor((Color.parseColor("#5F00FF")))
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option_background)
        view.typeface = Typeface.DEFAULT_BOLD
    }

}





