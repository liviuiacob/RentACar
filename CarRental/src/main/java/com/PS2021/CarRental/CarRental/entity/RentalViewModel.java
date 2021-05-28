
package com.PS2021.CarRental.CarRental.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

public class RentalViewModel {
    private Long id;
    private String user;
    private String car;

    private Date fromdate;
    private Date todate;

    public RentalViewModel(){

    }

    public RentalViewModel(Long id, String user, String car, Date from, Date to) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
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


