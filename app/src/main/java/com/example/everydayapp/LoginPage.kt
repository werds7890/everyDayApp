package com.example.everydayapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

       val loginBtn = findViewById<Button>(R.id.btn_login)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)
        val loginIdEditText = findViewById<EditText>(R.id.editId)
        val loginPwEditText = findViewById<EditText>(R.id.editPw)

        loginBtn.setOnClickListener {
            val loginId = loginIdEditText.text.toString()
            val loginPw = loginPwEditText.text.toString()

            if (loginId.isEmpty()) {
                Toast.makeText(this, "아이디를 확인해 주세요!", Toast.LENGTH_SHORT).show()
            } else if (loginPw.isEmpty()) {
                Toast.makeText(this, "비밀번호를 확인해 주세요!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()

                val loginIntent = Intent(this, SingleUpPage::class.java)
                intent.putExtra("dataFromSingleInActivity",loginId)
                startActivity(loginIntent)
            }
        }
        signUpBtn.setOnClickListener {
            val signUpIntent = Intent(this, SingleUpPage::class.java)
            startActivity(signUpIntent)
        }
    }
}