package edu.washington.bycao96.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.json.JSONObject

class QuizActivity : AppCompatActivity(){
    val quizData : JSONObject = JSONObject("""{
        |"Math":{
        |   "NumberOfQuestions" : [{
        |   "Question" : "Whats is "
        |   }]
        |}
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
        |
    """.trimMargin())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


    }
}