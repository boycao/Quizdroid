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
        |           "Question": "What is square 5?",
        |           "Answer": "25",
        |           "Choices": [
        |               "12",
        |               "16",
        |               "25",
        |               "64"
        |           ]
        |       },
        |       {
        |           "Question": "Which one is a triangle?",
        |           "Answer": "2,2,2",
        |           "Choices": [
        |               "1,2,3",
        |               "2,2,2",
        |               "10,1,1",
        |               "4,5,6"
        |           ]
        |       },
        |       {
        |           "Question": "Which is the elements of 24?",
        |           "Answer": "8",
        |           "Choices": [
        |               "7",
        |               "9",
        |               "13",
        |               "8"
        |           ]
        |       }
        |   ]
        |},
        |"Physics": {
        |   "NumberOfQuestions": "3",
        |   "Questions": [
        |       {
        |           "Question": "Electric current may be expressed in which one of the following units?",
        |           "Answer": "coulombs/second",
        |           "Choices": [
        |               "coulombs/volt",
        |               "joules/coulomb",
        |               "coulombs/second",
        |               "ohms/second"
        |           ]
        |       },
        |       {
        |           "Question": "A Newton is equal to which of the following?",
        |           "Answer": "kilogram-meter per second squared",
        |           "Choices": [
        |               "kilogram-meter per second",
        |               "meter per second squared",
        |               "kilogram-meter per second squared",
        |               "kilogram per meter-second"
        |           ]
        |       },
        |       {
        |           "Question": "For an object moving in uniform circular motion, the direction of the instantaneous acceleration vector is:",
        |           "Answer": "directed radially inward",
        |           "Choices": [
        |               "tangent to the path of motion",
        |               "equal to zero",
        |               "directed radially outward",
        |               "directed radially inward"
        |           ]
        |       }
        |   ]
        |},
        |"Marvel": {
        |   "NumberOfQuestions": "3",
        |   "Questions": [
        |       {
        |           "Question": "Who kills Frigga in 'Thor: The Dark World'?",
        |           "Answer": "Kurse",
        |           "Choices": [
        |               "Kurse",
        |               "Malekith",
        |               "Jane Foster",
        |               "Loki"
        |           ]
        |       },
        |       {
        |           "Question": "What was Sam Wilson before he became Falcon?",
        |           "Answer": "Pararescue",
        |           "Choices": [
        |               "Navy Seal",
        |               "Pararescue",
        |               "Pilot",
        |               "Professional daredevil"
        |           ]
        |       },
        |       {
        |           "Question": "In 'Iron Man 2,' what supposedly bunker-busting smart missile fizzled when War Machine tried to use it?",
        |           "Answer": "Ex-Wife",
        |           "Choices": [
        |               "Sidewinder",
        |               "Jericho Missile",
        |               "Ex-Wife",
        |               "Hulk Buster"
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
