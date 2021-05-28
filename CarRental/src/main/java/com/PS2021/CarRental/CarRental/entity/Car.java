package com.PS2021.CarRental.CarRental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue
	private Long id;
    private String name;
    private int yearOfFabrication;
    private int price;
    @Column(columnDefinition = "TEXT")
    private String coverURL;

    @OneToMany
    //@JsonIgnoreProperties("rentals")
    private List<Rental> rentals = new ArrayList<>();


    public Car(){
    }

    public Car(Long id, String name, int yearOfFabrication, int price, String coverURL){
        super();
        this.id=id;
        this.name=name;
        this.yearOfFabrication=yearOfFabrication;
        this.price=price;
        this.coverURL=coverURL;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id2) {
        this.id = id2;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfFabrication() {
        return yearOfFabrication;
    }

    public void setYearOfFabrication(int yearOfFabrication) {
        this.yearOfFabrication = yearOfFabrication;
    }
}
