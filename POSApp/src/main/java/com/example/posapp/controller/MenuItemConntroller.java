package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.*;
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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MenuItemConntroller {

    @FXML private ListView<String> recitedView;

    //menuId , MenuItem
    private final Map<Integer, MenuItem> menuItems = new HashMap<>();

    // menuId , quantity
    private final Map<Integer, Integer> activeOrder = new HashMap<>();

    // check item
    private final ObservableList<String> receiptLines = FXCollections.observableArrayList();

    //logger
    private static final Logger LOGGER = LogConfig.getLogger(MenuItemConntroller.class.getName());


    public void initialize() {
        recitedView.setItems(receiptLines);
        initMenuItems(); //setting up the menuItems
    }

    private  void initMenuItems(){
        // Load all menu items from DB; each one loads its ingredients
        for (MenuItem item : MenuItem.getMenuItems()) {
            menuItems.put(item.getMenuItemId(), item);
        }
    }

    //addMethod
    private void addItemToOrder(int menuId) {
        MenuItem item = menuItems.get(menuId);
        if (item == null) return;

        int currentQTY = activeOrder.getOrDefault(menuId, 0);

        MenuIngredient ingredientOutOfStock = findOutOfStockIngredient(item);

        if (ingredientOutOfStock != null) {

            Inventory invSnapshot = ingredientOutOfStock.getInventory();

            //ternary operator
            Inventory freshInv = invSnapshot == null ? null : Inventory.getOne(invSnapshot.getInvId());

            String invName;

            if (freshInv != null && freshInv.getInvName() != null) {
                invName = freshInv.getInvName();
            } else if (invSnapshot != null && invSnapshot.getInvName() != null) {
                invName = invSnapshot.getInvName();
            } else {
                invName = "One of the ingredients";
            }

            invalidMenuOrder3(
                    "Out of Stock",
                    item.getName() + " cannot be added",
                    invName + " is out of stock or not enough for this quantity.");
            return;
        }

        activeOrder.put(menuId, (currentQTY+1));
        rebuildReceipt();
    }

    //checking if the ingredient needed is out of stock
    //This checks the total needed for this ingredient across the whole order
    private MenuIngredient findOutOfStockIngredient(MenuItem item) {

        // For each ingredient that the clicked item needs
        for (MenuIngredient ingredient : item.getIngredients()) {
            Inventory theInventory = ingredient.getInventory();

            //getting the invId
            int invId = theInventory.getInvId();

            // Get fresh quantity from db (real current stock)
            Inventory freshInv = Inventory.getOne(invId);

            // Start with the value, then overwrite if db had something
            int available = theInventory.getQty();
            if (freshInv != null) {
                available = freshInv.getQty();
            }

            //defaulting
            int totalNeeded = 0;

            // sum of the inventory item across all items already in the order
            for (Map.Entry<Integer, Integer> entry : activeOrder.entrySet()) {
                int menuIdInOrder = entry.getKey();
                int qtyInOrder    = entry.getValue();

                MenuItem itemInOrder = menuItems.get(menuIdInOrder);

                for (MenuIngredient ingredient1 : itemInOrder.getIngredients()) {
                    Inventory invInOrder = ingredient1.getInventory();

                    if (invInOrder.getInvId() == invId) {
                        totalNeeded += ingredient1.getQuantityUsed() * qtyInOrder;
                    }
                }
            }

            // Now add the extra 1 item we are trying to add right now
            totalNeeded += ingredient.getQuantityUsed();

            // If the total needed for this inventory exceeds what we have, it's out of stock
            if (totalNeeded > available) {
                return ingredient;
            }
        }

        return null; // all ingredients
    }

    private void removeSelectedItem() {
        int index = recitedView.getSelectionModel().getSelectedIndex();
        if (index < 0) return;

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
            rebuildReceipt();
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
    public void espressoClick() { addItemToOrder(1); }

    @FXML
    public void latteClick() { addItemToOrder(2); }

    @FXML
    public void cappuccinoClick() { addItemToOrder(3); }

    @FXML
    public void iceAmeriClick() { addItemToOrder(4); }

    @FXML
    public void iceCoffeeClick() { addItemToOrder(17); }

    @FXML
    public void frappeClick() { addItemToOrder(16); }

    @FXML
    public void greenTeaClick() { addItemToOrder(5); }

    @FXML
    public void chaiLatteTeaClick() { addItemToOrder(6); }

    @FXML
    public void blackTeaClick() { addItemToOrder(18); }

    @FXML
    public void oolongTeaClick() { addItemToOrder(19); }

    @FXML
    public void peachTeaClick() { addItemToOrder(20); }

    @FXML
    public void starwberryTeaClick() { addItemToOrder(21); }
    @FXML
    public void croissantClick() { addItemToOrder(7); }

    @FXML
    public void muffinClick() { addItemToOrder(8); }

    @FXML
    public void cheeseBagelClick() { addItemToOrder(9); }

    @FXML
    public void btlSandwichClick() { addItemToOrder(10); }

    @FXML
    public void grilledCheeseClick() { addItemToOrder(11); }

    @FXML
    public void chickenWrapClick() { addItemToOrder(12); }

    @FXML
    public void starwberrySmoClick() { addItemToOrder(22); }

    @FXML
    public void mixedBerrySmClick() { addItemToOrder(13); }

    @FXML
    public void proteinShakenClick() { addItemToOrder(15); }

    @FXML
    public void mangoSmClick() { addItemToOrder(14); }


    @FXML
    public void proceedClick(ActionEvent event) {
        try {

            if (activeOrder.isEmpty()) {
                invalidMenuOrder("Proceeding to Sales Order", "You can't continue without adding an item!");
                return;
            }

            // create a new sale_order row and get its id
            int orderId = SalesOrder.addSaleOrderInitial();

            // save all items in the join table with their quantities
            activeOrder.forEach((menuId, qty) -> SalesOrderItem.addMenuItemToOrder(menuId, orderId, qty));

            // get the items for this order for the SalesOrder screen
            ObservableList<SalesOrderItem> orderLines =
                    SalesOrderItem.getMenuItemsBySalesOrderID(orderId);

            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/sales-order.fxml"));
            Parent newRoot = loader.load();

            SalesOrderController controller = loader.getController();
            controller.loadOrder(orderLines);


            Scene newScene = new Scene(newRoot);


            // Get the current stage (e.g., from a component's scene and window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Sales-Order Report");

        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() +"\nError loading sales order view");
        }
    }

    @FXML
    public void returnClick(ActionEvent event) {
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
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " \nError loading back to the Main Menu.");
        }
    }


    @FXML
    public void removeItemClick() {
        removeSelectedItem();
    }

    private void invalidMenuOrder(String title,String msg){
        Alert processingAlert = new Alert(Alert.AlertType.INFORMATION);
        processingAlert.setTitle(title);
        processingAlert.setHeaderText(null);
        processingAlert.setContentText(msg);
        processingAlert.show();
    }

    private void invalidMenuOrder3(String title,String header,String msg){
        Alert processingAlert = new Alert(Alert.AlertType.WARNING);
        processingAlert.setTitle(title);
        processingAlert.setHeaderText(header);
        processingAlert.setContentText(msg);
        processingAlert.show();
    }
}