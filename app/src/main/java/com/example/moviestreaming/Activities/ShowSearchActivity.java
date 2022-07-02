package com.example.moviestreaming.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.Adapter.SearchAdapter;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.Model.AllInformation;
import com.example.moviestreaming.R;

import java.util.ArrayList;
import java.util.List;

public class ShowSearchActivity extends AppCompatActivity {


    TextView title;
    androidx.appcompat.widget.SearchView edit_search;
    RecyclerView recyclerView_search;
    SearchAdapter searchAdapter;
    List<AllInformation> searchList = new ArrayList<>();
    Global global;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search);


        init();

        title.setText(SearchActivity.TITLE);

        recyclerView_search.setHasFixedSize(true);
        recyclerView_search.setLayoutManager(new GridLayoutManager(this, 3));

        edit_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                searchList.removeAll(searchList);


                if (newText.isEmpty()) {
                    searchAdapter = new SearchAdapter(ShowSearchActivity.this, searchList);
                    recyclerView_search.setAdapter(searchAdapter);

                } else {
                    if (!SearchActivity.TITLE.equals("All Movie"))
                        global.getSearch(ShowSearchActivity.this, requestQueue, SearchActivity.CATE_NAME + "&&name=" + newText, recyclerView_search, searchList);
                    else
                        global.getAllSearch(ShowSearchActivity.this, requestQueue, newText, recyclerView_search, searchList);


                    searchAdapter = new SearchAdapter(ShowSearchActivity.this, searchList);
                    recyclerView_search.setAdapter(searchAdapter);

                }


                return false;
            }
        });


    }

    private void init() {
        global = new Global();
        requestQueue = Volley.newRequestQueue(this);

        title = findViewById(R.id.name);
        edit_search = findViewById(R.id.edit_search);
        recyclerView_search = findViewById(R.id.recyclerView_search);

    }


}