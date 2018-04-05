package com.example.root.stackoverflowsearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class DetailsActivity extends AppCompatActivity {

    TextView title,answered,author,viewsCount,answersCount,score;
    String questionTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        Intent currentIntent = getIntent();
        title =  findViewById(R.id.question_title);
        title.setText(currentIntent.getStringExtra("title"));
        author =  findViewById(R.id.quetion_author);
        author.setText(currentIntent.getStringExtra("author"));

        viewsCount = findViewById(R.id.question_views_count);
        viewsCount.setText(String.valueOf(currentIntent.getStringExtra("views")));

        answersCount =  findViewById(R.id.question_answers_count);
        answersCount.setText(String.valueOf(currentIntent.getStringExtra("answers")));

        score = findViewById(R.id.question_score);
        score.setText(String.valueOf(currentIntent.getStringExtra("score")));

        if(currentIntent.getBooleanExtra("isAnswered",true)){
            answered = findViewById(R.id.quetion_answered);
            answered.setText("[answered]");
            Toast.makeText(this,currentIntent.getStringExtra("views"),Toast.LENGTH_LONG).show();
        }
        questionTitle = currentIntent.getStringExtra("link");
    }

    public void visitQuestionLink(View view){
        Uri questionLink = Uri.parse(this.questionTitle);
        Intent webIntent = new Intent(Intent.ACTION_VIEW,questionLink);
        if(webIntent.resolveActivity(getPackageManager()) != null){
            startActivity(webIntent);
        }
    }
}
