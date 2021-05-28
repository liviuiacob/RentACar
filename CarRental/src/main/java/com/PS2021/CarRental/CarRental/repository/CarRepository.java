package com.PS2021.CarRental.CarRental.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PS2021.CarRental.CarRental.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
