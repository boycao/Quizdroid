package edu.washington.bycao96.quizdroid

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_quiz.*
import org.json.JSONObject
import java.lang.RuntimeException

//private const val TOTAL_QUESTION = "param1"
private const val QUESTION_INDEX = "param2"
private const val TOPIC = "param3"
private const val CORRECT_COUNT = "param4"

class QuizFragment : Fragment(){
        private var topic : String = ""
        private var questionIndex : Int = 0
        private var totalQuestion : Int = 0
        private var currAnswer :String = ""
        private var corrAnswer : String = ""
        private var corrCount : Int = 0
        // Build the JSON for quiz questions
        val quizData: JSONObject = JSONObject("""{
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
        |"Physics": {
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

        //set the listener to implemnet the interface
        var listener : quizListener? = null
        //call the answerfragment
        interface quizListener{
            fun onSubmitAnswer(topic: String, totalQuestion : Int, questionIndex: Int ,currAnswer: String, corrAnswer : String, corrCount : Int)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            //Set up the input needed
            arguments?.let {
                topic=it.getString(TOPIC)
                questionIndex=it.getInt(QUESTION_INDEX)
                corrCount = it.getInt(CORRECT_COUNT)
                //totalQuestion=it.getInt(TOTAL_QUESTION)
            }

        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            // construct the view
            val view = inflater.inflate(R.layout.fragment_quiz, container, false)
            //Construct the question view, fetch the JSON data
            val questions = quizData.getJSONObject(topic.replace("\\s".toRegex(), "")).getJSONArray("Questions")
            val questionDescr: TextView = view.findViewById(R.id.questionText)
            val question = questions.getJSONObject(questionIndex).getString("Question")
            questionDescr.setText("$question")
            totalQuestion = quizData.getJSONObject(topic.replace("\\s".toRegex(), "")).getInt("NumberOfQuestions")
            //construct the radiogroup view

            val choices = questions.getJSONObject(questionIndex).getJSONArray("Choices")

            //val choicegroup : RadioGroup = view.findViewById(R.id.radioGroup)

            val choice1 : RadioButton = view.findViewById(R.id.choice1)
            val choice2 : RadioButton = view.findViewById(R.id.choice2)
            val choice3 : RadioButton = view.findViewById(R.id.choice3)
            val choice4 : RadioButton = view.findViewById(R.id.choice4)
            choice1.setText(choices[0].toString())
            choice2.setText(choices[1].toString())
            choice3.setText(choices[2].toString())
            choice4.setText(choices[3].toString())

            // Set up the current Answer
            val radiogroup: RadioGroup = view.findViewById(R.id.radioGroup)
            radiogroup.setOnCheckedChangeListener {_, checkedId ->
                val checked: RadioButton = view.findViewById(checkedId)
                currAnswer = checked.text.toString()
            }

            // Set up the correct Answer
            corrAnswer = questions.getJSONObject(questionIndex).getString("Answer")

            //Set up the submit button
            val submitBtn : Button = view.findViewById(R.id.submitBtn)
            submitBtn.setOnClickListener {
                (activity as QuizFragment.quizListener).onSubmitAnswer(topic,totalQuestion ,questionIndex, currAnswer,corrAnswer,corrCount)
            }

            return view;
        }
        //Handle the exception
        override fun onAttach(context: Context) {
            super.onAttach(context)
            listener = context as? quizListener
            if (listener == null) {
                throw RuntimeException("$context must implement quizListener")
            }
        }

        companion object {
            @JvmStatic
            fun newInstance(topic : String, questionIndex : Int, corrCount: Int) = QuizFragment().apply {
                arguments = Bundle().apply{
                    putString(TOPIC, topic)
                    putInt(QUESTION_INDEX, questionIndex)
                    putInt(CORRECT_COUNT, corrCount)
                }
            }
        }
    }
