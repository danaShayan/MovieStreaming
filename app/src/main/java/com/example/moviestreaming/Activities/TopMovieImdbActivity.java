package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.TopMovieIMDbCompleteAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.TopMovieIMDb;
import com.example.moviestreaming.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviestreaming.Activities.HomeActivity.CATEGORY_KEY;

public class TopMovieImdbActivity extends AppCompatActivity {


    RequestQueue requestQueue;
    Global global;
    ImageView back;
    RecyclerView recyclerView_topNewMovieComplete;
    TopMovieIMDbCompleteAdapter topMovieIMDbCompleteAdapter;
    List<TopMovieIMDb> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movie_imdb);

        requestQueue = Volley.newRequestQueue(this);
        global = new Global();

        recyclerView_topNewMovieComplete = findViewById(R.id.topMovieIMDb_recyclerViewComplete);
        back = findViewById(R.id.back);

        recyclerView_topNewMovieComplete.setHasFixedSize(true);
        recyclerView_topNewMovieComplete.setLayoutManager(new LinearLayoutManager(this));
        topMovieIMDbCompleteAdapter = new TopMovieIMDbCompleteAdapter(this, list);
        recyclerView_topNewMovieComplete.setAdapter(topMovieIMDbCompleteAdapter);
        global.getTopMovieIMDbComplete(this, requestQueue, CATEGORY_KEY, recyclerView_topNewMovieComplete, list);


    }
}