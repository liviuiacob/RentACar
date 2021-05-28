package com.PS2021.CarRental.CarRental.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.repository.CarRepository;
import com.PS2021.CarRental.CarRental.service.CarService;


@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	public void setCarRepository(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public List<Car> retrieveCars() {
		List<Car> cars = carRepository.findAll();
		return cars;
	}

	public Car getCar(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		return optCar.get();
	}

	public void saveCar(Car car) {
		carRepository.save(car);
	}

	public void deleteCar(Long carId) {
		carRepository.deleteById(carId);
	}

	public void updateCar(Car car) {
		carRepository.save(car);
	}

}
