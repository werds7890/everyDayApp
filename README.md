# everyDayApp

### LoginPage
아이디/비밀번호 미입력 시 안내 메시지\
비밀번호 입력 시 보안(***으로 표현)\
작성된 아이디를 TeamPage로 전달
```kotlin

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
```kotlin

class MainActivity : AppCompatActivity() {
    private lateinit var writingResult:ActivityResultLauncher<Intent>
    private var intentMaster=Intent()   //액티비티 전환 인텐트
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val githubBtn=findViewById<ImageButton>(R.id.github)
        val slackBtn=findViewById<ImageButton>(R.id.slack)
        val notionBtn=findViewById<ImageButton>(R.id.notion)
        val pgBtn=findViewById<ImageButton>(R.id.programmers)

        val myPageBtn=findViewById<ImageButton>(R.id.myPage)
        val writingBtn=findViewById<ImageButton>(R.id.writing)

        val teamName=findViewById<TextView>(R.id.teamName)  //조이름
        val teamNameData=intent.getStringExtra("teamNameData")  //로그인페이지에서 데이터 받아옴
        teamName.setText(teamNameData) //받아온 데이터로 조이름 변경

        val intent2=Intent(Intent.ACTION_VIEW, Uri.parse(""))   //주소링크받는 인텐트

        githubBtn.setOnClickListener {
            intent2.setData(Uri.parse("https://github.com/werds7890/everyDayApp"))
            startActivity(intent2)
        }
        slackBtn.setOnClickListener {
            intent2.setData(Uri.parse("https://slack.com/intl/ko-kr/"))
            startActivity(intent2)
        }
        notionBtn.setOnClickListener {
            intent2.setData(Uri.parse("https://www.notion.so/ko-kr"))
            startActivity(intent2)
        }
        pgBtn.setOnClickListener {
            intent2.setData(Uri.parse("https://programmers.co.kr/"))
            startActivity(intent2)
        }
        myPageBtn.setOnClickListener {
            intentMaster.setClass(this,TeamActivity::class.java)
            val idData=intent.getStringExtra("idData")
            val teamNameData=intent.getStringExtra("teamNameData")
            intentMaster.putExtra("idData",idData)
            intentMaster.putExtra("teamNameData",teamNameData)
            intentMaster.putExtra("motto","포기하지 말자!")
            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        writingResultComplete()

        writingBtn.setOnClickListener {
            intentMaster.setClass(this,WritingActivity::class.java)
            writingResult.launch(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
    fun intoDetail(view:View) {
        intentMaster.setClass(this,Detail::class.java)
        if(view.getId()==R.id.imageButton3){
            intentMaster.putExtra("name","이름 : 김현걸")
            intentMaster.putExtra("mbti","MBTI : ISTP")
            intentMaster.putExtra("좌우명","좌우명 : 포기는 배추 셀 때나 쓰는 말이다.")

            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }else if(view.getId()==R.id.imageButton4){
            intentMaster.putExtra("name","이름 : 황현아")
            intentMaster.putExtra("mbti","MBTI : ISFP")
            intentMaster.putExtra("좌우명","좌우명 : 그냥 살자")
            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }else if(view.getId()==R.id.imageButton5){
            intentMaster.putExtra("name","이름 : 이지현")
            intentMaster.putExtra("mbti","MBTI : INTJ")
            intentMaster.putExtra("좌우명","좌우명 : 최선을 다하자")
            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }else if(view.getId()==R.id.imageButton6){
            intentMaster.putExtra("name","이름 : 황일규")
            intentMaster.putExtra("mbti","MBTI : ENFJ")
            intentMaster.putExtra("좌우명","좌우명 : 노력은 배신하지 않는다.")
            startActivity(intentMaster)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }

    private fun writingResultComplete() {
        val write=findViewById<TextView>(R.id.realWrite)
        val name=findViewById<TextView>(R.id.realName)

        writingResult=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            result -> if(result.resultCode == RESULT_OK) {
                val write2=result.data?.getStringExtra("writing") ?:"익명"
                val name2=result.data?.getStringExtra("nickname") ?:"글을 입력해주세요."
                write.setText(write2)
                name.setText(name2)
        }
        }
    }

}

```
### WritingPage
닉네임, 글 작성 후 MainPage로 입력 값 전달
```kotlin

class WritingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        val complete=findViewById<Button>(R.id.complete)
        complete.setOnClickListener {
            val nickName=findViewById<EditText>(R.id.nameEdit).text.toString()
            val writing=findViewById<EditText>(R.id.writingEdit).text.toString()
            intent.putExtra("nickname", nickName)
            intent.putExtra("writing", writing)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}
```
### DetailPage
개인 프로필 이미지, 정보 표시\
더보기 클릭 시 잘린 텍스트 표시
```kotlin

class Detail : AppCompatActivity() {
    private var lineCount=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val name=findViewById<TextView>(R.id.name)
        val mbti=findViewById<TextView>(R.id.mbti)
        val motto=findViewById<TextView>(R.id.motto)
        val detailImg=findViewById<ImageButton>(R.id.detailImage)
        val firsttext =findViewById<TextView>(R.id.firsttext)   //textview
        val secondtext=findViewById<TextView>(R.id.secondtext)
        val thirdtext =findViewById<TextView>(R.id.thirdtext)
        val firstEdittext=findViewById<EditText>(R.id.editText1)    //edittext
        val secondEdittext=findViewById<EditText>(R.id.editText2)
        val thirdEdittext =findViewById<EditText>(R.id.editText3)
        val firstedit =findViewById<ImageButton>(R.id.Edit_Button1) //edit btn
        val secondedit =findViewById<ImageButton>(R.id.Edit_Button2)
        val thirdedit=findViewById<ImageButton>(R.id.Edit_Button3)
        val firstsave =findViewById<ImageButton>(R.id.Save_Button1)   //save btn
        val secondsave =findViewById<ImageButton>(R.id.Save_Button2)
        val thirdsave =findViewById<ImageButton>(R.id.Save_Button3)

        val nameData=intent.getStringExtra("name")
        val mbtiData=intent.getStringExtra("mbti")
        val mottoData=intent.getStringExtra("좌우명")


        name.setText(nameData)
        mbti.setText(mbtiData)
        motto.setText(mottoData)

        seeMoreFeature()

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
            secondEdittext.setText(secondtext.text)
        }
        secondsave.setOnClickListener {
            secondtext.visibility = View.VISIBLE
            secondEdittext.visibility = View.GONE
            secondtext.text = secondEdittext.text
        }

        thirdedit.setOnClickListener {
            thirdtext.visibility = View.GONE
            thirdEdittext.visibility = View.VISIBLE
            thirdEdittext.setText(thirdtext.text)
        }
        thirdsave.setOnClickListener {
            thirdtext.visibility = View.VISIBLE
            thirdEdittext.visibility = View.GONE
            thirdtext.text = thirdEdittext.text
        }

    }

    private fun seeMoreFeature() {
        val textAdd=findViewById<TextView>(R.id.textAdd)
        val mottoAdd=findViewById<TextView>(R.id.motto)
        //post메서드는 텍스트뷰가 화면에 표시된후 Runnable함!
        mottoAdd.post {
            lineCount=mottoAdd.layout.lineCount
            if(mottoAdd.layout.getEllipsisCount(lineCount-1)>0){ //두번째줄부터 생략되었는지 체크
                textAdd.visibility=View.VISIBLE

                textAdd.setOnClickListener {
                    mottoAdd.maxLines=Int.MAX_VALUE
                    textAdd.visibility=View.GONE
                }
            }

        }
    }

}


```
게시물 수정/ 저장
### TeamPage
SignupPage에서 받아온 팀명, 아이디 값 표시\
게시글 수정/ 저장\
갤러리 아이콘 클릭 시 이미지 변경\
더보기 클릭 시 잘린 텍스트 표시
```kotlin

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

        teamSeeMore()

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
                textView.maxLines=1
                textView.text = editText.text
                teamSeeMore()
        }
    }

    private fun teamSeeMore(){
        val teamWriting=findViewById<TextView>(R.id.textView)
        val seeMore=findViewById<TextView>(R.id.teamSeeMore)
        teamWriting.post {
            val lineCount=teamWriting.layout.lineCount
            Log.v("test","${lineCount}")
            if(teamWriting.layout.getEllipsisCount(lineCount-1)>0){
                seeMore.visibility=View.VISIBLE

                seeMore.setOnClickListener {
                    teamWriting.maxLines=Int.MAX_VALUE
                    seeMore.visibility=View.GONE
                }
            }
        }
    }

}
```

### 추가 기능
스플래시 화면
```kotlin

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(applicationContext, LoginPage::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }, 2000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
```
언어 변경(영어) 지원\
Dark theme 지원\
세로/가로 모드 지원
