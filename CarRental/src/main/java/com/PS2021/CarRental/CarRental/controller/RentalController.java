package com.PS2021.CarRental.CarRental.controller;

import com.PS2021.CarRental.CarRental.entity.*;
import com.PS2021.CarRental.CarRental.service.CarService;
import com.PS2021.CarRental.CarRental.service.RentalService;
import com.PS2021.CarRental.CarRental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private SimpMessagingTemplate smTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/api/Rentals")
    public List<Rental> getRentals() {
        List<Rental> Rentals = rentalService.retrieveRentals();
        return Rentals;
    }

    @GetMapping("/api/Rentals/merge")
    public List<RentalViewModel> getRentalsBun() {
        List<Rental> Rentals = rentalService.retrieveRentals();
        List <RentalViewModel> result = new ArrayList<>();
        for (Rental r : Rentals){
            result.add(new RentalViewModel(r.getId(), r.getUser().getUsername(), r.getCar().getName(), r.getFrom(), r.getTo()));
        }
        return result;
        // return Rentals;
    }

    @GetMapping("/api/Rentals/{RentalId}")
    public Rental getRental(@PathVariable(name = "RentalId") Long RentalId) {
        return rentalService.getRental(RentalId);
    }

    @PostMapping("/api/Rentals")
    public void saveRental(@RequestBody Rental rental) {
        rentalService.saveRental(rental);
        System.out.println("Rental Saved Successfully");
    }

    @DeleteMapping("/api/Rentals/{RentalId}")
    public void deleteRental(@PathVariable(name = "RentalId") Long RentalId) {
        rentalService.deleteRental(RentalId);
        System.out.println("Rental Deleted Successfully");
    }

    @PutMapping("/api/Rentals/update")
    public void updateRental(@RequestBody RentalModel rental) throws ParseException {
        Car car = carService.getCar(rental.getCarid());
        User user = userService.getUser(rental.getUserid());

        Date x= new SimpleDateFormat("dd/MM/yyyy").parse(rental.getFromdate());
        Date y= new SimpleDateFormat("dd/MM/yyyy").parse(rental.getTodate());

        Rental r = new Rental();
        r.setCar(car);
        r.setUser(user);
        r.setFrom(x);
        r.setTo(y);

        rentalService.saveRental(r);

        List<Rental> ceva = rentalService.retrieveRentals();
        for (Rental ce : ceva){
            if(ce.getUser().getId()==rental.getUserid()){
                car.getRentals().add(ce);
                user.getRentals().add(ce);
            }
        }
        carService.updateCar(car);
        userService.updateUser(user);
        this.smTemplate.convertAndSend("/topic/socket/rental/",
                "Another user just rented a car, refreshing page");

    }


    @GetMapping("/api/Rentals/car/{RentalId}")
    public String getRentalCar(@PathVariable(name = "RentalId") Long RentalId) {
        Rental x = rentalService.getRental(RentalId);
        return x.getCar().getName();
    }
    @GetMapping("/api/Rentals/user/{RentalId}")
    public String getRentalUser(@PathVariable(name = "RentalId") Long RentalId) {
        Rental x = rentalService.getRental(RentalId);
        return x.getUser().getUsername();
    }

    @PostMapping("api/Rental/get")
    public List<Car> getCarsDate(@RequestBody RentalModel r) throws ParseException {
        Date x= new SimpleDateFormat("dd/MM/yyyy").parse(r.getFromdate());
        Date y= new SimpleDateFormat("dd/MM/yyyy").parse(r.getTodate());
        List<Car> cars = carService.retrieveCars();
        List<Rental> rentals = rentalService.retrieveRentals();
        for (Rental re : rentals){

            if (!
                    ((re.getFrom().before(x) &&
                    re.getTo().before(y)) ||
                    (re.getFrom().after(x) &&
                    re.getTo().after((y)))) ){
                cars.remove(re.getCar());
            }
        }

        return cars;
    }

    @PostMapping("/api/Rentals/dacanumergeimibag/user")
    public Long getUserid(@RequestBody RentalModel rental) {
        List<Rental> nume = rentalService.retrieveRentals();
        for (Rental u : nume) {
            if (u.getUser().getId()==rental.getUserid()
                    && u.getCar().getId()==rental.getCarid()
            ) {
                return rental.getUserid();
            }
        }
        System.out.println("User logged in Successfully");
        return 0l;
    }

    @PostMapping("/api/Rentals/dacanumergeimibag/car")
    public Long getCarid(@RequestBody RentalModel rental) {
        List<Rental> nume = rentalService.retrieveRentals();
        for (Rental u : nume) {
            if (u.getUser().getId().equals(rental.getUserid())
                    && u.getCar().getId().equals(rental.getCarid())
            ) {
                return rental.getCarid();
            }
        }
        System.out.println("User logged in Successfully");
        return 0l;
    }

}