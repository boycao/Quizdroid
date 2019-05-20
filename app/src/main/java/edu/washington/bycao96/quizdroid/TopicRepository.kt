package edu.washington.bycao96.quizdroid

import org.json.JSONArray

class TopicRepository : TopicRepositoryInterface{

    override fun getData(quizData: JSONArray): List<Topic> {
        var topics = mutableListOf<Topic>()
        // Create topics
        for (i in 0..(quizData.length() - 1)) {
            val topicName = quizData.getJSONObject(i).getString("Topic")
            val shortDescription = quizData.getJSONObject(i).getString("ShortDescription")
            val longDescription = quizData.getJSONObject(i).getString("LongDescription")
            val quizQuestions = quizData.getJSONObject(i).getJSONArray("Questions")
            val formattedQuestions = mutableListOf<Quiz>()
                for (i in 0..(quizQuestions.length() - 1)) {
                    val question = quizQuestions.getJSONObject(i).getString("Question")
                    val answer = quizQuestions.getJSONObject(i).getInt("Answer")
                    val choices = quizQuestions.getJSONObject(i).get("Choices")

                    formattedQuestions.add(Quiz(question, choices, answer))
                }
            topics.add(Topic(topicName, shortDescription, longDescription, formattedQuestions))
        }
        return topics
    }


}

class Topic(val title: String, val shortDescr: String, val longDescr: String, val quiz: List<Quiz>) {}
class Quiz(val question: String, val options: Any, val correctIndex: Int) {}