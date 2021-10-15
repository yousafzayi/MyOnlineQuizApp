package com.example.quizapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.DbQuerry;
import com.example.quizapp.Models.QuestionModel;
import com.example.quizapp.R;

import java.util.List;

public class QuestioningAdapter extends RecyclerView.Adapter<QuestioningAdapter.ViewHolder> {

    private List<QuestionModel> questionList;


    public QuestioningAdapter(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.queston_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.setData(i);

    }

    @Override
    public int getItemCount() {
        return questionList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private Button optionA, optionB, optionC, optionD, previousSelectedButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            optionA = itemView.findViewById(R.id.option_A);
            optionB = itemView.findViewById(R.id.option_B);
            optionC = itemView.findViewById(R.id.option_C);
            optionD = itemView.findViewById(R.id.option_D);
            question = itemView.findViewById(R.id.tv_question);
            previousSelectedButton = null;
        }

        private void setData(final int position) {
            question.setText(questionList.get(position).getQuestion());
            optionA.setText(questionList.get(position).getOptionA());
            optionB.setText(questionList.get(position).getOptionB());
            optionC.setText(questionList.get(position).getOptionC());
            optionD.setText(questionList.get(position).getOptionD());

            optionA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption(optionA,1,position);

                }
            });
            optionB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption(optionB,2,position);


                }
            });
            optionC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption(optionC,3,position);

                }
            });
            optionD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption(optionD,4,position);

                }
            });
        }

        public void selectOption(Button btn, int option_num, int quest_Id) {
            if (previousSelectedButton == null) {
                btn.setBackgroundResource(R.drawable.selected_btn);
                DbQuerry.g_questionList.get(quest_Id).setSelectedAns(option_num);
                previousSelectedButton = btn;
            }
            else {
                if(previousSelectedButton.getId()==btn.getId())
                {
                    btn.setBackgroundResource(R.drawable.unselected_btn);
                    DbQuerry.g_questionList.get(quest_Id).setSelectedAns(-1);
                    previousSelectedButton=null;
                }
                else
                    {
                    previousSelectedButton.setBackgroundResource(R.drawable.unselected_btn);
                    btn.setBackgroundResource(R.drawable.selected_btn);
                    DbQuerry.g_questionList.get(quest_Id).setSelectedAns(option_num);
                    previousSelectedButton=btn;

                }


            }
        }
    }
}
