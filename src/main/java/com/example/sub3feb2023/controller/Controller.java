package com.example.sub3feb2023.controller;

import com.example.sub3feb2023.domain.Hotel;
import com.example.sub3feb2023.service.Service;
import com.example.sub3feb2023.utils.DateUtils;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public abstract class Controller {
    protected Service service;
    protected DateUtils dateUtils=new DateUtils();

    protected static <E> void initialize_table(TableView<E> tabel, List<String> columnNames) {
        for (String columnName : columnNames) {
            TableColumn<E, String> column = new TableColumn<>(columnName);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tabel.getColumns().add(column);
        }
    }

    public void setService(Service service) {
        this.service = service;
    }

    public abstract void initialize_everything();


}
