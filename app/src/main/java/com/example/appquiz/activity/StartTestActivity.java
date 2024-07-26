package com.example.appquiz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquiz.R;

public class StartTestActivity extends AppCompatActivity {
    private TextView catName,testNo,totalQ,bestScore,time;
    private Button starttestB;
    private ImageView backB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        
        init();
    }
    private void init()
    {
        catName=findViewById(R.id.st_cat_name);
        testNo=findViewById(R.id.testNo);
        totalQ=findViewById(R.id.st_total_question);
        bestScore=findViewById(R.id.st_best_score);
        time=findViewById(R.id.st_time);
        starttestB=findViewById(R.id.start_testB);
        backB=findViewById(R.id.st_backB);

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTestActivity.this.finish();
            }
        });
        starttestB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(StartTestActivity.this,QuestionsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}