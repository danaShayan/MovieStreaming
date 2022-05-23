package com.example.moviestreaming.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.SeriesCompleteAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.Series;
import com.example.moviestreaming.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviestreaming.Activities.HomeActivity.CATEGORY_KEY;

public class SeriesActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    Global global;
    RecyclerView recyclerView;
    SeriesCompleteAdapter seriesCompleteAdapter;
    List<Series> list = new ArrayList<>();
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);


        requestQueue = Volley.newRequestQueue(this);
        global = new Global();
        recyclerView = findViewById(R.id.series_recyclerViewComplete);
        back = findViewById(R.id.back);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(seriesCompleteAdapter);
        seriesCompleteAdapter = new SeriesCompleteAdapter(this, list);
        global.getSeriesComplete(this, requestQueue, CATEGORY_KEY, recyclerView, list);


    }
}