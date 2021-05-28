package com.PS2021.CarRental.CarRental.entity;

import java.util.Date;

public class RentalModel {
    private String fromdate;
    private String todate;
    private Long carid;
    private Long userid;


    public RentalModel(String fromdate, String todate, Long carid, Long userid) {
        this.fromdate = fromdate;
        this.todate = todate;
    }

    public String getFromdate() {
        return fromdate;
    }

    public Long getCarid() {
        return carid;
    }

    public void setCarid(Long carid) {
        this.carid = carid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public RentalModel() {
    }


}
