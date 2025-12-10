package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.Inventory;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        innitAndLoadInventory(); // setting up the table for the inventory
        checkQTY(); //checking if the qty is below the threshold hold  to set it to yellow or red
        updateStoke(); // if the user updates the stock
        updateQTY(); //if the user updates the quantity
    }

    @FXML
    public void innitAndLoadInventory() {
        // allow the user to edit cells in the table
        inventoryListView.setEditable(true);

        // connect table columns to Inventory class fields
        colId.setCellValueFactory(new PropertyValueFactory<>("invId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("invName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colLow.setCellValueFactory(new PropertyValueFactory<>("lowStockThreshold"));

        // go through each inventory item
        colId.setReorderable(false);
        colName.setReorderable(false);
        colQty.setReorderable(false);
        colLow.setReorderable(false);

        // go through each inventory item
        ObservableList<Inventory> inventoryList = getInventory();
        inventoryListView.setItems(inventoryList);
    }

    // handle editing the "low stock" value in the table
    private void updateStoke(){
        colLow.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        // this runs when the user finishes editing a low stock cell
        colLow.setOnEditCommit(event -> {
            Inventory item = event.getRowValue(); // the row we are editing
            int oldStock = item.getLowStockThreshold(); // remember the old value
            String typed   = event.getNewValue() == null ? null : event.getNewValue().toString();
            try {
                // Validate input
                if (typed == null || typed.trim().isEmpty()) {
                    throw new NumberFormatException("Empty value not allowed");
                }

                // Try converting text → number
                int newValue = Integer.parseInt(typed.trim());

                // Check negative
                if (newValue < 0) {
                    throw new NumberFormatException("Negative number");
                }

                // Update model
                item.setLowStockThreshold(newValue);

                // Update DB
                Inventory.editLowStoke(newValue, item.getInvId());
                inventoryListView.getSelectionModel().clearSelection();

            } catch (NumberFormatException e) {
                item.setLowStockThreshold(oldStock);
                showInfo("Invalid Input", "You must enter a whole number ≥ 0.");
                inventoryListView.refresh();

            } catch (Exception e) {
                item.setLowStockThreshold(oldStock);
                LOGGER.log(Level.SEVERE, e.getCause()+e.getMessage()+"\nDatabase error updating low stock.");
                showError("Database Error", "Could not update the low stock threshold.");
                inventoryListView.refresh();
            }
        });

        // Clear highlight after edit
        Platform.runLater(() ->
                inventoryListView.getSelectionModel().clearSelection()
        );
    }

    private void updateQTY(){
        colQty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colQty.setOnEditCommit(event -> {
            Inventory item = event.getRowValue(); // the row we are editing
            int oldQTY = item.getQty(); // remember the old quantity
            String typed  = event.getNewValue() == null ? null : event.getNewValue().toString();
            try {
                // Validate input
                if (typed == null || typed.trim().isEmpty()) {
                    throw new NumberFormatException("Empty value not allowed");
                }

                // Try converting text → number
                int newValue = Integer.parseInt(typed.trim());

                // Check negative
                if (newValue < 0) {
                    throw new NumberFormatException("Negative number");
                }

                // Update model
                item.setQty(newValue);

                // Update DB
                Inventory.editItemQTY(newValue, item.getInvId());
                inventoryListView.getSelectionModel().clearSelection();

            } catch (NumberFormatException e) {
                item.setLowStockThreshold(oldQTY);
                showInfo("Invalid Input", "You must enter a whole number ≥ 0.");
                inventoryListView.refresh();

            } catch (Exception e) {
                item.setLowStockThreshold(oldQTY);
                LOGGER.log(Level.SEVERE, e.getCause()+e.getMessage()+"\nDatabase error updating low stock.");
                showError("Database Error", "Could not update the low stock threshold.");
                inventoryListView.refresh();
            }
        });

        // Clear highlight after edit
        Platform.runLater(() ->
                inventoryListView.getSelectionModel().clearSelection()
        );
    }

    private void checkQTY(){
        inventoryListView.setRowFactory(tv -> {
            TableRow<Inventory> row = new TableRow<>();

            // when the item in the row changes, update the row style (color)
            row.itemProperty().addListener((obs, oldItem, newItem) ->
                    updateRowStyle(row, newItem));
            // when the row is selected or unselected, update the style again
            row.selectedProperty().addListener((obs, wasSelected, isSelected) ->
                    updateRowStyle(row, row.getItem()));

            return row;
        });
    }

    private void updateRowStyle(TableRow<Inventory> row, Inventory item) {
        if (item == null) {
            // empty row, no style
            row.setStyle("");
            return;
        }

        if (row.isSelected()) {
            // if a row is selected, use normal style so selection color shows
            row.setStyle("");
            return;
        }

        // if quantity is 0, the background is dark-red
        if (item.getQty() == 0) {
            row.setStyle("-fx-background-color: #5a0000;");
        }
        // if quantity is less than a low stock threshold, the background is yellow-ish
        else if (item.getQty() < item.getLowStockThreshold()) {
            row.setStyle("-fx-background-color: #665200; fx-font-weight: bold;");
        }
        // if stock is fine, no special color
        else {
            row.setStyle("");
        }
    }

    @FXML
    public void searchInventory() {
        // get the text the user typed to search
        String search = input_TF.getText().toLowerCase();
        ObservableList<Inventory> inventoryList = getInventory();

        // list that will hold only the items that match the search
        ObservableList<Inventory> filteredInventoryList = FXCollections.observableArrayList();
        for (Inventory inventory : inventoryList) {
            if (inventory.getInvName().toLowerCase().contains(search)) {
                filteredInventoryList.add(inventory);
            }
        }
        // show only the filtered list in the table
        inventoryListView.setItems(filteredInventoryList);
    }

    @FXML
    public void clearSearch() {
        input_TF.clear(); // clear the search text field
        inventoryListView.setItems(getInventory()); // reload and show the full inventory list
    }


    //going back to the main menu
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

    // helper method: get all inventory items from the database
    public static ObservableList<Inventory> getInventory(){
        return Inventory.getAllInventory();
    }

    private void showError(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.setTitle(title);
        a.setHeaderText(null);
        a.showAndWait();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.setTitle(title);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
