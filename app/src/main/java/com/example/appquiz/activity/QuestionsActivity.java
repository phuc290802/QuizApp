package com.example.appquiz.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquiz.adapter.QuestionsAdapter;
import com.example.appquiz.R;
import com.example.appquiz.fragment.CategoryFragment;
import com.example.appquiz.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuestionsActivity extends AppCompatActivity {

    private RecyclerView questionsView;
    private TextView tvQuesID, timerTvMin,timerTvSec, catNameTV;
    private Button submitB, markB, clearB;
    private ImageButton prevQuesB, nextQuesB;
    private ImageView quesListB;
    public static int quesID;
    private Button starttest;
    public   List<QuestionModel> listQues= new ArrayList<>();;
    private QuestionsAdapter quesAdapter;
    private QuestionModel quesModel;
    private CountDownTimer mCountDownTimer;
    public static int correctQ = 0, wrongQ = 0, unattempQ = 0;
    public static long min_end,sec_end;

    private static final long start_time=600000;
    private long min=start_time;
    private boolean timerrunning;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        init();
        mytestList();
        //Hàm khởi tạo thời gian chạy
        if (timerrunning) {
            pauseTimer();
        } else {
            Starttimer();
        }
        quesModel=new QuestionModel();
        quesAdapter = new QuestionsAdapter(listQues);
        questionsView.setAdapter(quesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        questionsView.setLayoutManager(layoutManager);
        //Phân chia layout câu hỏi
        tvQuesID.setText("1/"+String.valueOf(listQues.size()));
        setClickListeners();
        setSnapHelper();
    }

    private void init() {
        questionsView = findViewById(R.id.questions_view);
        tvQuesID = findViewById(R.id.tv_quesID);
        catNameTV = findViewById(R.id.qa_catName);
        submitB = findViewById(R.id.submitB);
        prevQuesB = findViewById(R.id.prev_quesB);
        nextQuesB = findViewById(R.id.next_queB);
        quesListB = findViewById(R.id.ques_list_gridB);
        starttest=findViewById(R.id.start_testB);
        timerTvMin=findViewById(R.id.tv_timeMin);
    }
    private void setClickListeners()
    {
        prevQuesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quesID>0)
                {
                    questionsView.smoothScrollToPosition(quesID-1);
                    quesID=quesID-1;
                    tvQuesID.setText(String.valueOf(quesID+1)+"/"+listQues.size());
                }
            }
        });
        nextQuesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quesID<listQues.size()-1)
                {
                    questionsView.smoothScrollToPosition(quesID+1);
                    quesID=quesID+1;
                    tvQuesID.setText(String.valueOf(quesID+1)+"/"+listQues.size());
                }
            }
        });
        submitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                TraVeAnswer();
                pauseTimer();
                Intent intent= new Intent(QuestionsActivity.this,ScroeActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }


    private void check(){
        for(QuestionModel q:listQues)
        {
            if(q.getCorrectans()==q.getAnsChoice())
            {
                q.setSelectedAns(1);
            }
            else if(q.getAnsChoice()==0){
                q.setSelectedAns(-1);
            }
            else{
                q.setSelectedAns(0);
            }
        }
    }
    private void TraVeAnswer()
    {

        for (QuestionModel q:listQues)
        {
            if(q.getSelectedAns()==-1)
            {
                unattempQ++;
            }
            else{
                if(q.getSelectedAns()==1)
                {
                    correctQ++;
                }
                else{
                    wrongQ++;
                }
            }
        }
    }
    private void setSnapHelper()
    {
        final SnapHelper snapHelper =new PagerSnapHelper();
        snapHelper.attachToRecyclerView(questionsView);
        questionsView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view =snapHelper.findSnapView(recyclerView.getLayoutManager());
                quesID=recyclerView.getLayoutManager().getPosition(view);
                tvQuesID.setText(String.valueOf(quesID+1)+"/"+listQues.size());
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    public void Starttimer()
    {
        if(!timerrunning){
            mCountDownTimer=new CountDownTimer(min , 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            //
                            min =millisUntilFinished;
                            updateCountDownTest();
                            //
                        }
                    });
                }
                @Override
                public void onFinish() {
                    timerrunning=false;
                }
            }.start();
            timerrunning = true;
        }

    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        timerrunning = false;
    }

    private void updateCountDownTest()
    {

        int minit=(int) (min/1000)/60;
        int sec=(int)(min/1000)%60;
        String timeleftFormated=String.format(Locale.getDefault(),"%02d:%02d",minit,sec);
        timerTvMin.setText(timeleftFormated);
        min_end=minit;
        sec_end=sec;
        if(minit==0&&sec==0)
        {
            check();
            TraVeAnswer();
            Intent intent= new Intent(QuestionsActivity.this,ScroeActivity.class);
            startActivity(intent);
            finish();
        }
    }
    CategoryFragment catf = new CategoryFragment();
    TestActivity testA = new TestActivity();
    public static int sizeList;
    public void mytestList()
    {
        if(catf.getCategoryChoice()==1 && testA.getTestChoice()==1)
        {
            MathTest1();
        }
        else if(catf.getCategoryChoice()==1 && testA.getTestChoice()==2)
        {
            MathTest2();
        }
        else if(catf.getCategoryChoice()==2 && testA.getTestChoice()==1)
        {
            EngLishTest1();
        }
        else if(catf.getCategoryChoice()==2 && testA.getTestChoice()==2)
        {
            EngLishTest2();
        }
        else if(catf.getCategoryChoice()==2 && testA.getTestChoice()==3)
        {
            EngLishTest3();
        }
        else if(catf.getCategoryChoice()==3 && testA.getTestChoice()==1)
        {
            HistoryTest1();
        }
        else if(catf.getCategoryChoice()==4 && testA.getTestChoice()==1)
        {
            javaTest1();
        }
        sizeList=listQues.size();
    }

    public void MathTest1()
    {
        listQues = new ArrayList<>();
        listQues.add(new QuestionModel(1,"8 / 2", "1", "-1", "4", "-2", 3,0));
        listQues.add(new QuestionModel(2,"17 mod 7", "10", "7", "3", "280", 3,0));
        listQues.add(new QuestionModel(3,"-81 / 3", "-27", "-1", "270", "-2", 1,0));
        listQues.add(new QuestionModel(4,"111 + 222 + 333", "700", "666", "7", "100", 2,0));
        listQues.add(new QuestionModel(5,"3 + 6 x (5 + 4) ÷ 3 - 7 ", "-11", "16", "14", "15", 3,0));
//        listQues.add(new QuestionModel(6," 150 ÷ (6 + 3 x 8) - 5 ", "-2", "5", "-100", "0", 4,0));
//        listQues.add(new QuestionModel(7,"200 – (96 ÷ 4)", "105", "176", "26", "16", 2,0));
//        listQues.add(new QuestionModel(8,"90 ÷ 10 ", "9", "10", "900", "1", 1,0));
//        listQues.add(new QuestionModel(9,"173 mod 5", "-100", "8 mod 4", "7", "3", 4,0));
//        listQues.add(new QuestionModel(10,"26 + 32 - 12 ", "-11", "32", "56", "46", 4,0));

    }
    public void MathTest2()
    {
        listQues = new ArrayList<>();
        listQues.add(new QuestionModel(1,"3x3", "1", "-1", "9", "-2", 3,0));
        listQues.add(new QuestionModel(2,"2+2", "1", "-1", "-4", "4", 4,0));
        listQues.add(new QuestionModel(3,"55 /5 ", "11", "-1", "2", "-2", 1,0));
        listQues.add(new QuestionModel(4,"348x2", "1800", "696", "150922", "150", 2,0));
        listQues.add(new QuestionModel(5,"x + 1 = 0; x=? ", "-1", "1", "-10", "--100", 1,0));
    }
    public void EngLishTest1()
    {
        listQues = new ArrayList<>();
        listQues.add(new QuestionModel(1," He's very short: ________ sisters are taller.", "(A) both of them", "(B) his both", "(C) both his", "(D) the two both his", 3,0));
        listQues.add(new QuestionModel(2," When ________ dinner.", "(A) have you", "(B) do you have", "(C) you have", "(D) you are having", 2,0));
        listQues.add(new QuestionModel(3," Kate is the best ________ the three. ", "(A) in", "(B) from", "(C) than", "(D) of", 4,0));
        listQues.add(new QuestionModel(4,"Are you ready? - ________.", "(A) Already not", "(B) Quite not", "(C) Yet not", "(D) Not yet", 4,0));
        listQues.add(new QuestionModel(5,"Leave your dirty shoes ________ the door. ", "(A) out from", "(B) out", "(C) outside", "(D) out of", 3,0));
        listQues.add(new QuestionModel(6," He ________ swim very well.", "(A) not can", "(B) cannot", "(C) doesn't can", "(D) don't can", 2,0));
        listQues.add(new QuestionModel(7,". Peter works in London. ________.", "(A) He goes there by train.", "(B) He there goes by train.", "(C) He goes by train there.", "(D) There goes he by train.", 1,0));
        listQues.add(new QuestionModel(8," Yeuk Yee had her house painted white yesterday.", "(A) She had to paint her house white yesterday.", "(B) Her house was not blue last week.", "(C) They painted her house white for her yesterday.", "(D) She painted her house yesterday.", 3,0));
        listQues.add(new QuestionModel(9," The hotel is ________.", "(A) sell", "(B) for sale", "(C) for sell", "(D) sale", 2,0));
        listQues.add(new QuestionModel(10,"Can we begin the test? - We can't unless the teacher ________ so.", "(A) will say", "(B) is saying", "(C) shall say", "(D) says", 4,0));
    }
    public void EngLishTest2()
    {
        listQues = new ArrayList<>();
        listQues.add(new QuestionModel(1,"The little boy pleaded _____ not to leave him alone in the dark.", "A. on his mother", "B. his mother", "C. with his mother", "D. at his mother", 3,0));
        listQues.add(new QuestionModel(2,"_____, the people who come to this club are in their twenties and", "A. By and large", "B. Altogether", "C. To a degree", "D. Virtually", 1,0));
        listQues.add(new QuestionModel(3," The TV station, in _______ to massive popular demand, decided not", "A. reaction", "B. response", "C. answer", "D. rely", 2,0));
        listQues.add(new QuestionModel(4,"I didn’t think his the comments were very appropriate at the time.", "A. correct ", "B. right ", "  C. exact ", "D. suitable", 4,0));
        listQues.add(new QuestionModel(5,"When we went back to the bookstore, the bookseller _______ the book we wanted.", "A. sold", " B. had sold ", " C. sells ", "D. has sold", 2,0));
        listQues.add(new QuestionModel(6,"By the end of last summer, the farmers _______ all the crop.", "A. harvested  ", " B. had harvested ", " C. harvest ", "D. are harvested", 2,0));
        listQues.add(new QuestionModel(7,"The director _______ for the meeting by the time I got to his office.", "A. left ", "B. had left", " C. leaves", " D. will leave", 2,0));
        listQues.add(new QuestionModel(8,"Susan _______ her family after she had taken the university entrance examination.", "A. phoned", " B. had phoned  ", "C. phones ", "  D. is phoning", 1,0));

    }
    public void EngLishTest3()
    {
        listQues = new ArrayList<>();
        listQues.add(new QuestionModel(1," Sam didn't get much formal _______.", "school   ", "schooling", "schooldays ", "schoolgirl", 2,0));
        listQues.add(new QuestionModel(2,"Wow! What a _______ your sister is! I couldn't get off the phone", "talk    ", "talking    ", "talker    ", "talkative", 4,0));
        listQues.add(new QuestionModel(3,"He'll be remembered both as a brilliant footballer and as a true _______.", "sport    ", "sporting    ", "sportsman    ", "sports car", 3,0));
        listQues.add(new QuestionModel(4,"This season's _______ include five new plays and several concerts of Chinese and Indian music.", " entertainments", "entertainer", "to entertain ", "entertaining", 1,0));
        listQues.add(new QuestionModel(5," She was a _______ child, happiest when reading.", "study    ", "studied    ", "studious    ", "studiously", 3,0));
    }
    public void HistoryTest1()
    {
        listQues = new ArrayList<>();
        listQues.add(new QuestionModel(1,"Ngôn ngữ lập trình C được Dennish phát triển dựa trên ngôn ngữ lập trình nào:", "Ngôn ngữ B.", "Ngôn ngữ BCPL.", "Ngôn ngữ DEC PDP.", " Ngôn ngữ B và BCPL.", 4,0));
        listQues.add(new QuestionModel(2," Ngôn ngữ lập trình được Dennish đưa ra vào năm nào?", "1967", "1972", " 1970.", " 1976", 2,0));
        listQues.add(new QuestionModel(3,"Ngôn ngữ lập trình nào dưới đây là ngôn ngữ lập trình có cấu trúc?", "A. Ngôn ngữ Assembler..", "B. Ngôn ngữ C và Pascal.", "C. Ngôn ngữ Cobol.", "  A, B và C.", 2,0));
    }
    public void javaTest1()
    {
        listQues = new ArrayList<>();
        listQues.add(new QuestionModel(1," Kích thước của 1 Char là bao nhiêu?", "4 bit", "7 bit", "8 bit", "16 bit", 1,0));
        listQues.add(new QuestionModel(2,"Tùy chọn nào sau đây dẫn đến tính di động và bảo mật của Java?", "Bytecode được thực thi bởi JVM", "Applet làm cho mã Java an toàn và di động", "Sử dụng xử lý ngoại lệ", "Liên kết động giữa các đối tượng", 1,0));
        listQues.add(new QuestionModel(3,"Tính năng nào không phải là tính năng của Java?  ", "Dynamic (Động)", "Architecture Neutral (Độc lập với cấu trúc)", "Use of pointers (Sử dụng các điểm trỏ)", "Object-oriented (Hướng đối tượng) ", 3,0));
        listQues.add(new QuestionModel(4,"_____ được sử dụng để tìm và sửa lỗi trong các chương trình Java.", "JVM", "JRE ", "JDK ", "JDB ", 4,0));
        listQues.add(new QuestionModel(5,"Kiểu trả về của phương thức hashCode () trong lớp Object là gì?", "object", "int ", "long", "void ", 2,0));
    }

}