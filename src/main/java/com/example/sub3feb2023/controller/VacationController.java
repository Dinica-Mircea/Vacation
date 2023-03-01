package com.example.sub3feb2023.controller;

import com.example.sub3feb2023.HelloApplication;
import com.example.sub3feb2023.domain.Hotel;
import com.example.sub3feb2023.domain.Location;
import com.example.sub3feb2023.domain.TypeENUM;
import com.example.sub3feb2023.service.Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;

public class VacationController extends Controller {
    private Long clientId;
    @FXML
    private TableView<Hotel> tabelHotels;
    @FXML
    private ComboBox<Location> comboBoxLocations;

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public void initialize_everything() {
        comboBoxLocations.setItems(FXCollections.observableList(Service.toList(service.getAllLocations())));
        //comboBoxLocations.setItems(FXCollections.observableList(Arrays.asList(TypeENUM.valueOf("family"), TypeENUM.valueOf("oldpeople")));
        Arrays.asList(TypeENUM.valueOf("family"), TypeENUM.valueOf("oldpeople"));
        //tableHotels
        /*TableColumn<Hotel,String> hotelName=new TableColumn<>("hotelName");
        TableColumn<Hotel,String> noRooms=new TableColumn<>("noRooms");
        TableColumn<Hotel,String> pricePerNight=new TableColumn<>("pricePerNight");
        TableColumn<Hotel,String> type=new TableColumn<>("type");

        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        noRooms.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
        pricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        tabelHotels.getColumns().setAll(Arrays.asList(hotelName,noRooms,pricePerNight,type));*/
        initialize_table(tabelHotels, Arrays.asList("hotelName", "noRooms", "pricePerNight", "type"));


    }

    public void showHotelsInLocation() {
        tabelHotels.setItems(service.getAllHotelsInLocation(comboBoxLocations.getValue()));
    }

    public void showOffersOfHotel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("specialOffers-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Special Offers");
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(600);
        SpecialOfferController specialOfferController = fxmlLoader.getController();
        stage.show();
        specialOfferController.setHotel(tabelHotels.getSelectionModel().getSelectedItem());
        specialOfferController.setService(service);
        specialOfferController.initialize_everything();

        if (clientId != null) {
            specialOfferController.setClientId(clientId);
        }


    }
}
