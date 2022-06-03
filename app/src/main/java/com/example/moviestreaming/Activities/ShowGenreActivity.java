package com.example.moviestreaming.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviestreaming.R;

public class ShowGenreActivity extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_genre);

        Bundle bundle = getIntent().getExtras();
        name = findViewById(R.id.name);
        name.setText(bundle.getString("name"));
    }
}