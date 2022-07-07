package com.example.moviestreaming.Database.DataSource;

import com.example.moviestreaming.Database.Local.FavoriteDao;
import com.example.moviestreaming.Database.ModelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDateSource {

    Flowable<List<Favorite>> getAllFavoriteItem();
    void insertFavoriteItem(Favorite... favorites);
    void deleteFavoriteItem(Favorite favorite);
    int isFavorite(int itemFavID);

}
