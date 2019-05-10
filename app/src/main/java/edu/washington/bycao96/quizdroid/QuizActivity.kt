package edu.washington.bycao96.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class QuizActivity : AppCompatActivity(), OverviewFragment.beginQuizListener,
                     QuizFragment.submitAnswerListener, AnswerFragment.continueQuestionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_quiz)
        //Get the intent from MainActivity
        val topicName = intent.getStringExtra("Topic")

    }

    override fun beginQuiz(){}

    override fun submitAnswer(){}

    override fun continueQuestion(){}

}