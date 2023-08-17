package com.example.everydayapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpPage : AppCompatActivity() {
    private lateinit var useTextWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        val compBtn = findViewById<Button>(R.id.btn_Complete)
        val cencelBtn = findViewById<Button>(R.id.btn_cencel)
        val checkBtn = findViewById<Button>(R.id.btn_check)
        val signUpEditId = findViewById<EditText>(R.id.editId2)
        val signUpEditPw = findViewById<EditText>(R.id.editPw2)
        val signUpEditTeamName = findViewById<EditText>(R.id.editTeamName)


        useTextWatcher = object : TextWatcher {
            val maxLength = 15
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length ?: 0 > maxLength) {
                    signUpEditId.error = "최대 $maxLength 글자까지 입력 가능합니다."
                } else {
                    signUpEditId.error = null // 이전에 설정된 오류 지우기.
                }
            }
        }


        checkBtn.setOnClickListener {
            val signUpId = signUpEditId.text
            useTextWatcher.afterTextChanged(signUpId)
            checkBtn.setOnClickListener {
                val maxLength = 15 // maxLength를 여기에 정의??????
                val signUpId = signUpEditId.text
                val currentLength = signUpId.length
                when {
                    (currentLength <= maxLength) -> {
                        Toast.makeText(this, "아이디를 사용하실 수 있습니다", Toast.LENGTH_SHORT).show() }
                }
            }

            Log.d("Check_Button","Check Button Click")
            }


        compBtn.setOnClickListener {
            val signUpId = signUpEditId.text.toString()
            val signUpPw = signUpEditPw.text.toString()
            val signUpTeamName = signUpEditTeamName.text.toString()

            when {
                (signUpId.isEmpty()) -> {
                    Toast.makeText(this, "아이디를 입력해 주세요!", Toast.LENGTH_SHORT).show()
                }

                (signUpPw.isEmpty()) -> {
                    Toast.makeText(this, "비밀번호를 입력해 주세요!", Toast.LENGTH_SHORT).show()
                }

                (signUpTeamName.isEmpty()) -> {
                    Toast.makeText(this, "팀 이름을 입력해 주세요!", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                    val signUpIntent = Intent(this, LoginPage::class.java)
                    signUpIntent.putExtra("dataFromSignUpPage",signUpId)
                    signUpIntent.putExtra("TeamNamedataFromSignUpPage",signUpTeamName)
                    startActivity(signUpIntent)
                }
            }
        }

        cencelBtn.setOnClickListener {
            Log.d("cencel_Button","cencel Button Click")
            finish()
        }

    }
}