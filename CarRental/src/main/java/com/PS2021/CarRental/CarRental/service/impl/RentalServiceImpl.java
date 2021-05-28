package com.PS2021.CarRental.CarRental.service.impl;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.entity.Rental;
import com.PS2021.CarRental.CarRental.repository.CarRepository;
import com.PS2021.CarRental.CarRental.repository.RentalRepository;
import com.PS2021.CarRental.CarRental.service.CarService;
import com.PS2021.CarRental.CarRental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public void setRentalRepository(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> retrieveRentals() {
        List<Rental> cars = rentalRepository.findAll();
        return cars;
    }

    public Rental getRental(Long carId) {
        Optional<Rental> optRental = rentalRepository.findById(carId);
        return optRental.get();
    }

    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public void deleteRental(Long rentalId) {
        rentalRepository.deleteById(rentalId);
    }

    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }
}
