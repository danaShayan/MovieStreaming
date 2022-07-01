package com.example.moviestreaming.Activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviestreaming.R;

public class ShowSearchActivity extends AppCompatActivity {


    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search);

        title = findViewById(R.id.name);
        title.setText(SearchActivity.TITLE);
        Toast.makeText(this, SearchActivity.CATE_NAME, Toast.LENGTH_SHORT).show();

    }
}