package com.example.sub3feb2023.domain;

import java.time.LocalDateTime;

public class Reservation extends Entity<Double>{
    private Long clientId;
    private Double hotelId;
    private LocalDateTime startDate;
    private Integer noNights;

    public Reservation(Double aDouble, Long clientId, Double hotelId, LocalDateTime startDate, Integer noNights) {
        super(aDouble);
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public void setHotelId(Double hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getNoNights() {
        return noNights;
    }

    public void setNoNights(Integer noNights) {
        this.noNights = noNights;
    }
}
