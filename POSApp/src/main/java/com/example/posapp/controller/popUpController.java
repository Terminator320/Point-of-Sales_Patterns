package com.example.posapp.controller;

import com.example.posapp.models.Inventory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class popUpController {
    @FXML private TextField invNameTF;
    @FXML private TextField qtyTF;
    @FXML private TextField lowStokeThreeholdTF;
    @FXML private TextField itemCodeTF;


    //add
    @FXML
    public void addInventoryClick(){
        try{
            String invName = invNameTF.getText();
            int qty = Integer.parseInt(qtyTF.getText());
            int lowStoke = Integer.parseInt(lowStokeThreeholdTF.getText());
            int itemCode = Integer.parseInt(itemCodeTF.getText());

            //check if the code already exits
            if(Inventory.itemCodeExists(itemCode)){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Item already exists!");
                alert.setContentText("Please choose a different code.");
                alert.showAndWait();
                return;
            }

            Inventory.addItem(invName, qty, lowStoke, itemCode);

            // close window
            Stage stage = (Stage) invNameTF.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            //logger
            e.printStackTrace();
        }
    }



}
