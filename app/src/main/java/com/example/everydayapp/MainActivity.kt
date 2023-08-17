package com.example.everydayapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var writingResult:ActivityResultLauncher<Intent>
    private var intent=Intent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val githubBtn=findViewById<ImageButton>(R.id.github)
        val slackBtn=findViewById<ImageButton>(R.id.slack)
        val notionBtn=findViewById<ImageButton>(R.id.notion)
        val pgBtn=findViewById<ImageButton>(R.id.programmers)
        val myPageBtn=findViewById<ImageButton>(R.id.myPage)
        val writingBtn=findViewById<ImageButton>(R.id.writing)

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
            intent.setClass(this,TeamActivity::class.java)
            startActivity(intent)
        }

        writingResultComplete()

        writingBtn.setOnClickListener {
            intent.setClass(this,WritingActivity::class.java)
            writingResult.launch(intent)
        }
    }
    fun intoDetail(view:View) {
        intent.setClass(this,Detail::class.java)
        if(view.getId()==R.id.imageButton3){
            intent.putExtra("name","춘식이")
            intent.putExtra("mbti","ISTP")
            intent.putExtra("좌우명","착하게 살자")
            startActivity(intent)
        }else if(view.getId()==R.id.imageButton4){
            startActivity(intent)
        }
    }

    private fun writingResultComplete() {
        val write=findViewById<TextView>(R.id.realWrite)
        val name=findViewById<TextView>(R.id.realName)

        writingResult=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            result -> if(result.resultCode== RESULT_OK) {
                val write2=result.data?.getStringExtra("writing") ?:"익명"
                val name2=result.data?.getStringExtra("nickname") ?:"글을 입력해주세요."
                write.setText(write2)
                name.setText(name2)
        }
        }
    }

}
