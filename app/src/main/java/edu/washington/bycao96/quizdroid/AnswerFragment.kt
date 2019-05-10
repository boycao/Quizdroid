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

class AnswerFragment : Fragment(){

    private var topic: String = ""
    private var totalQuestion: Int = 0
    private var questionIndex: Int = 0
    private var currAnswer: String = ""
    private var corrAnswer: String = ""
    private var corrCount : Int = 0

    var listener: continueQuestionListener ? = null

    interface continueQuestionListener{
        fun continueQuestion()
    }
    //Get the intent value
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            topic = it.getString(TopicName)
            totalQuestion = it.getInt(TotalQuestion)
            questionIndex = it.getInt(QuestionIndex)
            currAnswer = it.getString(CurrAnswer)
            corrAnswer = it.getString(CorrAnswer)
            corrCount = it.getInt(CorrCount)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? continueQuestionListener
        if(listener == null) {
            throw ClassCastException("$context didn't trigger continueQuestionListener")

        }
    }
    //construct the layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        //inflate the layout
        val view = inflater.inflate(R.layout.fragmentAnswer,container, false)
        //render components
        var currAnswerTextView : TextView = view.findViewById(R.id.TextViewCurrAnswer)
        var corrAnswerTextView : TextView = view.findViewById(R.id.TextViewCorrAnswer)



        //Setup the continueButton display status and next step
        var continueButton : Button = view.findViewById(R.id.ButtonContinue)
        if(questionIndex==totalQuestion)
            continueButton.setText("Finish")

        continueButton.setOnClickListener{
            if(questionIndex==totalQuestion)
                startActivity(Intent(activity, MainActivity()::class.java))
            else (activity as continueQuestionListener).continueQuestion()
        }

        return view
    }

    companion object {
        fun newInstance(topic : String, totalQuestion : Int , questionIndex : Int,  currAnswer : String,
                        corrAnswer: String) : AnswerFragment {
            val answerFragment = AnswerFragment()
            answerFragment.arguments = Bundle().apply {
                putString("Topic", topic)
                putInt("TotalQuestions", totalQuestion)
                putInt("QuestionIndex",questionIndex)
                putString("")
            }



        }
    }

}