package com.PS2021.CarRental.CarRental.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.PS2021.CarRental.CarRental.Factory.ReportFactory;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.exception.CarNotFoundException;
import com.PS2021.CarRental.CarRental.repository.CarRepository;
import com.PS2021.CarRental.CarRental.service.CarService;

import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin("*")
public class CarController {

	@Autowired
	private CarService carService;

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/api/Cars")
	public List<Car> getCars() {
		List<Car> Cars = carService.retrieveCars();
		return Cars;
	}

	@GetMapping("/api/Cars/{CarId}")
	public Car getCar(@PathVariable(name = "CarId") Long CarId) {
		return carService.getCar(CarId);
	}

	@PostMapping("/api/Cars")
	public void saveCar(@RequestBody Car car) {
		carService.saveCar(car);
		System.out.println("Car Saved Successfully");
	}

	@DeleteMapping("/api/Cars/{CarId}")
	public void deleteCar(@PathVariable(name = "CarId") Long CarId) {
		carService.deleteCar(CarId);
		System.out.println("Car Deleted Successfully");
	}

	@PutMapping("/api/Cars/{CarId}")
	public void updateCar(@RequestBody Car car, @PathVariable(name = "CarId") Long CarId) {
		Car emp = carService.getCar(CarId);
		if (emp != null) {
			carService.updateCar(car);
		}

	}

	@PutMapping("/generatePdf/{CarId}")
	public String generate(@PathVariable(name = "CarId") Long CarId) throws URISyntaxException, DocumentException, org.dom4j.DocumentException, IOException {
		return new ReportFactory().createReport("pdf").generatePDF(carService.getCar(CarId));
	}

}