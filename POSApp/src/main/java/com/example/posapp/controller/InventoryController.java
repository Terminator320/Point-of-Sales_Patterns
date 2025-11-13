package com.example.posapp.controller;

import com.example.posapp.models.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryController {
    @FXML private TableView<Inventory> inventoryListView;
    @FXML private TableColumn<Inventory, Integer> colId;
    @FXML private TableColumn<Inventory, String> colName;
    @FXML private TableColumn<Inventory, Integer> colQty;
    @FXML private TableColumn<Inventory, Integer> colLow;

    @FXML private TextField input_TF;



    @FXML
    public void initialize() {
        innitAndLoadInventory();
    }

    @FXML
    public void innitAndLoadInventory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("invId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("invName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colLow.setCellValueFactory(new PropertyValueFactory<>("lowStokeThreeshold"));

        ObservableList<Inventory> inventoryList = getInventory();
        inventoryListView.setItems(inventoryList);
    }

    @FXML
    public void searchInventory() {
        String search = input_TF.getText();
        ObservableList<Inventory> inventoryList = getInventory();

        ObservableList<Inventory> filteredInventoryList = FXCollections.observableArrayList();
        for (Inventory inventory : inventoryList) {
            if (inventory.getInvName().contains(search)) {
                filteredInventoryList.add(inventory);
            }
        }
        inventoryListView.setItems(filteredInventoryList);
    }


    @FXML
    public void clearSearch() {
        input_TF.clear();
        inventoryListView.setItems(getInventory());
    }

    //add new inventory
    @FXML
    public void addInventory() {}

    //delete inventory
    @FXML
    public void deleteItem() {}

    //edit
    @FXML
    public void updateItem() {}

    //pop up
    @FXML
    public void addPopUp(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/popUpInventory.fxml"));
            Parent root = loader.load();

            // Create a new window (Stage)
            Stage popupStage = new Stage();
            popupStage.setTitle("Adding Inventory");
            popupStage.setScene(new Scene(root));


            popupStage.initModality(Modality.APPLICATION_MODAL);

            popupStage.show(); // or showAndWait()

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static ObservableList<Inventory> getInventory(){
        return Inventory.getAllInventory();
    }
}
