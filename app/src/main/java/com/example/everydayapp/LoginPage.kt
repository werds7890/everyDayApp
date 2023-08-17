package com.example.everydayapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val loginBtn = findViewById<Button>(R.id.btn_login)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)
        val loginIdEditText = findViewById<EditText>(R.id.editId)
        val loginPwEditText = findViewById<EditText>(R.id.editPw)

        //Id data 받기
        val idData = intent.getStringExtra("dataFromSignUpPage")
        loginIdEditText.setText(idData)
        val idDataToIntent = Intent(this, MainActivity::class.java)
        idDataToIntent.putExtra("dataFromSignUpPage",idData)
        startActivity(idDataToIntent)

        //teamName data 받기
        val teamNameData = intent.getStringExtra("TeamNamedataFromSignUpPage")
        //
        val teamNameToIntent = Intent(this, MainActivity::class.java)
        teamNameToIntent.putExtra("dataFromSignUpPage",teamNameData)
        startActivity(teamNameToIntent)


        loginBtn.setOnClickListener {
            val loginId = loginIdEditText.text.toString()
            val loginPw = loginPwEditText.text.toString()

            if (loginId.isEmpty()) {
                Toast.makeText(this, "아이디를 확인해 주세요!", Toast.LENGTH_SHORT).show()
            } else if (loginPw.isEmpty()) {
                Toast.makeText(this, "비밀번호를 확인해 주세요!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()


                //intent 메인으로 넘어가기 추가.
                val intoMainIntent = Intent(this, MainActivity::class.java)
                startActivity(intoMainIntent)

            }
        }

        //회원가입페이지로 이동.
        signUpBtn.setOnClickListener {
            val signUpIntent = Intent(this, SignUpPage::class.java)
            startActivity(signUpIntent)
        }
    }
}