package com.PS2021.CarRental.CarRental.entity;

import javax.persistence.*;

@Entity
@Table(name="favorites")
public class Favorite {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Car car=new Car();

    public Favorite() {
    }

    public Favorite(Long id, Car car) {
        this.id = id;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
