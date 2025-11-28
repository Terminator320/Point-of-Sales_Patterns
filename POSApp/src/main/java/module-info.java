module com.example.posapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    requires java.logging;


    opens com.example.posapp to javafx.fxml;
    exports com.example.posapp;
    exports com.example.posapp.controller;
    opens com.example.posapp.controller to javafx.fxml;
    opens com.example.posapp.models to javafx.base;
}