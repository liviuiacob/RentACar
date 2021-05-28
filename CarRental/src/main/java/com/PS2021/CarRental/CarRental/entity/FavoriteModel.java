package com.PS2021.CarRental.CarRental.entity;

public class FavoriteModel {
    private Long userId;
    private Long carId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public FavoriteModel(Long userId, Long carId) {
        this.userId = userId;
        this.carId = carId;
    }
}
