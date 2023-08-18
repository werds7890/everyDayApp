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
    private var intentMaster=Intent()   //액티비티 전환 인텐트
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val githubBtn=findViewById<ImageButton>(R.id.github)
        val slackBtn=findViewById<ImageButton>(R.id.slack)
        val notionBtn=findViewById<ImageButton>(R.id.notion)
        val pgBtn=findViewById<ImageButton>(R.id.programmers)

        val myPageBtn=findViewById<ImageButton>(R.id.myPage)
        val writingBtn=findViewById<ImageButton>(R.id.writing)

        val teamName=findViewById<TextView>(R.id.teamName)  //조이름
        val teamNameData=intent.getStringExtra("teamNameData")  //로그인페이지에서 데이터 받아옴
        teamName.setText(teamNameData) //받아온 데이터로 조이름 변경

        val intent2=Intent(Intent.ACTION_VIEW, Uri.parse(""))   //주소링크받는 인텐트

        githubBtn.setOnClickListener {
            intent2.setData(Uri.parse("https://github.com/werds7890/everyDayApp"))
            startActivity(intent2)
        }
        slackBtn.setOnClickListener {
            intent2.setData(Uri.parse("https://slack.com/intl/ko-kr/"))
            startActivity(intent2)
        }
        notionBtn.setOnClickListener {
            intent2.setData(Uri.parse("https://www.notion.so/ko-kr"))
            startActivity(intent2)
        }
        pgBtn.setOnClickListener {
            intent2.setData(Uri.parse("https://programmers.co.kr/"))
            startActivity(intent2)
        }
        myPageBtn.setOnClickListener {
            intentMaster.setClass(this,TeamActivity::class.java)
            val idData=intent.getStringExtra("idData")
            val teamNameData=intent.getStringExtra("teamNameData")
            intentMaster.putExtra("idData",idData)
            intentMaster.putExtra("teamNameData",teamNameData)
            intentMaster.putExtra("motto","포기하지 말자!")
            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        writingResultComplete()

        writingBtn.setOnClickListener {
            intentMaster.setClass(this,WritingActivity::class.java)
            writingResult.launch(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
    fun intoDetail(view:View) {
        intentMaster.setClass(this,Detail::class.java)
        if(view.getId()==R.id.imageButton3){
            intentMaster.putExtra("name","이름 : 김현걸")
            intentMaster.putExtra("mbti","MBTI : ISTP")
            intentMaster.putExtra("좌우명","좌우명 : 포기는 배추 셀 때나 쓰는 말이다.")

            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }else if(view.getId()==R.id.imageButton4){
            intentMaster.putExtra("name","이름 : 황현아")
            intentMaster.putExtra("mbti","MBTI : ISFP")
            intentMaster.putExtra("좌우명","좌우명 : 그냥 살자")
            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }else if(view.getId()==R.id.imageButton5){
            intentMaster.putExtra("name","이름 : 이지현")
            intentMaster.putExtra("mbti","MBTI : INTJ")
            intentMaster.putExtra("좌우명","좌우명 : 최선을 다하자")
            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }else if(view.getId()==R.id.imageButton6){
            intentMaster.putExtra("name","이름 : 황일규")
            intentMaster.putExtra("mbti","MBTI : ENFJ")
            intentMaster.putExtra("좌우명","좌우명 : 노력은 배신하지 않는다.")
            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }

    private fun writingResultComplete() {
        val write=findViewById<TextView>(R.id.realWrite)
        val name=findViewById<TextView>(R.id.realName)

        writingResult=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            result -> if(result.resultCode == RESULT_OK) {
                val write2=result.data?.getStringExtra("writing") ?:"익명"
                val name2=result.data?.getStringExtra("nickname") ?:"글을 입력해주세요."
                write.setText(write2)
                name.setText(name2)
        }
        }
    }

}
