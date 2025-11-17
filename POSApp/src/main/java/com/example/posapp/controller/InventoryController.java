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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    public void innitAndLoadInventory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
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



    //delete inventory
    @FXML
    public void deleteItem() {
        Inventory inventory = inventoryListView.getSelectionModel().getSelectedItem();
        if (inventory != null) {
            //get the id
            int itemCode = inventory.getItemCode();

            //delete the item
            Inventory.deleteItem(itemCode);
            reloadInventory();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // Logger log = Logger.getLogger(InventoryController.class.getName());
        }
    }


    //edit
    //
    @FXML
    public void updateItem(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/editItem-view.fxml"));
            Parent root = loader.load();

            // Create a new window (Stage)
            Stage popupStage = new Stage();
            popupStage.setTitle("Editing Item");
            popupStage.setScene(new Scene(root));

            popupStage.initModality(Modality.APPLICATION_MODAL);

            popupStage.setOnHiding(e -> reloadInventory());
            popupStage.show();

        } catch (IOException e) {
            //logger
            e.printStackTrace();
        }

    }

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

            popupStage.setOnHiding(e -> reloadInventory());
            popupStage.show();

        } catch (IOException e) {
            //logger
            e.printStackTrace();
        }
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
