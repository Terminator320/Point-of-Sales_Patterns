package com.example.posapp.controller;

import com.example.posapp.models.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.logging.Logger;

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

    private void reloadInventory(){
        ObservableList<Inventory> inventoryList = getInventory();
        inventoryListView.setItems(inventoryList);
    }

    private void updateStoke(){
        colLow.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colLow.setOnEditCommit(event -> {
            Inventory item = event.getRowValue();
            int oldStoke = item.getLowStokeThreeshold();
            Integer newStoke = event.getNewValue();

            if(newStoke >= 0) {
                item.setLowStokeThreeshold(newStoke);
                //update to db
                try
                {
                    Inventory.editLowStoke(newStoke,item.getItemCode());
                }
                catch (Exception e) {
                    //if there is an error while updating the db go back to old value
                    item.setLowStokeThreeshold(oldStoke);
                    Alert alert = new Alert(Alert.AlertType.ERROR,"An error has occured while trying to update the DB");
                    alert.showAndWait();
                    //change to logger later
                    e.printStackTrace();
                }
            }
            else {
                item.setLowStokeThreeshold(oldStoke);
                //logger
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Low Stoke Threshold can not be a negative number.");
                alert.showAndWait();
                inventoryListView.refresh();
            }
        });
    }

    private void updateQTY(){
        colQty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colQty.setOnEditCommit(event -> {
            Inventory item = event.getRowValue();
            int oldQTY = item.getQty();
            Integer newQTY = event.getNewValue();

            if(newQTY >= 0) {
                item.setQty(newQTY);
                //update to db
                try
                {
                    Inventory.editItemQTY(newQTY,item.getItemCode());
                }
                catch (Exception e) {
                    //if there is an error while updating the db go back to old value
                    item.setQty(oldQTY);
                    Alert alert = new Alert(Alert.AlertType.ERROR,"An error has occured while trying to update the DB");
                    alert.showAndWait();
                    //change to logger later
                    e.printStackTrace();
                }
            }
            else {
                item.setQty(oldQTY);
                //logger
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Quanty can not be a negative number.");
                alert.showAndWait();
                inventoryListView.refresh();
            }
        });
    }

    private void checkQTY(){
        inventoryListView.setRowFactory(tv -> new TableRow<Inventory>() {
            @Override
            protected void updateItem(Inventory item , boolean em) {
                super.updateItem(item,em);

                if(em || item ==null) {
                    setStyle("");
                    return;
                }

                if(item.getQty() < item.getLowStokeThreeshold()) {
                    setStyle("-fx-background-color: #FFF300FF;");
                }
                else {
                    setStyle("");
                }
            }
        });
    }
    @FXML
    public void innitAndLoadInventory() {
        inventoryListView.setEditable(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("invName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colLow.setCellValueFactory(new PropertyValueFactory<>("lowStokeThreeshold"));

        ObservableList<Inventory> inventoryList = getInventory();
        inventoryListView.setItems(inventoryList);

        updateStoke();
        updateQTY();
        checkQTY();
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


    //going back to main mneu
    @FXML
    private void backMainMenu(ActionEvent event) {
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/main-view.fxml"));
            Parent newRoot = loader.load();

            Scene newScene = new Scene(newRoot);

            // Get the current stage (e.g., from a component's scene and window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Menu");
        }
        catch (IOException e) {
            //check top looger
            e.printStackTrace();
        }
    }


    public static ObservableList<Inventory> getInventory(){
        return Inventory.getAllInventory();
    }
}
