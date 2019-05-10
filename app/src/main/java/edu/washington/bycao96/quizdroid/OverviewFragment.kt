package edu.washington.bycao96.quizdroid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject

class OverviewFragment : Fragment(){
    private val topicData : JSONObject = JSONObject("""
        "Math":{
            "Description" : " This is a Math quiz that helps measure your basic Math knowledge"
            "NumberOfQuestions" : "4"
        },
        "OO Programming":{
            "Description" : " This is a Object-Oriented quiz that measures your knowledge about OO Programming"
            "NumberOfQuestions" : "4"
        },
        "Marvel":{
            "Description" : " How much do you know about Marvel hero?"
            "NumberOfQuestions" : "4"
        },
        "Game of Throne":{
            "Description" : " Are you a real GoT fan?"
            "NumberOfQuestions" : "4"
        }
    """.trimIndent())
    private var topic : String = ""
    private var totalQuestions : Int = 0

    private var listener : beginQuizListener? = null

    interface beginQuizListener{
        fun beginQuiz()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            topic = it.getString(TopicName)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        val topicTitle = view.findViewById<TextView>(R.id.textViewTopicTitle)
        topicTitle.setText("Topic: $topic")

        val desc = topicData.getJSONObject(topic.replace("\\s".toRegex(), "")).get("Description") as String
        val descTextView = view.findViewById<TextView>(R.id.textViewTopicDescription)
        descTextView.setText("Description: \n $desc")

        //Number of questions set up
        val qNum = topicData.getJSONObject(topic.replace("\\s".toRegex(), "")).get("NumberOfQuestions")
        val qNumTextView = view.findViewById<TextView>(R.id.textViewQuestionNumber)
        qNumTextView.setText("The total questions number is $qNum")

        val beginButton = view.findViewById<Button>(R.id.buttonBegin)

        beginButton.setOnClickListener {
            listener?.onBeginClicked(question_count)
        }

        return view

    }



    companion object {
        fun newInstance(topic: String) =
            OverviewFragment().apply {
                arguments = Bundle().apply {
                    putString(TopicName, topic)
                }
            }
    }


}