package edu.washington.bycao96.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.json.JSONObject


class TopicOverviewActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topicoverview)
    }

    val topicData : JSONObject = JSONObject("""
        "Math":{
            "Description" : " This is a Math quiz that helps measure your basic Math knowledge"
            "NumberOfQuestions" : "4"
        },
        "OO Programming":{
            "Description" : " This is a Object-Oriented quiz that measures your knowledge about OO Programming"
            "NumberOfQuestions" : "5"
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



}