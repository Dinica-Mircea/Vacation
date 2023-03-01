module com.example.sub3feb2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sub3feb2023 to javafx.fxml;
    exports com.example.sub3feb2023;

    exports com.example.sub3feb2023.domain;
    opens com.example.sub3feb2023.domain to javafx.fxml;
    exports com.example.sub3feb2023.controller;
    opens com.example.sub3feb2023.controller to javafx.fxml;
}