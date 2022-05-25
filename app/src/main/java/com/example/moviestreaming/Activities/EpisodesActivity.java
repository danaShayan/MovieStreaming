package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.moviestreaming.R;

public class EpisodesActivity extends AppCompatActivity {

    public static final String ID_SEASON = "";
    public static final String SEASON_NUMBER = "season_number";

    Bundle bundle;
    int id;

    TextView season_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        bundle = getIntent().getExtras();
        id = Integer.parseInt(bundle.getString(ID_SEASON));

        season_txt = findViewById(R.id.txt_seasons);
        season_txt.setText(new StringBuilder("Season " + bundle.getString(SEASON_NUMBER)));

    }
}