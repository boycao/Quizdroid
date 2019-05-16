package edu.washington.bycao96.quizdroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.lang.ClassCastException
import java.lang.RuntimeException

private const val CURRENT_ANSWER = "param1"
private const val CORRECT_ANSWER = "param2"
private const val CORRECT_COUNT = "param3"
private const val TOTAL_QUESTION = "param4"
private const val QUESTION_INDEX = "param5"
private const val TOPIC = "param6"

class AnswerFragment : Fragment(){

    private var topic: String = ""
    private var totalQuestions: Int = 0
    private var questionIndex: Int = 0
    private var currAnswer: String = ""
    private var corrAnswer: String = ""
    private var corrCount : Int = 0

    var listener: answerListener ? = null

    interface answerListener{
        fun onContinueQuestion(topic: String, questionIndex: Int, corrCount: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            topic = it.getString(TOPIC)
            totalQuestions = it.getInt(TOTAL_QUESTION)
            questionIndex = it.getInt(QUESTION_INDEX)
            currAnswer = it.getString(CURRENT_ANSWER)
            corrAnswer = it.getString(CORRECT_ANSWER)
            corrCount = it.getInt(CORRECT_COUNT)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? answerListener
        if(listener == null) {
            throw RuntimeException("$context must implement answerListener")

        }
    }
    //construct the layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //inflate the layout
        val view = inflater.inflate(R.layout.fragment_answer,container, false)
        //render components
        var resultTextView : TextView = view.findViewById(R.id.textViewResult)
        if(currAnswer===corrAnswer){
            corrCount++
            resultTextView.setText("correct!")
        } else{
            resultTextView.setText("Incorrect!")
        }

        var currAnswerTextView : TextView = view.findViewById(R.id.textViewYourAnswer)
        currAnswerTextView.setText("Your Answer is $currAnswer")

        var corrAnswerTextView : TextView = view.findViewById(R.id.textViewCorrectAnswer)
        corrAnswerTextView.setText("Correct Answer is $corrAnswer")

        var answerCorrTextView : TextView = view.findViewById(R.id.textViewCorrectAnswer)
        answerCorrTextView.setText("You have $corrCount right")

        var questionLeftTextView:TextView = view.findViewById(R.id.textViewQuestionsLeft)
        questionLeftTextView.setText("Progress: $questionIndex / $totalQuestions")


        //Setup the continueButton display status and next step
        var continueButton : Button = view.findViewById(R.id.buttonContinue)
        if(questionIndex==totalQuestions-1){
            continueButton.setText("Finish")
        }
        continueButton.setOnClickListener{
            if(continueButton.text === "Finish"){
                startActivity(Intent(activity, MainActivity()::class.java))
            } else {
                (activity as answerListener).onContinueQuestion(topic, questionIndex, corrCount)
            }
        }

        return view
    }
    //Pass the variable
    companion object {
        @JvmStatic
        fun newInstance(topic : String, totalQuestion : Int , questionIndex : Int,  currAnswer : String,
                        corrAnswer: String, corrCount: Int) = AnswerFragment().apply {
            arguments = Bundle().apply {
                putString(TOPIC, topic)
                putInt(TOTAL_QUESTION, totalQuestion)
                putInt(QUESTION_INDEX,questionIndex)
                putString(CURRENT_ANSWER,currAnswer)
                putString(CORRECT_ANSWER,corrAnswer)
                putInt(CORRECT_COUNT,corrCount)
            }
        }
    }

}