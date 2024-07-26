package com.example.appquiz.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.R;
import com.example.appquiz.activity.StartTestActivity;
import com.example.appquiz.activity.TestActivity;
import com.example.appquiz.model.TestModel;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder > {

    private List<TestModel> testList;
    private TestActivity testActivity = new TestActivity();
    private TextView testNo;
    private TextView topScore;
    private ProgressBar progressBar;

    public TestAdapter(List<TestModel> testList) {
        this.testList = testList;
    }



    public Object getItem(int position) {
        return null;
    }
    public int getItemCount() {
        return testList.size();
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @NonNull
    @Override

    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        int progress=testList.get(position).getTopScore();
        holder.setData(position,progress);
    }
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        testNo=view.findViewById(R.id.testNo);
        topScore=view.findViewById(R.id.scoretext);
        progressBar=view.findViewById(R.id.testProgressbar);
        View myview;
        if(view==null)
        {
            myview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_item_layout,viewGroup,false);

        }
        else {
            myview=view;
        }
        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testActivity.setTestChoice(i+1);
                Intent intent= new Intent(view.getContext(), StartTestActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        return myview;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            testNo=itemView.findViewById(R.id.testNo);
            topScore=itemView.findViewById(R.id.scoretext);
            progressBar=itemView.findViewById(R.id.testProgressbar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedtestId=getAdapterPosition()+1;
                    testActivity.setTestChoice(selectedtestId);
                    Intent intent= new Intent(itemView.getContext(), StartTestActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
        public void setData(int pos,int progress)
        {
            testNo.setText("Text No : "+String.valueOf((pos+1)));
            topScore.setText(String.valueOf(progress) +"%");
            progressBar.setProgress(progress);
        }
    }
}
