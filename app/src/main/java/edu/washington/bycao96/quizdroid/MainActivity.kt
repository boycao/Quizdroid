package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mathButton = findViewById<View>(R.id.buttonMath)
        mathButton.setOnClickListener(){
            val mathIntent = Intent(this@MainActivity, TopicOverviewActivity::class.java )
            intent.putExtra("Topic","Math")
            startActivity(mathIntent)
        }


        val codeButton = findViewById<View>(R.id.buttonProgramming)
        codeButton.setOnClickListener(){
            val codeIntent = Intent(this@MainActivity,TopicOverviewActivity::class.java)
            intent.putExtra("Topic","Code")
            startActivity(codeIntent)
        }

        val goTButton = findViewById<View>(R.id.buttonGoT)
        goTButton.setOnClickListener(){
            val goTIntent = Intent(this@MainActivity, TopicOverviewActivity::class.java)
            intent.putExtra("Topic","GoT")
            startActivity(goTIntent)
        }

        val marvelButton = findViewById<View>(R.id.buttonMarvel)
        marvelButton.setOnClickListener(){
            val marvelIntent = Intent(this@MainActivity,TopicOverviewActivity::class.java)
            intent.putExtra("Topic","Marvel")
            startActivity(marvelIntent)
        }


    }
}
