package com.example.everydayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class WritingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        val complete=findViewById<Button>(R.id.complete)
        complete.setOnClickListener {
            val nickName=findViewById<EditText>(R.id.nameEdit).text.toString()
            val writing=findViewById<EditText>(R.id.writingEdit).text.toString()
            intent.putExtra("nickname", nickName)
            intent.putExtra("writing", writing)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}