package com.example.sub3feb2023.controller;

import com.example.sub3feb2023.domain.Hotel;
import com.example.sub3feb2023.domain.Reservation;
import com.example.sub3feb2023.domain.SpecialOffer;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

import java.util.Arrays;
import java.util.Date;

public class SpecialOfferController extends Controller {
    private Hotel hotel;
    private Long clientId;

    public void setClientId(Long clientId){this.clientId=clientId;}

    public void setHotel(Hotel hotel){this.hotel=hotel;}
    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<SpecialOffer> tabelSpecialOffers;

    @Override
    public void initialize_everything(){
        initialize_table(tabelSpecialOffers, Arrays.asList("startDate","endDate","percents"));
        System.out.println(hotel);

    }

    public void showOffers(){
        Date startDate=dateUtils.convertToSQLDate(startDatePicker.getValue());
        Date endDate=dateUtils.convertToSQLDate(endDatePicker.getValue());
        tabelSpecialOffers.setItems(service.getAllOffers(startDate,endDate,hotel));
    }

    public void makeReservation(){
        Date startDate= dateUtils.convertToSQLDate(startDatePicker.getValue());
        Integer noNights=dateUtils.differenceBetweenTwoLocalDates(startDatePicker.getValue(),endDatePicker.getValue());
        Double reservationId = service.getMaxReservationID() + 1;
        Reservation reservation=new Reservation(reservationId,clientId,hotel.getId(),
                dateUtils.convertToLocalDateTimeViaInstant(startDate),noNights);
        service.makeReservation(reservation);
    }
}
