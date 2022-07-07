package com.example.moviestreaming.Database.Local;

import com.example.moviestreaming.Database.DataSource.IFavoriteDateSource;
import com.example.moviestreaming.Database.ModelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteDataSource implements IFavoriteDateSource {

    private FavoriteDao favoriteDao;

    public static FavoriteDataSource instance;

    public FavoriteDataSource(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public static FavoriteDataSource getInstance(FavoriteDao favoriteDao) {

        if (instance == null)
            instance = new FavoriteDataSource(favoriteDao);
        return instance;
    }


    @Override
    public Flowable<List<Favorite>> getAllFavoriteItem() {
        return favoriteDao.getAllFavoriteItem();
    }

    @Override
    public void insertFavoriteItem(Favorite... favorites) {
        favoriteDao.insertFavoriteItem(favorites);
    }

    @Override
    public void deleteFavoriteItem(Favorite favorite) {
        favoriteDao.deleteFavoriteItem(favorite);
    }

    @Override
    public int isFavorite(int itemFavID) {
        return favoriteDao.isFavorite(itemFavID);
    }
}
