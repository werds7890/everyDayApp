# everyDayApp

### LoginPage
아이디/비밀번호 미입력 시 안내 메시지\
비밀번호 입력 시 보안(***으로 표현)\
작성된 아이디,팀명을 TeamPage로 전달
```kotlin
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

        //Id data 받기, teamNameData받기
        val idData = intent.getStringExtra("dataFromSignUpPage")
        val teamNameData = intent.getStringExtra("TeamNamedataFromSignUpPage")
        val pwData = intent.getStringExtra("dataPW")
        loginIdEditText.setText(idData)
        loginPwEditText.setText(pwData)


        val dataToIntent = Intent(this, MainActivity::class.java)
        dataToIntent.putExtra("idData",idData)
        dataToIntent.putExtra("teamNameData",teamNameData)

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
                startActivity(dataToIntent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            }
        }

        //회원가입페이지로 이동.
        signUpBtn.setOnClickListener {
            val signUpIntent = Intent(this, SignUpPage::class.java)
            startActivity(signUpIntent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
}
```
### SignupPage
체크 버튼으로 아이디 유효성 검사('아이디 5자 이상 15자 이하' 안내 메세지)\
하나라도 미 입력 시 안내 메시지\
작성된 아이디,비밀번호를 loginPage로 전달
```kotlin
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
                val currentLength = signUpId.length
                when {
                    (currentLength < 5 ) -> {Toast.makeText(this, "5자이상 15자만 가능합니다.", Toast.LENGTH_SHORT).show() }
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
                    signUpIntent.putExtra("dataPW",signUpPw)
                    signUpIntent.putExtra("TeamNamedataFromSignUpPage",signUpTeamName)
                    startActivity(signUpIntent)
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                }
            }
        }

        cencelBtn.setOnClickListener {
            Log.d("cencel_Button","cencel Button Click")
            finish()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

    }
}
```
### MainPage
개인 프로필 이미지 클릭 시 DetailPage로 이동(좌우 스크롤)\
사이트 아이콘 클릭 시 각 사이트 링로 이동\
한마디 아이콘 클릭 시 WritingPage로 이동
### WritingPage
닉네임, 글 작성 후 MainPage로 입력 값 전달
### DetailPage
개인 프로필 이미지, 정보 표시\
더보기 클릭 시 잘린 텍스트 표시
게시물 수정/ 저장
### TeamPage
SignupPage에서 받아온 팀명, 아이디 값 표시\
게시글 수정/ 저장\
갤러리 아이콘 클릭 시 이미지 변경\
더보기 클릭 시 잘린 텍스트 표시

### 추가 기능
언어 변경(영어) 지원\
Dark theme 지원\
세로/가로 모드 지원
