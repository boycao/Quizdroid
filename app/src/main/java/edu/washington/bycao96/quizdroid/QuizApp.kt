package edu.washington.bycao96.quizdroid

import android.app.Application
import android.nfc.Tag
import android.util.Log

class QuizApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp", "QuizApp is running")
    }

    /*
    Repository Pattern:  create a TopicRepository interface
                         create one implementation that simply stores elements in memory from a hard-coded list initialized on startup
                         Create domain objects for Topic and Quiz, where a Quiz is question text, four answers, and an integer saying which of the four answers is correct, and Topic is a title, short description, long description, and a collection of Question objects.

    Singleton:The singleton pattern is a design pattern that restricts the instantiation of a class to one object.
    Make the QuizApp object a Singleton, and provide a method for accessing the TopicRepository.
    Refactor the activities in the application to use the TopicRepository. On the topic list page, the title and the short description should come from the similar fields in the Topic object. On the topic overview page, the title and long description should come from the similar fields in the Topic object. The Question object should be similarly easy to match up to the UI.
     */
    companion object {
        private val instance: QuizApp = QuizApp()

        fun getInstance(): QuizApp {
            return instance
        }
    }
}