package com.example.moviestreaming.Database.DataSource;

import com.example.moviestreaming.Database.ModelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteRepository implements IFavoriteDateSource {

    private IFavoriteDateSource iFavoriteDateSource;
    public static FavoriteRepository instance;

    public FavoriteRepository(IFavoriteDateSource iFavoriteDateSource) {
        this.iFavoriteDateSource = iFavoriteDateSource;
    }

    public static FavoriteRepository getInstance(IFavoriteDateSource iFavoriteDateSource) {

        if (instance == null)
            instance = new FavoriteRepository(iFavoriteDateSource);

        return instance;
    }

    @Override
    public Flowable<List<Favorite>> getAllFavoriteItem() {

        return iFavoriteDateSource.getAllFavoriteItem();
    }

    @Override
    public void insertFavoriteItem(Favorite... favorites) {
        iFavoriteDateSource.insertFavoriteItem(favorites);
    }

    @Override
    public void deleteFavoriteItem(Favorite favorite) {
        iFavoriteDateSource.deleteFavoriteItem(favorite);
    }

    @Override
    public int isFavorite(int itemFavID) {
        return iFavoriteDateSource.isFavorite(itemFavID);
    }
}
