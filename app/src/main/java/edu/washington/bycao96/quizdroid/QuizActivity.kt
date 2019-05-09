package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import org.json.JSONObject

class QuizActivity : AppCompatActivity(){
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

    private val topic = getIntent().getStringExtra("Topic")
    // Parse in the specific question set for the intent name topic
    private val questions = quizData.getJSONObject(topic.replace("\\s".toRegex(), "")).getJSONArray("Questions")
    private var totalQuestions : Int = quizData.getJSONObject(topic.replace("\\s".toRegex(),"")).getJSONObject("NumberOfQuestions")
    private var numberCorrect : Int = getIntent().getIntExtra("NUMBER_CORRECT", 0)
    private val currentIndex : Int = getIntent().getIntExtra("QUESTION_INDEX", 0)
    private var currentAnswer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //Set up the topic name componenet on each questionaire
        val topicNameView: TextView = findViewById(R.id.textViewQuizTopic)
        topicNameView.setText(topic)

        displayQuestion()
        displayChoices()

        Log.i("INDEX", "$currentIndex")

        val options: RadioGroup = findViewById(R.id.RadioGroupChoices)
        options.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener({group, checkedId ->
            val checked: RadioButton = findViewById(checkedId)
            currentAnswer = checked.text.toString()
        }))

    }
        /*
            Display the question element from the jasonarray, and the questionindex
        */
        fun displayQuestion(){
            val questionView: TextView = findViewById(R.id.textViewQuestionDesc)
            val question = questions.getJSONObject(currentIndex).get("Question")
            questionView.setText("$question")
        }
        /*
            Setup the display Choices function, use the question and the corresponding question index to get the choice
            array, and set up the four choices correspondingly
         */
        fun displayChoices(){
            val choices = questions.getJSONObject(currentIndex).getJSONArray("Choices")
            val choice1 : RadioButton = findViewById(R.id.ButtonChoice1)
            val choice2 : RadioButton = findViewById(R.id.ButtonChoice2)
            val choice3 : RadioButton = findViewById(R.id.ButtonChoice3)
            val choice4 : RadioButton = findViewById(R.id.ButtonChoice4)
            choice1.setText(choices[0].toString())
            choice2.setText(choices[1].toString())
            choice3.setText(choices[2].toString())
            choice4.setText(choices[3].toString())
        }

        fun submitToNextQuestion(){
                if(currentAnswer !=null || currentAnswer.length()!=0){
                    val answer = questions.getJSONObject(currentIndex).get("Answer")
                    var isCorrect : Boolean = false;
                    if(currentAnswer.equals(answer)){
                        isCorrect = true;
                        numberCorrect ++
                    }
                    val intent = Intent(this@QuizActivity,AnswerActivity::class.java)
                    if (isCorrect) {
                        intent.putExtra("RESULT", "Correct!")
                    } else {
                        intent.putExtra("RESULT", "Incorrect!")
                    }
                    intent.putExtra("TOTAL_QUESTIONS", totalQuestions)
                    intent.putExtra("TOPIC", topic)

                    intent.putExtra("CORRECT_ANSWER", answer.toString())
                    intent.putExtra("YOUR_ANSWER", currentAnswer)
                    startActivity(intent)
                }


        }
}