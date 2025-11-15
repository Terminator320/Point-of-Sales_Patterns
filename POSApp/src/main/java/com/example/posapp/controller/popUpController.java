package com.example.posapp.controller;

import com.example.posapp.models.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.posapp.controller.InventoryController.getInventory;
import static java.lang.Integer.parseInt;

public class popUpController {
    @FXML private TextField invNameTF;
    @FXML private TextField qtyTF;
    @FXML private TextField lowStokeThreeholdTF;
    @FXML private ChoiceBox<String> categoryCB;

    @FXML
    public void initialize() {}

    //add
    @FXML
    public void addInventoryClick(){
        String invName = invNameTF.getText();
        int qty = parseInt(qtyTF.getText());
        int lowStoke = parseInt(lowStokeThreeholdTF.getText());

        Inventory.addItem(invName, qty, lowStoke);

        // close window
        Stage stage = (Stage) invNameTF.getScene().getWindow();
        stage.close();
    }



}
