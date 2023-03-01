package com.example.sub3feb2023.domain;

public class Hotel extends Entity<Double>{
    private Double locationId;
    private String hotelName;

    public Double getLocationId() {
        return locationId;
    }

    public void setLocationId(Double locationId) {
        this.locationId = locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public TypeENUM getType() {
        return type;
    }

    public void setType(TypeENUM type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "locationId=" + locationId +
                ", hotelName='" + hotelName + '\'' +
                ", noRooms=" + noRooms +
                ", pricePerNight=" + pricePerNight +
                ", type=" + type +
                '}';
    }

    private int noRooms;
    private Double pricePerNight;
    private TypeENUM type;

    public Hotel(Double aDouble, Double locationId, String hotelName, int noRooms, Double pricePerNight, TypeENUM type) {
        super(aDouble);
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

}
