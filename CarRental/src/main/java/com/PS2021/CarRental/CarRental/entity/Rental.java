
package com.PS2021.CarRental.CarRental.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user= new User();

    @ManyToOne
    @JsonIgnore
    private Car car=new Car();

    private Date fromdate;
    private Date todate;

    public Rental(){

    }

    public Rental(Long id, User user, Car car, Date from, Date to) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.fromdate = from;
        this.todate = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getFrom() {
        return fromdate;
    }

    public void setFrom(Date from) {
        this.fromdate = from;
    }

    public Date getTo() {
        return todate;
    }

    public void setTo(Date to) {
        this.todate = to;
    }
}


