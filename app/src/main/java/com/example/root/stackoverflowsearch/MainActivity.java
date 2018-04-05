package com.example.root.stackoverflowsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText seacrhQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seacrhQuery = (EditText) findViewById(R.id.editText);
    }

    public void startSearching(View view){
        Intent searchIntent = new Intent(this,ResultActivity.class);
        searchIntent.putExtra("query",this.seacrhQuery.getText().toString());
        startActivity(searchIntent);
    }


}
