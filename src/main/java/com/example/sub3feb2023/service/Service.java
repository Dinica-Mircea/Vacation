package com.example.sub3feb2023.service;

import com.example.sub3feb2023.domain.*;
import com.example.sub3feb2023.repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service {
    private Repository<Double, Hotel> repoHotels;
    private Repository<Double, Location> repoLocations;
    private Repository<Double, SpecialOffer> repoSpecialOffers;
    private Repository<Long, Client> repoClients;

    private Repository<Double, Reservation> repoReservations;

    public Service(Repository<Double, Hotel> repoHotels, Repository<Double, Location> repoLocations,
                   Repository<Double, SpecialOffer> repoOffers, Repository<Long, Client> repoClients,
                   Repository<Double, Reservation> repoReservations) {
        this.repoHotels = repoHotels;
        this.repoLocations = repoLocations;
        this.repoSpecialOffers = repoOffers;
        this.repoClients = repoClients;
        this.repoReservations = repoReservations;
    }

    public ObservableList<Hotel> getAllHotelsInLocation(Location location) {
        List<Hotel> hotelsInLocation = new ArrayList<>();
        for (Hotel it : repoHotels.findAll()) {
            if (Objects.equals(it.getLocationId(), location.getId())) {
                hotelsInLocation.add(it);
            }
        }
        return FXCollections.observableArrayList(hotelsInLocation);
    }

    public Iterable<Location> getAllLocations() {
        return repoLocations.findAll();
    }

    public static <E> List<E> toList(final Iterable<E> iterable) {
        List<E> result=new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    public ObservableList<SpecialOffer> getAllOffers(Date startDate, Date endDate, Hotel hotel) {
        List<SpecialOffer> specialOffers = new ArrayList<>();
        for (SpecialOffer it : repoSpecialOffers.findAll()) {
            if (Objects.equals(it.getHotelId(), hotel.getId()) && startDate.compareTo(it.getStartDate()) <= 0 && endDate.compareTo(it.getEndDate()) >= 0) {
                specialOffers.add(it);
            }
        }
        return FXCollections.observableArrayList(specialOffers);
    }

    public ObservableList<ClientOffer> getClientOffers(Long clientID) {
        Client client = repoClients.findOne(clientID).get();
        Date now = new Date();
        List<ClientOffer> clientOffers = new ArrayList<>();
        for (SpecialOffer specialOffer : repoSpecialOffers.findAll()) {
            if (client.getFidelityGrade() > specialOffer.getPercents() && specialOffer.getStartDate().compareTo(now) > 0) {
                System.out.println(specialOffer.getHotelId());
                Hotel hotel = repoHotels.findOne(specialOffer.getHotelId()).get();
                String hotelName = hotel.getHotelName();
                System.out.println(repoLocations.findOne(hotel.getLocationId()).get());
                String locationName = repoLocations.findOne(hotel.getLocationId()).get().getLocationName();
                ClientOffer newClientOffer = new ClientOffer(hotelName, locationName, specialOffer.getStartDate(), specialOffer.getEndDate());
                clientOffers.add(newClientOffer);
            }
        }
        return FXCollections.observableArrayList(clientOffers);
    }

    public void makeReservation(Reservation reservation) {
        repoReservations.save(reservation);
    }

    public Double getMaxReservationID() {
        List<Reservation> reservations = new ArrayList<>();
        repoReservations.findAll().forEach(reservations::add);
        if (reservations.size() == 0) {
            return 1.0;
        } else {
            Double maxID=1.0;
            for (Reservation reservation : reservations) {
                if(reservation.getId()>maxID)
                    maxID=reservation.getId();
            }
            return maxID;
        }
    }

    @Override
    public String toString() {
        return "Service{" +
                "repoHotels=" + repoHotels +
                ", repoLocations=" + repoLocations +
                ", repoSpecialOffers=" + repoSpecialOffers +
                ", repoClients=" + repoClients +
                '}';
    }
}
