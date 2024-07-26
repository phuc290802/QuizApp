package com.example.appquiz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquiz.database.DuLieuQues;
import com.example.appquiz.R;
import com.example.appquiz.fragment.CategoryFragment;
import com.example.appquiz.model.QuestionModel;
import com.example.appquiz.model.RankModel;

import java.util.ArrayList;
import java.util.List;

public class ScroeActivity extends AppCompatActivity {
        private TextView scoreTv,timeTv,totalQtv,correctTv,wrongTV,unattemptedQTV;
        private GridView listview;
        private QuestionsActivity quesAc =new QuestionsActivity();
        private TestActivity testActivity = new TestActivity();
        private Button viewAnsB;
        private ImageView outTestB;
        private QuestionModel quesModel =new QuestionModel();
        private CategoryFragment catF = new CategoryFragment();
        //
        private static List<RankModel> userlist = new ArrayList<>();
        private DuLieuQues dl;
        private List<String> list = new ArrayList<>();
        private Context context;
        //
        private static int checkButton;
        private static int checkId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroe);
        context=this;
        init();
        clickback();
        clickEndScore();

    }
    private void init()
    {
        scoreTv=findViewById(R.id.score);
        timeTv=findViewById(R.id.time);
        totalQtv=findViewById(R.id.totalQ);
        wrongTV=findViewById(R.id.wrongQ);
        unattemptedQTV=findViewById(R.id.un_attemtedQ);
        viewAnsB=findViewById(R.id.view_answerB);
        correctTv=findViewById(R.id.correctQ);
        outTestB=findViewById(R.id.st_backB);
    }
    private void loadData() {
        correctTv.setText(String.valueOf(quesAc.correctQ));
        wrongTV.setText(String.valueOf(quesAc.wrongQ));
        unattemptedQTV.setText(String.valueOf(quesAc.unattempQ));
        totalQtv.setText(String.valueOf(quesAc.sizeList));
        int c=quesAc.correctQ*100/quesAc.sizeList;
        scoreTv.setText(String.valueOf(c));

        quesAc.correctQ=0;
        quesAc.wrongQ=0;
        quesAc.unattempQ=0;
    }
    private void clickback()
    {
        outTestB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckButton(0);
                ScroeActivity.this.finish();
            }
        });
    }
    private void clickEndScore()
    {
        int min=(int)quesAc.min_end;
        int sec=(int)quesAc.sec_end;
        timeTv.setText(min+":"+sec);
        list.clear();
        dl=new DuLieuQues(context);
        dl.sizeList();
        if(getCheckButton()!=1) {
            loadData();
            outTestB.setVisibility(View.INVISIBLE);
            viewAnsB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RankModel r = new RankModel();
                    r.setId(dl.getSize_list());
                    r.setName(catF.getNameC());
                    r.setScore(quesAc.correctQ * 100 / quesAc.sizeList);
                    r.setMinute(min);
                    r.setSecond(sec);
                    r.setCorrect(Integer.parseInt(correctTv.getText().toString()));
                    r.setWrong(Integer.parseInt(wrongTV.getText().toString()));
                    r.setUnattemp(Integer.parseInt(unattemptedQTV.getText().toString()));
                    r.setScore(Integer.parseInt(scoreTv.getText().toString()));
                    r.setTestId(testActivity.getTestChoice());
                    int kq = dl.InseartDiem(r);
                    ScroeActivity.this.finish();
                    testActivity.getTheTopScore();
                }
            });
        }
        else {
            viewAnswerTolitDb(getCheckId());
            viewAnsB.setVisibility(View.INVISIBLE);
        }

    }
    private void viewAnswerTolitDb(int b)
    {
        list.clear();
        dl=new DuLieuQues(context);
        userlist.clear();
        dl.getAllQuesToList(userlist);
        for(RankModel r:userlist)
        {
            if(r.getId()==b)
            {
                correctTv.setText(String.valueOf(r.getCorrect()));
                wrongTV.setText(String.valueOf(r.getWrong()));
                unattemptedQTV.setText(String.valueOf(r.getUnattemp()));
                scoreTv.setText(String.valueOf(r.getScore()));
                timeTv.setText(r.getMinute()+":"+r.getSecond());
            }
        }
    }

    public static int getCheckId() {
        return checkId;
    }
    public  void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getCheckButton() {
        return checkButton;
    }

    public void setCheckButton(int checkButton) {
        this.checkButton = checkButton;
    }

}