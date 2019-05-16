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
        |"Math": {
        |   "NumberOfQuestions": "3",
        |   "Questions": [
        |       {
        |           "Question": "What is 2 + 2?",
        |           "Answer": "4",
        |           "Choices": [
        |               "2",
        |               "8",
        |               "4",
        |               "13"
        |           ]
        |       },
        |       {
        |           "Question": "What is 5 * 4?",
        |           "Answer": "20",
        |           "Choices": [
        |               "9",
        |               "25",
        |               "1",
        |               "20"
        |           ]
        |       },
        |       {
        |           "Question": "What is 14 - 7?",
        |           "Answer": "7",
        |           "Choices": [
        |               "7",
        |               "21",
        |               "100",
        |               "4"
        |           ]
        |       }
        |   ]
        |},
        |"Code": {
        |   "NumberOfQuestions": "3",
        |   "Questions": [
        |       {
        |           "Question": "What is light?",
        |           "Answer": "a wave",
        |           "Choices": [
        |               "a particle",
        |               "a wave",
        |               "an energy",
        |               "fire"
        |           ]
        |       },
        |       {
        |           "Question": "How fast does light travel?",
        |           "Answer": "299,792,458 m/s",
        |           "Choices": [
        |               "299,792,458 m/s",
        |               "466,467,938 m/s",
        |               "~1 million mph",
        |               "552,375 mph"
        |           ]
        |       },
        |       {
        |           "Question": "What is the unit of measurement for force?",
        |           "Answer": "Newtons",
        |           "Choices": [
        |               "grams",
        |               "Newtons",
        |               "Moles",
        |               "Kelvin"
        |           ]
        |       }
        |   ]
        |},
        |"Marvel": {
        |   "NumberOfQuestions": "3",
        |   "Questions": [
        |       {
        |           "Question": "Which of these is NOT a Marvel hero?",
        |           "Answer": "Wonder Woman",
        |           "Choices": [
        |               "Wonder Woman",
        |               "Ant Man",
        |               "Spiderman",
        |               "Thor"
        |           ]
        |       },
        |       {
        |           "Question": "What is Captain Marvel's real name?",
        |           "Answer": "Carol Danvers",
        |           "Choices": [
        |               "Mary Jane",
        |               "Katniss Everdeen",
        |               "Susan Johnson",
        |               "Carol Danvers"
        |           ]
        |       },
        |       {
        |           "Question": "What is Thor's weapon?",
        |           "Answer": "a hammer",
        |           "Choices": [
        |               "a hammer",
        |               "a sword",
        |               "a whip",
        |               "nunchucks"
        |           ]
        |       }
        |   ]
        |}
    }""".trimMargin())
    // Specify the topicname, totalquestion number, correctanswer number, currentquestion index, currentanswer, correctanswer

    private var topicName : String = ""
    private var numberCorrect : Int = 0
    private var currentIndex : Int = 0
    private var currentAnswer : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //get the quiz topic value from the intent
        val topic = getIntent().getStringExtra("TOPIC")
        // setup the topicNameView and assign the topic name to the view
        val topicNameView: TextView = findViewById(R.id.textViewQuizTopic)
        topicNameView.setText(topic)
        topicName = topic;
        // Parse in the specific question set for the intent name topic
        val questions = quizData.getJSONObject(topicName.replace("\\s".toRegex(), "")).getJSONArray("Questions")

        //Setup the questionDescription
        val questionView: TextView = findViewById(R.id.textViewQuestionDesc)
        val question = questions.getJSONObject(currentIndex).getString("Question")
        questionView.setText("$question")

        //Setup the RadioGroup for Choices
        //val questions = quizData.getJSONObject(topicName.replace("\\s".toRegex(), "")).getJSONArray("Questions")
        currentIndex = getIntent().getIntExtra("QUESTION_INDEX", 0)
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
        submitButton.isClickable = false
        choices.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener({group, checkedId ->
            val checked: RadioButton = findViewById(checkedId)
            currentAnswer = checked.text.toString()
            submitButton.isClickable = true

        }))

        submitButton.setOnClickListener {
            val intent = Intent(this@QuizActivity,AnswerActivity::class.java)

            intent.putExtra("TOPIC", topicName)

            intent.putExtra("YOUR_ANSWER", currentAnswer)

            var correctAnswer : String = questions.getJSONObject(currentIndex).getString("Answer")
            numberCorrect = getIntent().getIntExtra("NUMBER_CORRECT", 0)
            if(currentAnswer.equals(correctAnswer)){
                numberCorrect ++
                intent.putExtra("RESULT", "Correct!")
            }else{
                intent.putExtra("RESULT", "Incorrect!")
            }

            intent.putExtra("CORRECT_ANSWER", correctAnswer)

            var totalQuestions = quizData.getJSONObject(topicName).getString("NumberOfQuestions")
            intent.putExtra("TOTAL_QUESTIONS", totalQuestions)


            intent.putExtra("NUMBER_CORRECT", numberCorrect)


            startActivity(intent)

            Log.e(TAG,topicName)
        }




        }

    }
