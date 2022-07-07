package com.example.moviestreaming.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreaming.Adapter.FavoriteAdapter;
import com.example.moviestreaming.Database.ModelDB.Favorite;
import com.example.moviestreaming.Global.Global;
import com.example.moviestreaming.R;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView favorite_recyclerView;
    FavoriteAdapter favoriteAdapter;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        favorite_recyclerView = findViewById(R.id.favorite_recyclerView);
        favorite_recyclerView.setHasFixedSize(true);
        favorite_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Global.favoriteRepository.getAllFavoriteItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Favorite>>() {
                    @Override
                    public void accept(List<Favorite> favorites) throws Exception {
                        favoriteAdapter = new FavoriteAdapter(FavoriteActivity.this, favorites);
                        favorite_recyclerView.setAdapter(favoriteAdapter);
                    }
                }));
    }
}