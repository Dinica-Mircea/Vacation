package com.example.sub3feb2023.controller;

import com.example.sub3feb2023.domain.ClientOffer;
import com.example.sub3feb2023.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.Arrays;

public class ClientController extends Controller {
    private Long clientID;

    public void setService(Service service){
        this.service=service;
    }

    public void setClientID(Long clientID){this.clientID=clientID;}
    @FXML
    private TableView<ClientOffer> offersTable;


    @Override
    public void initialize_everything(){
        initialize_table(offersTable, Arrays.asList("numeHotel","numeLocatie","startDate","endDate"));
        offersTable.setItems(service.getClientOffers(clientID));
    }
}
