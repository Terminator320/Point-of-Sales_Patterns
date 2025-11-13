package com.example.posapp.controller;

import com.example.posapp.models.Category;
import com.example.posapp.models.Inventory;
import database.ConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class popUpController {
    @FXML private TextField invIdTF;
    @FXML private TextField invNameTF;
    @FXML private TextField qtyTF;
    @FXML private TextField lowStokeThreeholdTF;
    @FXML private ChoiceBox<String> categoryCB;

    @FXML
    public void initialize() {}

    //add
    @FXML
    public void addInventoryClick(){}
}
