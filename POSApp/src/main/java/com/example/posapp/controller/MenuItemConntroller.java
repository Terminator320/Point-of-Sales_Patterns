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
import javafx.scene.control.ListView;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.example.posapp.models.MenuItem;


public class MenuItemConntroller {

    @FXML private ListView<String> recitedView;

    // menu items available on the screen: menuId -> MenuItem
    private  Map<Integer, MenuItem> menuItems = new HashMap<>();

    private Map<Integer, Inventory> inventoryMap = new HashMap<>();


    // active order: menuId -> quantity
    private final Map<Integer, Integer> activeOrder = new HashMap<>();

    // backing list for the ListView
    private final ObservableList<String> receiptLines = FXCollections.observableArrayList();

    public void initialize() {
        recitedView.setItems(receiptLines);
        //setting the map for the menu items with its inventory
        for (Inventory inv : Inventory.getAllInventory()) {
            inventoryMap.put(inv.getItemCode(), inv);
        }

        menuItems.put(1, new MenuItem(100, "Espresso",       2.50, inventoryMap.get(100)));
        menuItems.put(2, new MenuItem(101, "Latte",          3.25, inventoryMap.get(101)));
        menuItems.put(3, new MenuItem(102, "Cappuccino",     3.50, inventoryMap.get(102)));
        menuItems.put(4, new MenuItem(103, "Iced Americano", 3.25, inventoryMap.get(103)));
        menuItems.put(5, new MenuItem(103, "Iced Coffee",    3.25, inventoryMap.get(103)));
        menuItems.put(6, new MenuItem(104, "Green Tea",      2.75, inventoryMap.get(104)));
        menuItems.put(7, new MenuItem(105, "Chai Latte",     3.00, inventoryMap.get(105)));

        menuItems.put(20, new MenuItem(106, "Croissant",         3.00, inventoryMap.get(106)));
        menuItems.put(21, new MenuItem(107, "Muffin",            2.50, inventoryMap.get(107)));
        menuItems.put(22, new MenuItem(108, "Cheese Bagel",      3.50, inventoryMap.get(108)));
        menuItems.put(23, new MenuItem(109, "BLT Sandwich",      6.00, inventoryMap.get(109)));
        menuItems.put(24, new MenuItem(110, "Grilled Cheese",    5.00, inventoryMap.get(110)));
        menuItems.put(25, new MenuItem(111, "Chicken Wrap",      7.00, inventoryMap.get(111)));


        menuItems.put(30, new MenuItem(112, "Strawberry Smoothie", 4.50, inventoryMap.get(112)));
        menuItems.put(31, new MenuItem(113, "Mixed Berry",         5.00, inventoryMap.get(113)));
        menuItems.put(32, new MenuItem(114, "Protein Shake",       6.50, inventoryMap.get(114)));

    }

    //addMethod
    private void addItemToOrder(int menuId) {

        MenuItem item = menuItems.get(menuId);
        if (item == null) return;


        // update quantity in order
        int newQty = activeOrder.getOrDefault(menuId, 0) + 1;

        Inventory stoke = item.getInventory();
        if (stoke.getQty() < newQty) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Out of Stoke!");
            alert.showAndWait();
        }

        activeOrder.put(menuId, newQty);

        rebuildReceipt();
    }

    private void removeSelectedItem() {
        int index = recitedView.getSelectionModel().getSelectedIndex();
        if (index < 0) return;  // nothing selected

        // Find which menuId this corresponds to
        int i = 0;
        Integer menuIdToRemove = null;

        for (Integer menuId : activeOrder.keySet()) {
            if (i == index) {
                menuIdToRemove = menuId;
                break;
            }
            i++;
        }

        if (menuIdToRemove != null) {
            activeOrder.remove(menuIdToRemove);
            rebuildReceipt();   // refresh ListView
        }
    }

    private void rebuildReceipt() {
        receiptLines.clear();

        for (Map.Entry<Integer, Integer> e : activeOrder.entrySet()) {
            int menuId = e.getKey();
            int qty    = e.getValue();
            MenuItem item = menuItems.get(menuId);

            receiptLines.add(qty + " x " + item.getName());
        }
    }

    @FXML
    public void espressoClick(ActionEvent event) {
        addItemToOrder(1);
    }

    @FXML
    public void latteClick(ActionEvent event) {
        addItemToOrder(2);
    }

    @FXML
    public void cappuccinoClick(ActionEvent event) {
        addItemToOrder(3);
    }

    @FXML
    public void frappeClick(ActionEvent event) {

    }

    @FXML
    public void iceAmeriClick(ActionEvent event) {
        addItemToOrder(4);
    }

    @FXML
    public void iceCoffeeClick(ActionEvent event) {
        addItemToOrder(5);
    }

    @FXML
    public void greenTeaClick(ActionEvent event) {
        addItemToOrder(6);
    }

    @FXML
    public void blackTeaClick(ActionEvent event) {

    }

    @FXML
    public void peachTeaClick(ActionEvent event) {

    }

    @FXML
    public void starwberryTeaClick(ActionEvent event) {

    }

    @FXML
    public void chaiLatteTeaClick(ActionEvent event) {
        addItemToOrder(7);
    }

    @FXML
    public void oolongTeaClick(ActionEvent event) {

    }

    @FXML
    public void starwberrySmoClick(ActionEvent event) {
        addItemToOrder(30);
    }

    @FXML
    public void mangoSmClick(ActionEvent event) {

    }

    @FXML
    public void mixedBerrySmClick(ActionEvent event) {
        addItemToOrder(31);
    }

    @FXML
    public void proteinShakenClick(ActionEvent event) {
        addItemToOrder(32);
    }

    @FXML
    public void croissantClick(ActionEvent event) {
        addItemToOrder(20);
    }

    @FXML
    public void muffinClick(ActionEvent event) {
        addItemToOrder(21);
    }

    @FXML
    public void chickenWrapClick(ActionEvent event) {
        addItemToOrder(25);
    }

    @FXML
    public void btlSandwichClick(ActionEvent event) {
        addItemToOrder(23);
    }

    @FXML
    public void grilledCheeseClick(ActionEvent event) {
        addItemToOrder(24);
    }

    @FXML
    public void cheeseBagelClick(ActionEvent event) {
        addItemToOrder(22);
    }


    @FXML
    public void proceedClick(ActionEvent event) {
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/sales-order.fxml"));
            Parent newRoot = loader.load();

            Scene newScene = new Scene(newRoot);

            // Get the current stage (e.g., from a component's scene and window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Sales-Order Report");
        }
        catch (IOException e) {
            //check top logger
            e.printStackTrace();
        }
    }

    @FXML
    public void removeItemClick(ActionEvent event) {
        removeSelectedItem();
    }
}
