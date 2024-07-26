package com.example.appquiz.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appquiz.activity.MainActivity;
import com.example.appquiz.database.DuLieuQues;
import com.example.appquiz.adapter.LeaderBoardAdapter;
import com.example.appquiz.R;
import com.example.appquiz.adapter.RankAdapter;
import com.example.appquiz.model.RankModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class LeaderBoardFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public LeaderBoardFragment() {
        // Required empty public constructor
    }
    public static LeaderBoardFragment newInstance(String param1, String param2) {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private TextView myId,mySubject,myTime,myScore,myRank;
    private ListView userView;
    private Toolbar toolbar;
    private EditText search;
    private Button find;
    //
    private DuLieuQues dl;

    private Context context;
    //
    private LeaderBoardAdapter adapter;
    private static List<RankModel> userlist = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =inflater.inflate(R.layout.fragment_leader_board,container,false);
       context=view.getContext();
       initViews(view);
       adapter= new LeaderBoardAdapter(userlist);
       takeListFromDb();
       rankTop1();
       clickFindname();
       userView.setAdapter(adapter);

       return view;
    }
    private void clickFindname()
    {
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_search = search.getText().toString();
                findByName(text_search);
            }
        });

    }
    private void findByName(String name) {
        takeListFromDb();
        List<RankModel> filteredList = new ArrayList<>();
        for (RankModel r : userlist) {
            if (r.getName().equalsIgnoreCase(name)) {
                filteredList.add(r);
            }
        }
        userlist.clear();
        userlist.addAll(filteredList);
        adapter.notifyDataSetChanged();
    }

    private void takeListFromDb()
    {
        dl=new DuLieuQues(context);
        userlist.clear();
        dl.getAllQuesToList(userlist);
    }
    private void initViews(View view)
    {
        myId=view.findViewById(R.id.total_id);
        myTime=view.findViewById(R.id.total_time);
        mySubject=view.findViewById(R.id.total_subject);
        myScore=view.findViewById(R.id.total_score);
        myRank=view.findViewById(R.id.rank);
        userView=view.findViewById(R.id.users_view);
        search=view.findViewById(R.id.editTextTextPersonName);
        find=view.findViewById(R.id.find);
    }

    public void sortPoint()
    {
        Collections.sort(userlist, new Comparator<RankModel>() {
            @Override
            public int compare(RankModel o1, RankModel o2) {
                if (o1.getScore() > o2.getScore()) {
                    return -1;
                } else if (o1.getScore() < o2.getScore()) {
                    return 1;
                } else {
                    return -0;
                }
            }
        });
    }
    public void sortTimeAscending() {
        Collections.sort(userlist, new Comparator<RankModel>() {
            @Override
            public int compare(RankModel o1, RankModel o2) {
                if (o1.getMinute() > o2.getMinute()) {
                    return -1;
                } else if (o1.getMinute() < o2.getMinute()) {
                    return 1;
                } else {
                    if (o1.getSecond() > o2.getSecond()) {
                        return -1;
                    } else if (o1.getSecond() < o2.getSecond()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
    }

    public void rankTop1()
    {
        int score=0;
        int timeSec=0;
        int timeMin=0;
        int id=100;
        for(RankModel r:userlist)
        {
            if(r.getScore()>score)
            {
                score=r.getScore();
            }
        }
        for(RankModel r:userlist)
        {
            if(r.getScore()==score&&r.getMinute()>=timeMin)
            {
                timeMin=r.getMinute();
            }
        }
        for(RankModel r:userlist)
        {
            if(r.getScore()==score&&r.getMinute()==timeMin&&r.getSecond()>=timeSec)
            {
                timeSec=r.getSecond();

            }
        }
        for(RankModel r:userlist)
        {
            if(r.getScore()==score&&r.getMinute()==timeMin&&r.getSecond()==timeSec&&r.getId()<id)
            {
                id=r.getId();
            }
        }
        for(RankModel r:userlist)
        {
            if(r.getScore()==score&&r.getMinute()==timeMin&&r.getSecond()==timeSec&&r.getId()==id)
            {
                setTextTop1(r.getId(),r.getMinute(),r.getSecond(),r.getName(),r.getScore());
            }
        }
    }
    public void setTextTop1(int id,int timeMin,int timeSec,String subject,int score)
    {
        myId.setText("ID : "+String.valueOf(id));
        myTime.setText("TIME : "+timeMin+":"+timeSec);
        mySubject.setText(subject);
        myScore.setText("SCORE : "+String.valueOf(score));
    }

}