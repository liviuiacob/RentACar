package com.PS2021.CarRental.CarRental.service.impl;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.entity.Favorite;
import com.PS2021.CarRental.CarRental.repository.CarRepository;
import com.PS2021.CarRental.CarRental.repository.FavoriteRepository;
import com.PS2021.CarRental.CarRental.service.CarService;
import com.PS2021.CarRental.CarRental.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public void setFavoriteRepository(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> retrieveFavorites() {
        List<Favorite> favorites = favoriteRepository.findAll();
        return favorites;
    }

    public Favorite getFavorite(Long favoriteId) {
        Optional<Favorite> optFavorite = favoriteRepository.findById(favoriteId);
        return optFavorite.get();
    }

    public void saveFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    public void updateFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

}
