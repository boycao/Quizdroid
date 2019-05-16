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
import java.lang.RuntimeException

    private const val  TOPIC = "param1"

class OverviewFragment : Fragment(){


    private val topicData: JSONObject = JSONObject("""{
        "Math": {
            "Description": "This quiz tests your math ability.",
            "NumberOfQuestions": "3"
        },
        "Physics": {
            "Description": "This is a physics quiz",
            "NumberOfQuestions": "3"
        },
        "Marvel": {
            "Description" : "How much do you know about marvel heroes.",
            "NumberOfQuestions": "3"
        }
    }""")
    private var topic : String = ""
    //private var totalQuestion : Int = 0

    private var listener : overviewListener? = null

    interface overviewListener{
        fun onBeginQuiz(topic: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            topic = it.getString(TOPIC)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        val topicTitle = view.findViewById<TextView>(R.id.textViewTopicTitle)
        topicTitle.setText("Topic: $topic")

        val desc = topicData.getJSONObject(topic.replace("\\s".toRegex(), "")).getString("Description")
        val descTextView = view.findViewById<TextView>(R.id.textViewTopicDescription)
        descTextView.setText("Description: \n $desc")

        //Number of questions set up
        val qNum = topicData.getJSONObject(topic.replace("\\s".toRegex(), "")).getString("NumberOfQuestions")
        val qNumTextView = view.findViewById<TextView>(R.id.textViewQuestionNumber)
        qNumTextView.setText("The total questions number is $qNum")

        val beginButton = view.findViewById<Button>(R.id.buttonBegin)

        beginButton.setOnClickListener {
            listener?.onBeginQuiz(topic)
        }

        return view

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? overviewListener
        if(listener==null){
            throw RuntimeException("$context must implement overviewListener")
        }
    }


    companion object {
        fun newInstance(topic: String) =
            OverviewFragment().apply {
                arguments = Bundle().apply {
                    putString(TOPIC, topic)
                }
            }
    }


}