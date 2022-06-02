package com.example.moviestreaming.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.EpisodeAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.Episode;
import com.example.moviestreaming.R;

import java.util.ArrayList;
import java.util.List;

public class EpisodesActivity extends AppCompatActivity {

    public static final String ID_SEASON = "";
    public static final String SEASON_NUMBER = "season_number";
    public static final String LINK_MOVIE = "link_movie";

    Bundle bundle;
    Global global;
    RequestQueue requestQueue;
    int id;

    TextView season_txt;
    RecyclerView recyclerView_episode;
    List<Episode> list = new ArrayList<>();
    EpisodeAdapter episodeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        bundle = getIntent().getExtras();
        global = new Global();
        requestQueue = Volley.newRequestQueue(this);
        id = Integer.parseInt(bundle.getString(ID_SEASON));

        season_txt = findViewById(R.id.txt_seasons);
        season_txt.setText(new StringBuilder("Season " + bundle.getString(SEASON_NUMBER)));

        recyclerView_episode = findViewById(R.id.episodes_recyclerView);
        recyclerView_episode.setHasFixedSize(true);
        recyclerView_episode.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        episodeAdapter = new EpisodeAdapter(this, list);
        global.getEpisode(this, requestQueue, id + "", recyclerView_episode, list);
        recyclerView_episode.setAdapter(episodeAdapter);
    }
}