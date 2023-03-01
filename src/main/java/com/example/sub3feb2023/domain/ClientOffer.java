package com.example.sub3feb2023.domain;

import java.util.Date;

public class ClientOffer {
    private String numeHotel;
    private String numeLocatie;
    private Date startDate;
    private Date endDate;

    public ClientOffer(String numeHotel, String numeLocatie, Date startDate, Date endDate) {
        this.numeHotel = numeHotel;
        this.numeLocatie = numeLocatie;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getNumeHotel() {
        return numeHotel;
    }

    public void setNumeHotel(String numeHotel) {
        this.numeHotel = numeHotel;
    }

    public String getNumeLocatie() {
        return numeLocatie;
    }

    public void setNumeLocatie(String numeLocatie) {
        this.numeLocatie = numeLocatie;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
