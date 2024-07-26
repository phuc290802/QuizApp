package com.example.appquiz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appquiz.database.DuLieuQues;
import com.example.appquiz.R;
import com.example.appquiz.activity.ScroeActivity;
import com.example.appquiz.model.RankModel;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardAdapter extends BaseAdapter {
    private static List<RankModel> user_list;
    private ScroeActivity scoreAc= new ScroeActivity();


    public LeaderBoardAdapter(List<RankModel> user_list) {
        this.user_list = user_list;
    }

    @Override
    public int getCount() {
        return user_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview;
        if(view==null)
        {
            myview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history,viewGroup,false);
        }
        else {
            myview=view;
        }
        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreAc.setCheckButton(1);
                scoreAc.setCheckId(i+1);
                Intent intent= new Intent(view.getContext(), ScroeActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        TextView name =myview.findViewById(R.id.name);
        TextView score=myview.findViewById(R.id.score);
        TextView time=myview.findViewById(R.id.time);
        TextView id=myview.findViewById(R.id.id);
        name.setText("SUBJECT : "+user_list.get(i).getName());
        score.setText("SCORE : "+String.valueOf(user_list.get(i).getScore()));
        time.setText("TIME : "+user_list.get(i).getMinute()+":"+user_list.get(i).getSecond());
        id.setText("ID : "+String.valueOf(user_list.get(i).getId()));
        return myview;
    }
}
