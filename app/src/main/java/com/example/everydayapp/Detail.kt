package com.example.everydayapp

import android.media.Image
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
        val detailImg=findViewById<ImageButton>(R.id.detailImage)
        val firsttext =findViewById<TextView>(R.id.firsttext)   //textview
        val secondtext=findViewById<TextView>(R.id.secondtext)
        val thirdtext =findViewById<TextView>(R.id.Thirdtext)
        val firstEdittext=findViewById<EditText>(R.id.editText1)    //edittext
        val secondEdittext=findViewById<EditText>(R.id.editText2)
        val thirdEditText =findViewById<EditText>(R.id.editText3)
        val firstedit =findViewById<ImageButton>(R.id.Edit_Button1) //edit btn
        val secondedit =findViewById<ImageButton>(R.id.Edit_Button2)
        val thirdedit =findViewById<ImageButton>(R.id.Edit_Button3)
        val firstsave =findViewById<ImageButton>(R.id.Save_Button1)   //save btn
        val secondsave =findViewById<ImageButton>(R.id.Save_Button2)
        val thirdsave=findViewById<ImageButton>(R.id.Save_Button3)

        val nameData=intent.getStringExtra("name")
        val mbtiData=intent.getStringExtra("mbti")
        val mottoData=intent.getStringExtra("좌우명")


        name.setText(nameData)
        mbti.setText(mbtiData)
        motto.setText(mottoData)

        when(nameData){
            "이름 : 김현걸" -> detailImg.setImageResource(R.drawable.hyeon)
            "이름 : 황현아" -> detailImg.setImageResource(R.drawable.pika)
            "이름 : 이지현" -> detailImg.setImageResource(R.drawable.kitty)
            "이름 : 황일규" -> detailImg.setImageResource(R.drawable.leejamong)
        }



        firstedit.setOnClickListener {
            firsttext.visibility = View.GONE
            firstEdittext.visibility = View.VISIBLE
            firstEdittext.setText(firsttext.text)
        }
        firstsave.setOnClickListener {
            firsttext.visibility = View.VISIBLE
            firstEdittext.visibility = View.GONE
            firsttext.text = firstEdittext.text
        }

        secondedit.setOnClickListener {
            secondtext.visibility = View.GONE
            secondEdittext.visibility = View.VISIBLE
        }
        secondsave.setOnClickListener {
            secondtext.visibility = View.VISIBLE
            secondEdittext.visibility = View.GONE
            secondtext.text = secondEdittext.text
        }

        thirdedit.setOnClickListener {
            thirdtext.visibility = View.GONE
            thirdEditText.visibility = View.VISIBLE
            thirdEditText.setText(thirdtext.text)
        }
        thirdsave.setOnClickListener {
            thirdtext.visibility = View.VISIBLE
            thirdEditText.visibility = View.GONE
        }

    }

}

