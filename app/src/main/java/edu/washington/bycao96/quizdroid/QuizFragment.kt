package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import org.json.JSONObject

    class QuizFragment : Fragment(){
        private val topic : String = ""
        private val questionIndex : Int = 0
        private var totalQuestions : Int = 0
            // Build the JSON for quiz
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

        //set the listener to default
        var listener : submitAnswerListener? = null

        interface submitAnswerListener{
            fun submitAnswer()
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            //Set up the topic name componenet on each questionaire
            arguments?.let {
                topic=it.getString(TopicName)
                questionIndex=it.getInt(QuestionIndex)
            }

        }
        /*
            val topicNameView: TextView = findViewById(R.id.textViewQuizTopic)
            topicNameView.setText(topic)

            displayQuestion()
            displayChoices()

            Log.i("INDEX", "$currentIndex")

            val options: RadioGroup = findViewById(R.id.RadioGroupChoices)
            options.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener({ group, checkedId ->
                val checked: RadioButton = findViewById(checkedId)
                currentAnswer = checked.text.toString()
            }))
            */
        /*
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
        */
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            //Inflate the layout
            val view = inflater.inflate(R.layout.FragmentQuiz, container, false)
            val questions = JSONObject(topic)
            val questionView: TextView = view.findViewById(R.id.textViewQuestionDesc)
            val question = questions.getJSONObject("questionIndex").get("Question")
            questionView.setText("$question")

            val choices = questions.getJSONObject("questionIndex").getJSONArray("Choices")
            val choice1 : RadioButton = view.findViewById(R.id.ButtonChoice1)
            val choice2 : RadioButton = view.findViewById(R.id.ButtonChoice2)
            val choice3 : RadioButton = view.findViewById(R.id.ButtonChoice3)
            val choice4 : RadioButton = view.findViewById(R.id.ButtonChoice4)

        }


        companion object {
            fun newInstance(topic : String, questionIndex : Int) =
                    QuizFragment().apply {
                        arguments = Bundle().apply{
                            putString(TopicName, topic)
                            putInt(QuestionIndex, questionIndex)
                        }
                    }
        }
    }
