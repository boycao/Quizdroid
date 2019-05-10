package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import org.json.JSONObject

class QuizActivity : AppCompatActivity(), OverviewFragment.beginQuizListener,
                     QuizFragment.submitAnswerListener, AnswerFragment.continueQuestionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //Get the intent from MainActivity
        val topicName = intent.getStringExtra("Topic")

    }

    override fun beginQuiz(){}

    override fun submitAnswer(){}

    override fun continueQuestion(){}

}