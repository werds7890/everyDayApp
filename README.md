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
