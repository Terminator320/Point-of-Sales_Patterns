package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.Inventory;
import com.example.posapp.models.PopularItems;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;

import static com.example.posapp.models.PopularItems.popularItemsFinder;

public class InventoryController {
    @FXML private TableView<Inventory> inventoryListView;
    @FXML private TableColumn<Inventory, Integer> colId;
    @FXML private TableColumn<Inventory, String> colName;
    @FXML private TableColumn<Inventory, Integer> colQty;
    @FXML private TableColumn<Inventory, Integer> colLow;



    @FXML private TextField input_TF;

    private static final Logger LOGGER = LogConfig.getLogger(InventoryController.class.getName());


    @FXML
    public void initialize() {
        innitAndLoadInventory();
        checkQTY();
        updateStoke();
        updateQTY();
    }

    private void reloadInventory(){
        ObservableList<Inventory> inventoryList = getInventory();
        inventoryListView.setItems(inventoryList);
    }

    private void updateStoke(){
        colLow.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colLow.setOnEditCommit(event -> {
            Inventory item = event.getRowValue();
            int oldStoke = item.getLowStokeThreshold();
            Integer newStoke = event.getNewValue();

            if(newStoke == null || newStoke < 0 || newStoke == oldStoke){

            }
            if(newStoke >= 0) {
                item.setLowStokeThreshold(newStoke);
                //update to db
                try
                {
                    Inventory.editLowStoke(newStoke,item.getInvId());
                }
                catch (Exception e) {
                    //if there is an error while updating the db go back to old value
                    item.setLowStokeThreshold(oldStoke);
                    LOGGER.log(Level.SEVERE, "Error updating low stoke threshold the database.");
                    Alert alert = new Alert(Alert.AlertType.ERROR,"An error has occurred while trying to update the DB.");
                    alert.showAndWait();
                }
            }
            else {
                item.setLowStokeThreshold(oldStoke);
                //logger
                LOGGER.log(Level.SEVERE, "Error updating low stoke threshold the database.");

                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Low Stoke Threshold can not be a negative number.");
                alert.showAndWait();
                inventoryListView.refresh();
            }
        });

        //clears selection AFTER edit
        Platform.runLater(() ->
                inventoryListView.getSelectionModel().clearSelection()
        );
    }

    private void updateQTY(){
        colQty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colQty.setOnEditCommit(event -> {
            Inventory item = event.getRowValue();
            int oldQTY = item.getQty();
            Integer newQTY = event.getNewValue();

            //if user inputted negative or null value
            if (newQTY == null || newQTY < 0 || newQTY == oldQTY) {
                item.setQty(oldQTY);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Quantity can not be a negative number or empty or same value.");
                alert.showAndWait();
                LOGGER.log(Level.WARNING, "User inputted a negative number or empty value or the same value.");

                inventoryListView.refresh();
                return;
            }

            //if value is valid set new qty and update db
            item.setQty(newQTY);

            //update to db
            try {
                Inventory.editItemQTY(newQTY, item.getInvId());
            } catch (Exception e) {
                //if there is an error while updating the db go back to old value
                item.setQty(oldQTY);
                inventoryListView.refresh();

                Alert alert = new Alert(Alert.AlertType.ERROR, "An error has occurred while trying to update the DB");
                alert.showAndWait();


                LOGGER.log(Level.SEVERE, "Error updating quantity the database.");
            }

            //clears selection AFTER edit
            Platform.runLater(() ->
                    inventoryListView.getSelectionModel().clearSelection()
            );
        });

    }

    private void checkQTY(){
        inventoryListView.setRowFactory(tv -> {
            TableRow<Inventory> row = new TableRow<>();

            row.itemProperty().addListener((obs, oldItem, newItem) ->
                    updateRowStyle(row, newItem));
            row.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                    updateRowStyle(row, row.getItem()));

            return row;
        });
    }

    private void updateRowStyle(TableRow<Inventory> row, Inventory item) {
        if (item == null) {
            row.setStyle("");
            return;
        }

        if (row.isSelected()) {
            row.setStyle("");
            return;
        }

        if (item.getQty() == 0) {
            row.setStyle("-fx-background-color: #fa0202;");
        } else if (item.getQty() < item.getLowStokeThreshold()) {
            row.setStyle("-fx-background-color: #FFF300FF;");
        } else {
            row.setStyle("");
        }
    }

    @FXML
    public void innitAndLoadInventory() {
        inventoryListView.setEditable(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("invId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("invName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colLow.setCellValueFactory(new PropertyValueFactory<>("lowStokeThreshold"));

        colId.setReorderable(false);
        colName.setReorderable(false);
        colQty.setReorderable(false);
        colLow.setReorderable(false);

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


    //going back to main menu
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
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading back to the Main Menu.");
        }
    }


    public static ObservableList<Inventory> getInventory(){
        return Inventory.getAllInventory();
    }
}
