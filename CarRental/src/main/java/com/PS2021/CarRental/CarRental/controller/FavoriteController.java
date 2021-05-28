package com.PS2021.CarRental.CarRental.controller;


import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.entity.Favorite;
import com.PS2021.CarRental.CarRental.service.CarService;
import com.PS2021.CarRental.CarRental.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    public void setFavoriteService(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/api/Favorites")
    public List<Favorite> getFavorites() {
        List<Favorite> Favorite = favoriteService.retrieveFavorites();
        return Favorite;
    }

    @GetMapping("/api/Favorite/{FavoriteId}")
    public Favorite getFavorite(@PathVariable(name = "FavoriteId") Long favoriteId) {
        return favoriteService.getFavorite(favoriteId);
    }

    @PostMapping("/api/Favorites")
    public void saveFavorite(@RequestBody Favorite favorite) {
        favoriteService.saveFavorite(favorite);
        System.out.println("Favorite Saved Successfully");
    }

    @DeleteMapping("/api/Favorite/{FavoriteId}")
    public void deleteFavorite(@PathVariable(name = "FavoriteId") Long FavoriteId) {
        favoriteService.deleteFavorite(FavoriteId);
        System.out.println("Favorite Deleted Successfully");
    }

    @PutMapping("/api/Favorite/{FavoriteId}")
    public void updateFavorite(@RequestBody Favorite favorite, @PathVariable(name = "FavoriteId") Long FavoriteId) {
        Favorite emp = favoriteService.getFavorite(FavoriteId);
        if (emp != null) {
            favoriteService.updateFavorite(favorite);
        }

    }
}
