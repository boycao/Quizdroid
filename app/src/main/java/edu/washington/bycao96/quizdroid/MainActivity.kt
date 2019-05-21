package edu.washington.bycao96.quizdroid

import android.content.Intent
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mathButton = findViewById<View>(R.id.buttonMath)
        mathButton.setOnClickListener(){
            val intent = Intent(this@MainActivity, QuizActivity::class.java )
            intent.putExtra("TOPIC","Math")
            startActivity(intent)
        }


        val physicsButton = findViewById<View>(R.id.buttonPhysics)
        physicsButton.setOnClickListener(){
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra("TOPIC","Physics")
            startActivity(intent)
        }

        val marvelButton = findViewById<View>(R.id.buttonMarvel)
        marvelButton.setOnClickListener(){
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra("TOPIC","Marvel")
            startActivity(intent)
        }

    }
}
