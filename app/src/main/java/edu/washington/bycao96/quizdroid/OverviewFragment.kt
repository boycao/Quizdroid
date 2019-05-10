package edu.washington.bycao96.quizdroid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class OverviewFragment : Fragment(){

    private var overviewTopicName : TextView
    private var overviewTopicDescr: TextView
    private var overviewBeginButton: Button
    private var overviewTopic : String? = null
    private var overviewQuestionNum : Int = 0
    private var overviewlistener : OnBeginClickedListener? = null

    interface beginQuizListener{
        fun beginQuizListener(overviewQuestionNum : Int)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }






}