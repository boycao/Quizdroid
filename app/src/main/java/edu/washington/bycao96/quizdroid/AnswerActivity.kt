package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity(){
    private val topic = getIntent().getStringExtra("TOPIC")
    private val numberCorrect = getIntent().getIntExtra("NUMBER_CORRECT", 0)
    private val numberOfQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0)
    private var questionIndex = getIntent().getIntExtra("QUESTION_INDEX", 0)
    private val result = getIntent().getStringExtra("RESULT")
    private val correctAnswer = getIntent().getStringExtra("CORRECT_ANSWER")
    private val yourAnswer = getIntent().getStringExtra("YOUR_ANSWER")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val resultView: TextView = findViewById(R.id.textViewResult)
        resultView.setText(result)

        val yourAnswerView: TextView = findViewById(R.id.textViewYourAnswer)
        yourAnswerView.setText("Your answer: $yourAnswer")

        val correctAnswerView: TextView = findViewById(R.id.textViewCorrectAnswer)
        correctAnswerView.setText("Correct Answer: $correctAnswer")

        val numberCorrectView: TextView = findViewById(R.id.textViewNumberCorrect)
        numberCorrectView.setText("You have $numberCorrect out of $numberOfQuestions correct.")

        val progressView: TextView = findViewById(R.id.textViewQuestionsLeft)
        progressView.setText("Your progress: $questionIndex / $numberOfQuestions")

        val nextBtn: Button = findViewById(R.id.buttonNextQuestion)

        if (questionIndex == numberOfQuestions - 1) {
            nextBtn.setText("Finish")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            questionIndex += 1
            nextBtn.setText("Continue")
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("TOPIC", topic)
            intent.putExtra("NUMBER_CORRECT", numberCorrect)
            intent.putExtra("QUESTION_INDEX", questionIndex)
            startActivity(intent)
        }
    }
}