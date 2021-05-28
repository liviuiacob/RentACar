package com.PS2021.CarRental.CarRental.service;

import java.util.List;

import com.PS2021.CarRental.CarRental.entity.Car;

public interface CarService {

	public List<Car> retrieveCars();

	public Car getCar(Long carId);

	public void saveCar(Car car);

	public void deleteCar(Long carId);

	public void updateCar(Car var);

}
