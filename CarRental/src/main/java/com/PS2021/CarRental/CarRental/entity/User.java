package com.PS2021.CarRental.CarRental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private boolean admin;

    @OneToMany
    private List<Favorite> fav = new ArrayList<>();

    @OneToMany
    //@JsonIgnoreProperties("rentals")
    private List<Rental> rentals= new ArrayList<>();


    public User() { }

    public User(Long id, String username, String password, boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public List<Favorite> getFav() {
        return fav;
    }

    public void setFav(List<Favorite> fav) {
        this.fav = fav;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


}
