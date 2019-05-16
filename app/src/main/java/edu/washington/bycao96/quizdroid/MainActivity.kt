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

        val sportsButton = findViewById<View>(R.id.buttonSports)
        sportsButton.setOnClickListener(){
            val sportsIntent = Intent(this@MainActivity, TopicOverviewActivity::class.java )
            sportsIntent.putExtra("TOPIC","Sports")
            startActivity(sportsIntent)
        }


        val basketballButton = findViewById<View>(R.id.buttonBasketball)
        basketballButton.setOnClickListener(){
            val basketballIntent = Intent(this@MainActivity,TopicOverviewActivity::class.java)
            basketballIntent.putExtra("TOPIC","Basketball")
            startActivity(basketballIntent)
        }

        val soccerButton = findViewById<View>(R.id.buttonSoccer)
        soccerButton.setOnClickListener(){
            val soccerIntent = Intent(this@MainActivity,TopicOverviewActivity::class.java)
            soccerIntent.putExtra("TOPIC","Soccer")
            startActivity(soccerIntent)
        }
        Log.e(TAG,"Main")

    }
}
