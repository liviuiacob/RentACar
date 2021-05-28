package com.PS2021.CarRental.CarRental.controller;

import com.PS2021.CarRental.CarRental.entity.*;
import com.PS2021.CarRental.CarRental.service.CarService;
import com.PS2021.CarRental.CarRental.service.FavoriteService;
import com.PS2021.CarRental.CarRental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CarService carService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/Users")
    public List<User> getUsers() {
        List<User> Users = userService.retrieveUsers();
        return Users;
    }

    @GetMapping("/api/Users/{UserId}")
    public User getUser(@PathVariable(name = "UserId") Long UserId) {
        return userService.getUser(UserId);
    }

    @PostMapping("/api/Users")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
        System.out.println("User Saved Successfully");
    }

    @DeleteMapping("/api/Users/{UserId}")
    public void deleteUser(@PathVariable(name = "UserId") Long UserId) {
        userService.deleteUser(UserId);
        System.out.println("User Deleted Successfully");
    }

    @PutMapping("/api/Users/{UserId}")
    public void updateUser(@RequestBody User user, @PathVariable(name = "UserId") Long UserId) {
        User emp = userService.getUser(UserId);
        if (emp != null) {
            userService.updateUser(user);
        }

    }

    @PutMapping("/api/Users/ceva/{UserId}")
    public void updateUser(@RequestBody FavoriteModel user, @PathVariable(name = "UserId") Long UserId) {
        User emp = userService.getUser(UserId);
        if (emp != null) {
            Car c = new Car();
            c=carService.getCar(user.getCarId());
            Favorite x = new Favorite();
            x.setCar(c);
            favoriteService.updateFavorite(x);
            User u = userService.getUser(UserId);
            int ok=1;
            for (Favorite f : u.getFav()){
                if(f.getCar().getId()==c.getId()) {
                    ok = 0;
                }
            }
            if(ok==1){
                u.getFav().add(x);
                userService.updateUser(u);
            }
        }

    }

    @PutMapping("/api/Users/remove/{UserId}")
    public void removeFavorite(@RequestBody FavoriteModel user, @PathVariable(name = "UserId") Long UserId) {
        User emp = userService.getUser(UserId);
        if (emp != null) {
            Car c = new Car();
            c=carService.getCar(user.getCarId());
            User u = userService.getUser(UserId);
            int ok=1;
            Favorite desters = new Favorite();
            for (Favorite f : u.getFav()){
                if(f.getCar().getId()==c.getId()) {
                    desters=f;
                    ok = 0;
                }
            }
            if(ok==0){
                u.getFav().remove(desters);
                userService.updateUser(u);
                favoriteService.deleteFavorite(desters.getId());
            }
        }

    }

    @PostMapping("/api/Users/login")
    public int loginUser(@RequestBody LoginModel user) {
        List<User> nume =getUsers();
        for (User u : nume) {
            if (u.getUsername().equals(user.getUsername())
                    && u.getPassword().equals(user.getPassword())) {
                if(u.isAdmin()==true)
                    return 2;
                return 1;
            }
        }
        System.out.println("User logged in Successfully");
        return 0;
    }

    @GetMapping("/api/Users/get/{username}")
    public Long getUserByName(@PathVariable(name = "username") String username) {
        return userService.getUserByName(username);
    }

    @PostMapping("/api/Users/rentals")
    public List<Car> getDateCars (Date fromdate, Date todate){
        return userService.findAllAvailibleCars(fromdate, todate);
    }

}
