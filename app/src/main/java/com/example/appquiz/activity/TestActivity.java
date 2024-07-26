package com.example.appquiz.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.appquiz.R;
import com.example.appquiz.adapter.TestAdapter;
import com.example.appquiz.database.DuLieuQues;
import com.example.appquiz.fragment.CategoryFragment;
import com.example.appquiz.model.CategoryModel;
import com.example.appquiz.model.RankModel;
import com.example.appquiz.model.TestModel;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    CategoryFragment cateF= new CategoryFragment();
    private RecyclerView testview;
    private Toolbar toolbar ;
    private ImageView backB;
    private List<TestModel> testList;
    private static int testChoice;
    private int top_Score;
    private String name;
    //
    private DuLieuQues dl;
    private List<String> list = new ArrayList<>();
    private Context context;
    private static List<RankModel> userlist = new ArrayList<>();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        int cat_index=getIntent().getIntExtra("c",0);
        getSupportActionBar().setTitle(CategoryFragment.castList.get(cat_index).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        testview=findViewById(R.id.test_recycler_view);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        testview.setLayoutManager(layoutManager);

        //
        context=this;
        takeListFromDb();
        //
        getTheTopScore();
        TestAdapter adapter = new TestAdapter(testList);
        testview.setAdapter(adapter);
        backB=findViewById(R.id.st_backB);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.this.finish();
            }
        });
    }
    private void takeListFromDb()
    {
        list.clear();
        dl=new DuLieuQues(context);
        userlist.clear();
        dl.getAllQuesToList(userlist);
    }
    private void getChoiceTest()
    {
        if(cateF.getCategoryChoice()==1)
        {
            loadTestMath();
        }
        else if(cateF.getCategoryChoice()==2)
        {
            loadTestEnglish();
        }
        else if(cateF.getCategoryChoice()==3)
        {
            loadTestHistory();
        }
        else if(cateF.getCategoryChoice()==4)
        {
            loadTestSCIENCE();
        }
    }
    public void getTheTopScore()
    {
        getChoiceTest();
        getTheScore();
    }
    private void getTheScore()
    {
        if(cateF.getCategoryChoice()==1)
        {
            setTheScore("MATH");
        }
        else if(cateF.getCategoryChoice()==2)
        {
            setTheScore("ENGLISH");
        }
        else if(cateF.getCategoryChoice()==3)
        {
            setTheScore("HISTORY");
        }
        else if(cateF.getCategoryChoice()==4)
        {
            setTheScore("SCIENCE");
        }
    }
    private void setTheScore(String name)
    {
        for(TestModel t:testList)
        {
            System.out.println(t.getTestId());
            for(RankModel r:userlist)
            {   top_Score=0;
                if(r.getName().equalsIgnoreCase(name) && t.getTestId()==r.getTestId())
                {
                    if(r.getScore()>top_Score){
                        top_Score=r.getScore();
                        t.setTopScore(top_Score);
                    }
                }
            }
        }
    }
    private void loadTestMath()
    {
        testList=new ArrayList<>();
        testList.add(new TestModel(1,0,10));
        testList.add(new TestModel(2,0,10));
    }
    private void loadTestEnglish()
    {
        testList=new ArrayList<>();
        testList.add(new TestModel(1,0,10));
        testList.add(new TestModel(2,0,10));
        testList.add(new TestModel(3,0,15));
    }
    private void loadTestHistory()
    {
        testList=new ArrayList<>();
        testList.add(new TestModel(1,0,10));

    }
    private void loadTestSCIENCE()
    {
        testList=new ArrayList<>();
        testList.add(new TestModel(1,0,10));

    }

    public static int getTestChoice() {
        return testChoice;
    }

    public  void setTestChoice(int testChoice) {
        this.testChoice = testChoice;
    }
}