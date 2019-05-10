package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import org.json.JSONObject
import java.lang.Integer.parseInt

class QuizActivity : AppCompatActivity(){
    //Use JSON for the quizData
    private val quizData : JSONObject = JSONObject("""{
        |"Math":{
        |   "NumberOfQuestions" : "4",
        |   "Questions" : [
        |   {
        |      "Question" : "Whats is the square of 16?",
        |      "Answer"   : "4",
        |      "Choices"  :[
        |           "2",
        |           "4",
        |           "6",
        |           "8"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is the 5 times 6?",
        |      "Answer"   : "30",
        |      "Choices"  :[
        |           "18",
        |           "26",
        |           "30",
        |           "35"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is the log 2 of 32?",
        |      "Answer"   : "5",
        |      "Choices"  :[
        |           "2",
        |           "3",
        |           "4",
        |           "5"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is 10 plus 29?",
        |      "Answer"   : "39",
        |      "Choices"  :[
        |           "29",
        |           "39",
        |           "49",
        |           "59"
        |      ]
        |
        |   }]
        |},
        ||"Programming":{
        |   "NumberOfQuestions" : "4",
        |   "Questions" : [
        |   {
        |      "Question" : "Whats is ?",
        |      "Answer"   : "4",
        |      "Choices"  :[
        |           "2",
        |           "4",
        |           "6",
        |           "8"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is the 5 times 6?",
        |      "Answer"   : "30",
        |      "Choices"  :[
        |           "18",
        |           "26",
        |           "30",
        |           "35"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is the log 2 of 32?",
        |      "Answer"   : "5",
        |      "Choices"  :[
        |           "2",
        |           "3",
        |           "4",
        |           "5"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is 10 plus 29?",
        |      "Answer"   : "39",
        |      "Choices"  :[
        |           "29",
        |           "39",
        |           "49",
        |           "59"
        |      ]
        |
        |   }]
        |},
        ||"GoT":{
        |   "NumberOfQuestions" : "4",
        |   "Questions" : [
        |   {
        |      "Question" : "Whats is the square of 16?",
        |      "Answer"   : "4",
        |      "Choices"  :[
        |           "2",
        |           "4",
        |           "6",
        |           "8"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is the 5 times 6?",
        |      "Answer"   : "30",
        |      "Choices"  :[
        |           "18",
        |           "26",
        |           "30",
        |           "35"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is the log 2 of 32?",
        |      "Answer"   : "5",
        |      "Choices"  :[
        |           "2",
        |           "3",
        |           "4",
        |           "5"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is 10 plus 29?",
        |      "Answer"   : "39",
        |      "Choices"  :[
        |           "29",
        |           "39",
        |           "49",
        |           "59"
        |      ]
        |
        |   }]
        |},
        |"Marvel":{
        |   "NumberOfQuestions" : "4",
        |   "Questions" : [
        |   {
        |      "Question" : "Whats is the square of 16?",
        |      "Answer"   : "4",
        |      "Choices"  :[
        |           "2",
        |           "4",
        |           "6",
        |           "8"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is the 5 times 6?",
        |      "Answer"   : "30",
        |      "Choices"  :[
        |           "18",
        |           "26",
        |           "30",
        |           "35"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is the log 2 of 32?",
        |      "Answer"   : "5",
        |      "Choices"  :[
        |           "2",
        |           "3",
        |           "4",
        |           "5"
        |      ]
        |
        |   },
        |   {
        |      "Question" : "Whats is 10 plus 29?",
        |      "Answer"   : "39",
        |      "Choices"  :[
        |           "29",
        |           "39",
        |           "49",
        |           "59"
        |      ]
        |
        |   }]
        |}

    """.trimMargin())
    // Specify the topicname, totalquestion number, correctanswer number, currentquestion index, currentanswer, correctanswer
    private var topic : String = ""
    private var numberCorrect : Int = 0
    private var currentIndex : Int = 0
    private var currentAnswer : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //get the quiz topic value from the intent
        topic = getIntent().getStringExtra("TOPIC")
        // setup the topicNameView and assign the topic name to the view
        val topicNameView: TextView = findViewById(R.id.textViewQuizTopic)
        topicNameView.setText(topic)

        // Parse in the specific question set for the intent name topic
        val questions = quizData.getJSONObject(topic).getJSONArray("Questions")

        //Setup the questionDescription
        val questionView: TextView = findViewById(R.id.textViewQuestionDesc)
        val question = questions.getJSONObject(currentIndex).getJSONObject("Question") as String
        questionView.setText("$question")

        //Setup the RadioGroup for Choices
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

        //Prepare for the output intent, totalquestions, correctanswer, youranswer, result, correctnumber, index
        val intent = Intent(this@QuizActivity,AnswerActivity::class.java)

        intent.putExtra("TOPIC", topic)

        intent.putExtra("YOUR_ANSWER", currentAnswer)

        var correctAnswer = questions.getJSONObject(currentIndex).getJSONObject("Answer") as String
        numberCorrect = getIntent().getIntExtra("NUMBER_CORRECT", 0)
        if(currentAnswer.equals(correctAnswer)){
            numberCorrect ++
            intent.putExtra("RESULT", "Correct!")
        }else{
            intent.putExtra("RESULT", "Incorrect!")
        }
        intent.putExtra("CORRECT_ANSWER", correctAnswer)

        var totalQuestions = quizData.getJSONObject(topic).getJSONObject("NumberOfQuestions") as String
        intent.putExtra("TOTAL_QUESTIONS", totalQuestions)


        intent.putExtra("NUMBER_CORRECT", numberCorrect)


        startActivity(intent)
        }
    }
