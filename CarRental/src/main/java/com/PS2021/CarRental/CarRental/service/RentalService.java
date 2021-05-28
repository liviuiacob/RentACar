package com.PS2021.CarRental.CarRental.service;

import com.PS2021.CarRental.CarRental.entity.Rental;

import java.util.List;

public interface RentalService {
    public List<Rental> retrieveRentals();

    public Rental getRental(Long rentalId);

    public void saveRental(Rental rental);

    public void deleteRental(Long rentalId);

    public void updateRental(Rental rental);
}
