package com.PS2021.CarRental.CarRental.service;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.entity.RentalModel;
import com.PS2021.CarRental.CarRental.entity.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    public List<User> retrieveUsers();

    public User getUser(Long userId);

    public void saveUser(User user);

    public void deleteUser(Long userId);

    public void updateUser(User user);

    public Long getUserByName(String name);

    public List<Car> findAllAvailibleCars(Date fromdate, Date todate);
}
