package com.example.appquiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.R;
import com.example.appquiz.activity.QuestionsActivity;
import com.example.appquiz.model.QuestionModel;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private List<QuestionModel> questionList;
    private QuestionsActivity quesAc=new QuestionsActivity();
    public QuestionsAdapter(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.question_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ques;
        private Button optionA,optionB,optionC,optionD,prevSelectB;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ques=itemView.findViewById(R.id.tv_question);
            optionA=itemView.findViewById(R.id.optionA);
            optionB=itemView.findViewById(R.id.optionB);
            optionC=itemView.findViewById(R.id.optionC);
            optionD=itemView.findViewById(R.id.optionD);
            prevSelectB=null;
        }
        private void setData(final int pos)
        {

//            System.out.println(questionList.get(pos).getQuestion()+"]]]]]]]]]]]]]]]]]"
//                    +questionList.get(pos).getOptionA()+"]]]]]]]]]]]]]]]]]"
//                    +questionList.get(pos).getOptionB()+"]]]]]]]]]]]]]]]]]"
//                    +questionList.get(pos).getOptionC()+"]]]]]]]]]]]]]]]]]"
//                    +questionList.get(pos).getOptionD());
//            for (QuestionModel q:questionList)
//            {
//                System.out.println(q.getId()+"]]]]]]]]]]]"+q.getAnsChoice());
//            }
            ques.setText(questionList.get(pos).getQuestion());
            optionA.setText(questionList.get(pos).getOptionA());
            optionB.setText(questionList.get(pos).getOptionB());
            optionC.setText(questionList.get(pos).getOptionC());
            optionD.setText(questionList.get(pos).getOptionD());

            optionA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption(optionA,1,pos);
                }
            });

            optionB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption(optionB,2,pos);
                }
            });
            optionC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption(optionC,3,pos);

                }
            });
            optionD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption(optionD,4,pos);
                }
            });
        }
//        private void checkBtn(Button btn)
//        {
//            for (QuestionModel q:questionList)
//            {
//                if(q.getAnsChoice()==0)
//                {
//                    btn.setBackgroundResource(R.drawable.unselected_btn);
//                }
//            }
//        }
        private void selectOption(Button btn,int option_num,int quesID)
        {
            if(prevSelectB==null)
            {
                btn.setBackgroundResource(R.drawable.selected_btn);
                prevSelectB=btn;
                for(QuestionModel s:questionList)
                {
                    if(s.getId()==quesID+1)
                    {
                        s.setAnsChoice(option_num);
                    }
                }

            }
            else {
                if(prevSelectB.getId()==btn.getId())
                {
                    btn.setBackgroundResource(R.drawable.unselected_btn);
                    for(QuestionModel s:questionList)
                    {
                        if(s.getId()==quesID+1)
                        {
                            s.setAnsChoice(0);
                        }
                    }
                    prevSelectB=null;
                }
                else{
                    prevSelectB.setBackgroundResource(R.drawable.unselected_btn);
                    btn.setBackgroundResource(R.drawable.selected_btn);
                    for(QuestionModel s:questionList)
                    {
                        if(s.getId()==quesID+1)
                        {
                            s.setAnsChoice(option_num);
                        }
                    }
                    prevSelectB=btn;
                }
            }
        }


    }
}
