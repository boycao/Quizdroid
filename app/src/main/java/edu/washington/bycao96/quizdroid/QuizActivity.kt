package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class QuizActivity : AppCompatActivity(), OverviewFragment.overviewListener,
                     QuizFragment.quizListener, AnswerFragment.answerListener{
    private var topic : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //val fragManager: TopicFragment = supportFragmentManager.findFragmentById(R.id.topicFragment) as TopicFragment
        topic = this.intent.getStringExtra("TOPIC")
        var overview = OverviewFragment.newInstance(topic)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, overview, "Overview").commit()
    }

    override fun onBeginQuiz(topic: String) {
        val quiz = QuizFragment.newInstance(topic, 0, 0)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, quiz, "BEGIN QUIZ").commit()
    }

    override fun onSubmitAnswer(topic: String, totalQuestion : Int, questionIndex : Int, currAnswer: String, corrAnswer: String, corrCount: Int) {
        val answer = AnswerFragment.newInstance(topic,totalQuestion,questionIndex,currAnswer,corrAnswer ,corrCount)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, answer, "SUBMIT").commit()
    }


    override fun onContinueQuestion(topic: String,  questionIndex: Int, corrCount: Int) {
        val question = QuizFragment.newInstance(topic, questionIndex, corrCount)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, question, "NEXT QUESTION").commit()
    }



}