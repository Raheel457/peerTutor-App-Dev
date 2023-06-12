package com.example.peertutor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.ViewHolder> {
    Context context;
    ArrayList<QuestionModel> questionArray;
    RcAdapter(Context context, ArrayList<QuestionModel> questionArray){
        this.context= context;
        this.questionArray = questionArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_rview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionModel question = questionArray.get(position);
        holder.title.setText(question.title);
        holder.description.setText(question.description);

    }

    @Override
    public int getItemCount() {
        return questionArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.myTitle);
            description = itemView.findViewById(R.id.myDescription);
        }
    }
}
