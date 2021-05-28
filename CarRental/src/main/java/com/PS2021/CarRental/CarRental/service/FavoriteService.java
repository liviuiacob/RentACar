package com.PS2021.CarRental.CarRental.service;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    public List<Favorite> retrieveFavorites();

    public Favorite getFavorite(Long favoriteId);

    public void saveFavorite(Favorite favorite);

    public void deleteFavorite(Long favoriteId);

    public void updateFavorite(Favorite favorite);
}
