package com.PS2021.CarRental.CarRental.repository;

import com.PS2021.CarRental.CarRental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
