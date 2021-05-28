package com.PS2021.CarRental.CarRental.service.impl;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.PS2021.CarRental.CarRental.entity.RentalModel;
import com.PS2021.CarRental.CarRental.entity.User;
import com.PS2021.CarRental.CarRental.repository.UserRepository;
import com.PS2021.CarRental.CarRental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> retrieveUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getUser(Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        return optUser.get();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Long getUserByName(String name) {
        List<User> users = userRepository.findAll();
        for(User u: users)
            if(u.getUsername().equals(name)) {
                System.out.println(u.getId());
                return u.getId();
            }
        return null;
    }

    public List<Car> findAllAvailibleCars(Date fromdate, Date todate){
        return userRepository.findAllAvailibleCars(fromdate, todate);
    }

}
