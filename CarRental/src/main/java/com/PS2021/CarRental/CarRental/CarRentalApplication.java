package com.PS2021.CarRental.CarRental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.repository.CarRepository;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(CarRentalApplication.class, args);
        CarRepository r = c.getBean(CarRepository.class);
        //Car p = new Car((long) 20, "alune", 10);
        //r.save(p);
	}

}
