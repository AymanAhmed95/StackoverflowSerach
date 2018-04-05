package com.example.root.stackoverflowsearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.stackoverflowsearch.Models.Item;

import java.util.List;

/**
 * Created by root on 3/2/18.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {


    List<Item> resultItems;
    private int itemsCount;
    final private onQuestionClickListener questionClickListener;

    public interface onQuestionClickListener{
        void onQuestionClick(Item resultItem);
    }

    public ResultAdapter(List<Item> resultItems,onQuestionClickListener listener) {
        this.resultItems = resultItems;
        this.itemsCount = resultItems.size();
        this.questionClickListener = listener;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item,null);
        ResultViewHolder holder = new ResultViewHolder(cardView);
        return  holder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.resultTitle.setText(this.resultItems.get(position).getTitle());
        holder.resultAnswerCount.setText(String.valueOf(this.resultItems.get(position).getAnswerCount()));
        holder.resultScore.setText(String.valueOf(this.resultItems.get(position).getScore()));
    }


    @Override
    public int getItemCount() {
        return this.itemsCount;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public  TextView resultTitle,resultAnswerCount,resultScore;

        public ResultViewHolder(View itemView) {
            super(itemView);
            this.resultTitle =  itemView.findViewById(R.id.item_title);
            this.resultAnswerCount = itemView.findViewById(R.id.item_answer_count);
            this.resultScore = itemView.findViewById(R.id.item_score);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int itemPosition = getAdapterPosition();
            Item resultItem = resultItems.get(itemPosition);
            questionClickListener.onQuestionClick(resultItem);
        }

    }
}
