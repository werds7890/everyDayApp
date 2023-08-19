package com.example.everydayapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class TeamActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private fun loadImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .into(imageView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        // 아이디, 팀명, 좌우명 가져오기
        val myPgTeamName=findViewById<TextView>(R.id.myPageTeamName)
        val myPgId=findViewById<TextView>(R.id.myPageId)
        val myPgMotto=findViewById<TextView>(R.id.myPageMotto)
        val myPgTeamName2=findViewById<TextView>(R.id.myPageTeamName2)

        myPgId.setText(intent.getStringExtra("idData"))
        myPgTeamName.setText(intent.getStringExtra("teamNameData"))
        myPgMotto.setText(intent.getStringExtra("motto"))
        myPgTeamName2.setText(intent.getStringExtra("teamNameData"))

        // 버튼 클릭 시 갤러리 연동, 이미지 불러오기
        val imageBtn = findViewById<ImageButton>(R.id.imageBtn)
        imageView = findViewById(R.id.imageView)

        val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                loadImage(uri)
            }
        }
        imageBtn.setOnClickListener {
            getImage.launch("image/*")
        }

        // 버튼 클릭 시 텍스트 수정,저장
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