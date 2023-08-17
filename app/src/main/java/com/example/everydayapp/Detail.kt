package com.example.everydayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name=findViewById<TextView>(R.id.name)
        val mbti=findViewById<TextView>(R.id.mbti)
        val motto=findViewById<TextView>(R.id.motto)

        val nameData=intent.getStringExtra("name")
        val mbtiData=intent.getStringExtra("mbti")
        val mottoData=intent.getStringExtra("좌우명")

        name.setText(nameData)
        mbti.setText(mbtiData)
        motto.setText(mottoData)
    }
}