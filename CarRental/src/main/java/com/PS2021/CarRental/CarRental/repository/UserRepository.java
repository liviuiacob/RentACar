package com.PS2021.CarRental.CarRental.repository;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.entity.Rental;
import com.PS2021.CarRental.CarRental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT *"
            +"FROM Cars c JOIN Rentals r"
            + "ON c = r.car where (r.fromdate not between :fromdate and :todate) " +
            "AND (r.todate not between :fromdate and :todate)", nativeQuery = true)
    public List<Car> findAllAvailibleCars(@Param("fromdate") Date fromdate,@Param("todate") Date todate);
}
