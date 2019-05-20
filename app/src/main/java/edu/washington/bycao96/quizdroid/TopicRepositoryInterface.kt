package edu.washington.bycao96.quizdroid

import org.json.JSONArray

interface TopicRepositoryInterface{
    fun getData(quizData : JSONArray): List<Topic>
}