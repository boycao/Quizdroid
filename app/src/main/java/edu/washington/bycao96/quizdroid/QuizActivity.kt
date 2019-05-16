package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Integer.parseInt

class QuizActivity : AppCompatActivity(){

    private  val TAG = "QuizActivity"

    //Use JSON for the quizData
    private  val quizData: JSONObject = JSONObject("""{
        |"Sports": {
        |   "NumberOfQuestions": "3",
        |   "Questions": [
        |       {
        |           "Question": "Which of the following is not an extreme sport?",
        |           "Answer": "Gymnastics",
        |           "Choices": [
        |               "Gymnastics",
        |               "Rock Climbing",
        |               "Skateboarding",
        |               "Dirt Biking"
        |           ]
        |       },
        |       {
        |           "Question": "Which of the following athletes is incorrectly matched with his sport?",
        |           "Answer": "Muhammad Ali - Football",
        |           "Choices": [
        |               "Muhammad Ali - Football",
        |               "Babe Ruth - Baseball",
        |               "Michael Jordan - Basketball",
        |               "Wayne Gretzky - Ice Hockey"
        |           ]
        |       },
        |       {
        |           "Question": "Based on the number of people who play it, what is the most popular sport in the world?",
        |           "Answer": "Soccer",
        |           "Choices": [
        |               "Soccer",
        |               "Golf",
        |               "Basketball",
        |               "Bowling"
        |           ]
        |       }
        |   ]
        |},
        |"Basketball": {
        |   "NumberOfQuestions": "3",
        |   "Questions": [
        |       {
        |           "Question": "Where do you go, if you get fouled in the act of shooting?",
        |           "Answer": "Free throw line",
        |           "Choices": [
        |               "The Bench",
        |               "Three point line",
        |               "Free throw line",
        |               "Half court line"
        |           ]
        |       },
        |       {
        |           "Question": "The ball-handler takes three steps without dribbling, what violation did he commit?",
        |           "Answer": "Traveling",
        |           "Choices": [
        |               "Double dribble",
        |               "Traveling",
        |               "Goaltending",
        |               "Carrying"
        |           ]
        |       },
        |       {
        |           "Question": "Since the 1955-1956 season, the NBA has given out the Most Valuable Player award. Who won the award for the 2008-2009 NBA season?",
        |           "Answer": "Kobe Bryant",
        |           "Choices": [
        |               "Kobe Bryant",
        |               "Dwight Howard",
        |               "Kevin Garnett",
        |               "Lebron James"
        |           ]
        |       }
        |   ]
        |},
        |"Soccer": {
        |   "NumberOfQuestions": "3",
        |   "Questions": [
        |       {
        |           "Question": "Which club have won the most Serie A titles?",
        |           "Answer": "Juventus",
        |           "Choices": [
        |               "Rome",
        |               "Milan",
        |               "Internazionale",
        |               "Juventus"
        |           ]
        |       },
        |       {
        |           "Question": "Lionel Messi was born in which city?",
        |           "Answer": "Buenos Aires",
        |           "Choices": [
        |               "Rosario",
        |               "Buenos Aires",
        |               "Barcelona",
        |               "Cordoba"
        |           ]
        |       },
        |       {
        |           "Question": "In what year did a Dutch team last win a European club competition?",
        |           "Answer": "2013",
        |           "Choices": [
        |               "1978",
        |               "1997",
        |               "2002",
        |               "2013"
        |           ]
        |       }
        |   ]
        |}
    }""".trimMargin())

    // Specify the topicname, totalquestion number, correctanswer number, currentquestion index, currentanswer, correctanswer
    private var topic : String = ""
    private var numberCorrect : Int = 0
    private var currentIndex : Int = 0
    private var currentAnswer : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //get the quiz topic value from the intent
        topic = this.intent.getStringExtra("TOPIC")
        // setup the topicNameView and assign the topic name to the view
        val topicNameView: TextView = findViewById(R.id.textViewQuizTopic)
        topicNameView.setText(topic)
        // Parse in the specific question set for the intent name topic
        val questions = quizData.getJSONObject(topic.replace("\\s".toRegex(), "")).getJSONArray("Questions")

        //Setup the questionDescription
        val questionView: TextView = findViewById(R.id.textViewQuestionDesc)
        currentIndex = this.intent.getIntExtra("QUESTION_INDEX",0)
        val question = questions.getJSONObject(currentIndex).getString("Question")
        questionView.setText("$question")

        //Setup the RadioGroup for Choices
        //val questions = quizData.getJSONObject(topicName.replace("\\s".toRegex(), "")).getJSONArray("Questions")

        val choiceArray = questions.getJSONObject(currentIndex).getJSONArray("Choices")
        val choice1 : RadioButton = findViewById(R.id.buttonChoice1)
        val choice2 : RadioButton = findViewById(R.id.buttonChoice2)
        val choice3 : RadioButton = findViewById(R.id.buttonChoice3)
        val choice4 : RadioButton = findViewById(R.id.buttonChoice4)
        choice1.setText(choiceArray[0].toString())
        choice2.setText(choiceArray[1].toString())
        choice3.setText(choiceArray[2].toString())
        choice4.setText(choiceArray[3].toString())

        //Setup the checkListener to monitor the user's choice and set continueButton visible
        val choices: RadioGroup = findViewById(R.id.RadioGroupChoices)
        var submitButton : Button = findViewById(R.id.buttonSubmit)
        submitButton.isEnabled = false
        choices.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener({group, checkedId ->
            val checked: RadioButton = findViewById(checkedId)
            currentAnswer = checked.text.toString()
            submitButton.isEnabled = true

        }))

        submitButton.setOnClickListener {
                val intent = Intent(this@QuizActivity, AnswerActivity::class.java)

                intent.putExtra("TOPIC", topic)

                intent.putExtra("YOUR_ANSWER", currentAnswer)

                var correctAnswer: String = questions.getJSONObject(currentIndex).getString("Answer")
                numberCorrect = this.intent.getIntExtra("NUMBER_CORRECT", 0)
                if (currentAnswer.equals(correctAnswer)) {
                    numberCorrect++
                    intent.putExtra("RESULT", "Correct!")
                } else {
                    intent.putExtra("RESULT", "Incorrect!")
                }
            intent.putExtra("QUESTION_INDEX", currentIndex)
                intent.putExtra("CORRECT_ANSWER", correctAnswer)

                var totalQuestions = quizData.getJSONObject(topic).getString("NumberOfQuestions")
                intent.putExtra("TOTAL_QUESTIONS", totalQuestions)


                intent.putExtra("NUMBER_CORRECT", numberCorrect)


                startActivity(intent)

                Log.e(TAG, topic)



        }


        }

    }
