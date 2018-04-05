package com.example.root.stackoverflowsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.stackoverflowsearch.Models.Item;
import com.example.root.stackoverflowsearch.Models.Respone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity implements ResultAdapter.onQuestionClickListener{


    ResultAdapter adapter;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        String query = getIntent().getStringExtra("query");


        StackoverflowService serviceObj = ServiceInstance.getServiceInstance();
        Call<Respone> requestObj = serviceObj.search(query,"stackoverflow");
        requestObj.enqueue(new Callback<Respone>() {
            @Override
            public void onResponse(Call<Respone> call, Response<Respone> response) {
                if(response.body().getItems().size() > 0) {
                    recyclerView = (RecyclerView) findViewById(R.id.recycler);
                    ProgressBar loader = (ProgressBar) findViewById(R.id.progressBar);
                    loader.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    layoutManager = new LinearLayoutManager(ResultActivity.this);
                    adapter = new ResultAdapter(response.body().getItems(),ResultActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }else {
                    ProgressBar loader = (ProgressBar) findViewById(R.id.progressBar);
                    loader.setVisibility(View.GONE);
                    TextView noData = (TextView) findViewById(R.id.no_result);
                    noData.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<Respone> call, Throwable t) {
                ProgressBar loader = (ProgressBar) findViewById(R.id.progressBar);
                loader.setVisibility(View.GONE);
                TextView noData = (TextView) findViewById(R.id.no_result);
                noData.setText(getString(R.string.errorMSG));
                noData.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onQuestionClick(Item resultItem) {
        Intent detailsIntent = new Intent(ResultActivity.this,DetailsActivity.class);
        detailsIntent.putExtra("title",resultItem.getTitle());
        detailsIntent.putExtra("author",resultItem.getOwner().getDisplayName());
        detailsIntent.putExtra("isAnswered",resultItem.getIsAnswered());
        detailsIntent.putExtra("views",String.valueOf(resultItem.getViewCount()));
        detailsIntent.putExtra("answers",String.valueOf(resultItem.getAnswerCount()));
        detailsIntent.putExtra("score",String.valueOf(resultItem.getScore()));
        detailsIntent.putExtra("link",resultItem.getLink());
//        Toast.makeText(this,String.valueOf(resultItem.getViewCount()),Toast.LENGTH_LONG).show();

        startActivity(detailsIntent);
    }
}
