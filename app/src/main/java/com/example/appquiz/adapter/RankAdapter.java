package com.example.appquiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.R;
import com.example.appquiz.model.RankModel;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    private List<RankModel> userList;

    public RankAdapter(List<RankModel> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankAdapter.ViewHolder holder, int position) {
            String name=userList.get(position).getName();
            int score = userList.get(position).getScore();
            int second = userList.get(position).getSecond();
            int minute = userList.get(position).getMinute();
//            holder.setData(name,score,rank);
    }

    @Override
    public int getItemCount() {
        if(userList.size()>10)
        {
            return 10;
        }
        else{
            return userList.size();
        }

    }
    private TextView nameTv,rankTv,scoreTv,imgTv;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv=itemView.findViewById(R.id.name);
            rankTv=itemView.findViewById(R.id.rank);
            scoreTv=itemView.findViewById(R.id.score);
            imgTv=itemView.findViewById(R.id.img_text);
        }
    }
    private void setData(String name,int score,int rank)
    {
        nameTv.setText(name);
        scoreTv.setText("Score : "+score );
    }
}
