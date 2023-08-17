package com.example.everydayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class TeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

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