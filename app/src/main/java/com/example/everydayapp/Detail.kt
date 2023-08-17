package com.example.everydayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.os.persistableBundleOf
import android.widget.TextView


class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val name=findViewById<TextView>(R.id.name)
        val mbti=findViewById<TextView>(R.id.mbti)
        val motto=findViewById<TextView>(R.id.motto)
        val firsttext =findViewById<TextView>(R.id.firsttext)
        val secondtext=findViewById<TextView>(R.id.secondtext)
        val firstEdittext=findViewById<EditText>(R.id.editText1)
        val secondEdittext=findViewById<EditText>(R.id.editText2)
        val firstedit =findViewById<ImageButton>(R.id.Edit_Button1)
        val firstsave = findViewById<ImageButton>(R.id.Save_Button1)
        val secondedit =findViewById<ImageButton>(R.id.Edit_Button2)
        val secondsave = findViewById<ImageButton>(R.id.Save_Button2)

        val nameData=intent.getStringExtra("name")
        val mbtiData=intent.getStringExtra("mbti")
        val mottoData=intent.getStringExtra("좌우명")

        name.setText(nameData)
        mbti.setText(mbtiData)
        motto.setText(mottoData)

        firsttext.visibility

        firstedit.setOnClickListener {
            firsttext.visibility = View.GONE
            firstEdittext.visibility = View.VISIBLE
        }
        firstsave.setOnClickListener {
            firsttext.visibility = View.VISIBLE
            firstEdittext.visibility = View.GONE
            firsttext.text = firstEdittext.text
        }

        secondtext.visibility

        secondedit.setOnClickListener {
            secondtext.visibility = View.GONE
            secondEdittext.visibility = View.VISIBLE
        }
        secondsave.setOnClickListener {
            secondtext.visibility = View.VISIBLE
            secondEdittext.visibility = View.GONE
            secondtext.text = secondEdittext.text
        }

    }

}

