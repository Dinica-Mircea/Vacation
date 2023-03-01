package com.example.sub3feb2023;

import com.example.sub3feb2023.controller.ClientController;
import com.example.sub3feb2023.controller.VacationController;
import com.example.sub3feb2023.domain.*;
import com.example.sub3feb2023.repository.Repository;
import com.example.sub3feb2023.repository.db.*;
import com.example.sub3feb2023.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(getParameters().getRaw().size());
        if (getParameters().getRaw().size() == 0) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(600);
            Repository<Double, Location> locationDbRepository = new LocationDbRepository();
            Repository<Double, Hotel> hotelDbRepository = new HotelDbRepository();
            Repository<Double, SpecialOffer> specialDbOfferRepository = new SpecialOfferDbRepository();
            Repository<Long, Client> clientDbRepository = new ClientDbRepository();
            Repository<Double, Reservation> repoReservations=new ReservationDbRepository();
            System.out.println(hotelDbRepository.findOne(1.0));
            System.out.println(locationDbRepository.findOne(1.0));
        /*System.out.println(locationDbRepository.findAll());
        for (Location it:locationDbRepository.findAll()
             ) {
            System.out.println(it.getLocationName());

        }
        for (Hotel hotel:hotelDbRepository.findAll())
        {
            System.out.println(hotel);
        }*/

            VacationController vacationController = fxmlLoader.getController();
            vacationController.setService(new Service(hotelDbRepository,
                    locationDbRepository, specialDbOfferRepository, clientDbRepository,repoReservations));
            vacationController.initialize_everything();
            stage.show();
        } else {
            List<String> args = getParameters().getRaw();
            startWithArgs(args);
        }

    }

    public static void startWithArgs(List<String> args) throws IOException {
        List<Long> clientIds = new ArrayList<>();
        for (String arg : args) {
            clientIds.add(Long.parseLong(arg.substring(arg.length() - 1, arg.length())));
        }
        System.out.println(clientIds);
        for (Long clientId : clientIds) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("client-view.fxml"));
            Repository<Double, Location> locationDbRepository = new LocationDbRepository();
            Repository<Double, Hotel> hotelDbRepository = new HotelDbRepository();
            Repository<Double, SpecialOffer> specialDbOfferRepository = new SpecialOfferDbRepository();
            Repository<Long, Client> clientDbRepository = new ClientDbRepository();
            Repository<Double,Reservation> repoReservations=new ReservationDbRepository();
            Service service2 = new Service(hotelDbRepository, locationDbRepository,
                    specialDbOfferRepository, clientDbRepository,repoReservations);
            //System.out.println(service2);
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            Stage stage = new Stage();
            stage.setTitle(clientDbRepository.findOne(clientId).get().getName());
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(600);

            ClientController clientController = fxmlLoader.getController();
            clientController.setService(service2);
            clientController.setClientID(clientId);
            clientController.initialize_everything();
            stage.show();

            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);
            Stage stage2=new Stage();
            String Title="Hello" + clientDbRepository.findOne(clientId).get().getName()+"!";
            stage2.setTitle(Title);
            stage2.setScene(scene2);
            stage2.setHeight(400);
            stage2.setWidth(600);
            VacationController vacationController = fxmlLoader2.getController();
            vacationController.setService(new Service(hotelDbRepository, locationDbRepository,
                    specialDbOfferRepository, clientDbRepository,repoReservations));
            vacationController.setClientId(clientId);
            vacationController.initialize_everything();
            stage2.show();
        }

    }

    public static void main(String[] args) throws IOException {
        launch(HelloApplication.class, args);


    }
}