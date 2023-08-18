package com.example.everydayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text

class TeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val myPgTeamName=findViewById<TextView>(R.id.myPageTeamName)
        val myPgId=findViewById<TextView>(R.id.myPageId)
        val myPgMotto=findViewById<TextView>(R.id.myPageMotto)
        val myPgTeamName2=findViewById<TextView>(R.id.myPageTeamName2)

        myPgId.setText(intent.getStringExtra("idData"))
        myPgTeamName.setText(intent.getStringExtra("teamNameData"))
        myPgMotto.setText(intent.getStringExtra("motto"))
        myPgTeamName2.setText(intent.getStringExtra("teamNameData"))

        val editBtn = findViewById<ImageButton>(R.id.editBtn)
        val saveBtn = findViewById<ImageButton>(R.id.saveBtn)
        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        textView.visibility

        editBtn.setOnClickListener {
                textView.visibility = View.GONE
                editText.visibility = View.VISIBLE
                editText.setText(textView.text)
        }
        saveBtn.setOnClickListener {
                textView.visibility = View.VISIBLE
                editText.visibility = View.GONE
                textView.text = editText.text
        }
    }
}