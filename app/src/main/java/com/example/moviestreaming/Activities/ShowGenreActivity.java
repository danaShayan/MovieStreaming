package com.example.moviestreaming.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.ShowGenreAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.ShowGenre;
import com.example.moviestreaming.R;

import java.util.ArrayList;
import java.util.List;

public class ShowGenreActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    Global global;


    TextView name;
    RecyclerView genreRecyclerView;
    List<ShowGenre> list = new ArrayList<>();

    ShowGenreAdapter showGenreAdapter;
    String genre_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_genre);


        requestQueue = Volley.newRequestQueue(this);
        global = new Global();

        Bundle bundle = getIntent().getExtras();
        genre_name = bundle.getString("name");

        name = findViewById(R.id.name);
        genreRecyclerView = findViewById(R.id.showGenre_recyclerView);

        name.setText(genre_name);
        genreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        genreRecyclerView.setHasFixedSize(true);
        showGenreAdapter = new ShowGenreAdapter(this, list);
        global.getShowGenre(this, requestQueue, genre_name, genreRecyclerView, list);

    }
}