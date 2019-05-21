package edu.washington.bycao96.quizdroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Layout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject


class TopicOverviewActivity : AppCompatActivity(){
    //Declare the global variable
    private  val TAG = "TopicActivity"
    private val topicData : JSONObject = JSONObject("""{
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
    }""".trimIndent())
    private var topicName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topicoverview)

        //Get the topic name chosen
        val topic = getIntent().getStringExtra("TOPIC")
        val topicNameTextView = findViewById<TextView>(R.id.textViewTopicName)
        topicNameTextView.setText("$topic Overview")
         topicName= topic;

        //Description set up
        val desc = topicData.getJSONObject(topic.replace("\\s".toRegex(), "")).get("Description") as String
        val descTextView = findViewById<TextView>(R.id.textViewTopicDescription)
        descTextView.setText("Description: $desc")

        //Number of questions set up
        val qNum = topicData.getJSONObject(topic.replace("\\s".toRegex(), "")).get("NumberOfQuestions") as String
        val gNumTextView = findViewById<TextView>(R.id.textViewQuestionNumber)
        gNumTextView.setText("The total question number is $qNum")

        val beginButton = findViewById<Button>(R.id.buttonBegin)

        beginButton.setOnClickListener(){
            val intent = Intent(this@TopicOverviewActivity,QuizActivity::class.java)
            intent.putExtra("TOPIC",topicName)
            startActivity(intent)

        }
        Log.e(TAG,topicName)
    }

}