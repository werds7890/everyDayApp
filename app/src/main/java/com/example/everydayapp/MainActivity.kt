package com.example.everydayapp

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val githubBtn=findViewById<ImageButton>(R.id.github)
        val slackBtn=findViewById<ImageButton>(R.id.slack)
        val notionBtn=findViewById<ImageButton>(R.id.notion)
        val pgBtn=findViewById<ImageButton>(R.id.programmers)
        val myPageBtn=findViewById<ImageButton>(R.id.myPage)

        val intent=Intent(Intent.ACTION_VIEW, Uri.parse(""))

        githubBtn.setOnClickListener {
            intent.setData(Uri.parse("https://github.com/werds7890/everyDayApp"))
            startActivity(intent)
        }
        slackBtn.setOnClickListener {
            intent.setData(Uri.parse("https://slack.com/intl/ko-kr/"))
            startActivity(intent)
        }
        notionBtn.setOnClickListener {
            intent.setData(Uri.parse("https://www.notion.so/ko-kr"))
            startActivity(intent)
        }
        pgBtn.setOnClickListener {
            intent.setData(Uri.parse("https://programmers.co.kr/"))
            startActivity(intent)
        }
        myPageBtn.setOnClickListener {
            val intent=Intent(this,TeamActivity::class.java)
            startActivity(intent)
        }
    }
}
