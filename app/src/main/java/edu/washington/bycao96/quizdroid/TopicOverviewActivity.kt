package edu.washington.bycao96.quizdroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Layout
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject


class TopicOverviewActivity : AppCompatActivity(){
    //Declare the global variable
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
    private var topicName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topicoverview)

        //Get the topic name chosen
        topicName = getIntent().getStringExtra("Topic")



    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        val view : layout = findViewById<Layout>(R.layout.activity_topicoverview)
        val topicNameTextView = findViewById<TextView>(R.id.textViewTopicName)
        topicNameTextView.setText("$topicName Overview")


        //Description set up
        val desc = topicData.getJSONObject(topicName.replace("\\s".toRegex(), "")).get("Description") as String
        val descTextView = findViewById<TextView>(R.id.textViewTopicDescription)
        descTextView.setText(desc)

        //Number of questions set up
        val qNum = topicData.getJSONObject(topicName.replace("\\s".toRegex(), "")).get("NumberOfQuestions")
        val gNumTextView = findViewById<TextView>(R.id.textViewQuestionNumber)
        gNumTextView.setText("The total questions number is $qNum")

        val beginButton = findViewById<Button>(R.id.buttonBegin)
        beginButton.setOnClickListener(){
            val intent = Intent(this@TopicOverviewActivity,QuizActivity::class.java)
            intent.putExtra("Topic",topicName)
            startActivity(intent)
        }
        return view
    }

}