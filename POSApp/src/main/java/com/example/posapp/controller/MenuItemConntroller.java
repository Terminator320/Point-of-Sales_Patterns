package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.Inventory;
import com.example.posapp.models.MenuIngredient;
import com.example.posapp.models.SalesOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.example.posapp.models.MenuItem;

import static com.example.posapp.models.SalesOrder.addSaleOrderInitial;
import static com.example.posapp.models.SalesOrder.sizeSalesOrder;
import static com.example.posapp.models.SalesOrderItems.addMenuItemsToSalesOrderItems;
import static com.example.posapp.models.SalesOrderItems.getMenuItemsBySalesOrderID;


public class MenuItemConntroller {

    @FXML private ListView<String> recitedView;

    //buttons in the fxml
    @FXML private Button espressoButton;
    @FXML private Button latteButton;
    @FXML private Button cappuccinoButton;
    @FXML private Button iceAmericanoButton;
    @FXML private Button iceCoffeeButton;
    @FXML private Button frappeButton;

    @FXML private Button greenTeaButton;
    @FXML private Button chaiLatteButton;
    @FXML private Button blackTeaButton;
    @FXML private Button oolongTeaButton;
    @FXML private Button peachTeaButton;
    @FXML private Button strawberryTeaButton;

    @FXML private Button croissantButton;
    @FXML private Button muffinButton;
    @FXML private Button cheeseBagelButton;
    @FXML private Button bltSandwichButton;
    @FXML private Button grilledCheeseButton;
    @FXML private Button chickenWrapButton;

    @FXML private Button strawberrySmoothieButton;
    @FXML private Button berrySmoothieButton;
    @FXML private Button proteinShakeButton;
    @FXML private Button mangoSmoothieButton;


    // menuId -> Button
    private final Map<Integer, Button> menuButtons = new HashMap<>();


    //menuId , MenuItem
    private  Map<Integer, MenuItem> menuItems = new HashMap<>();

    // menuId , quantity
    private final Map<Integer, Integer> activeOrder = new HashMap<>();

    // check item
    private final ObservableList<String> receiptLines = FXCollections.observableArrayList();

    //logger
    private static final Logger LOGGER = LogConfig.getLogger(MenuItemConntroller.class.getName());


    public void initialize() {
        recitedView.setItems(receiptLines);
        initMenuItems(); //setting up the menuItems

        initMenuButtons();  //setting up the buttons
        refreshButtonStates(); //checks all the inventory and if the qty is 0 disable the button
    }


    private synchronized void initMenuItems(){
        // Load all menu items from DB; each one loads its ingredients
        for (MenuItem item : MenuItem.getMenuItems()) {
            menuItems.put(item.getMenuItemId(), item);
        }
    }

    private  synchronized void initMenuButtons() {
        // coffees
        menuButtons.put(1, espressoButton);
        menuButtons.put(2, latteButton);
        menuButtons.put(3, cappuccinoButton);
        menuButtons.put(4, iceAmericanoButton);
        menuButtons.put(5, iceCoffeeButton);
        menuButtons.put(6, frappeButton);

        // teas
        menuButtons.put(7, greenTeaButton);
        menuButtons.put(8, chaiLatteButton);
        menuButtons.put(9, blackTeaButton);
        menuButtons.put(10, oolongTeaButton);
        menuButtons.put(11, peachTeaButton);
        menuButtons.put(12, strawberryTeaButton);

        // food
        menuButtons.put(13, croissantButton);
        menuButtons.put(14, muffinButton);
        menuButtons.put(15, cheeseBagelButton);
        menuButtons.put(16, bltSandwichButton);
        menuButtons.put(17, grilledCheeseButton);
        menuButtons.put(18, chickenWrapButton);

        // smoothies
        menuButtons.put(19, strawberrySmoothieButton);
        menuButtons.put(20, berrySmoothieButton);
        menuButtons.put(21, proteinShakeButton);
        menuButtons.put(22, mangoSmoothieButton);
    }

    private  synchronized void refreshButtonStates() {
        for (Map.Entry<Integer, MenuItem> entry : menuItems.entrySet()) {
            int menuId = entry.getKey();
            MenuItem item = entry.getValue();
            Button button = menuButtons.get(menuId);

            if (button == null || item == null || item.getIngredients() == null) {
                continue;
            }

            boolean canMake = true;

            // If ANY ingredient has qty == 0, disable the button
            for (MenuIngredient ingredient : item.getIngredients()) {
                Inventory inv = ingredient.getInventory();
                if (inv == null) {
                    continue;
                }
                if (inv.getQty() <= 0) {
                    canMake = false;
                    break;
                }
            }

            button.setDisable(!canMake);
        }
    }

    //addMethod
    private void addItemToOrder(int menuId) {
        MenuItem item = menuItems.get(menuId);
        if (item == null) return;

        int newQty = activeOrder.getOrDefault(menuId, 0) + 1;

        // check all ingredients
        boolean enoughStock = true;
        MenuIngredient outOfStock = null;

        for (MenuIngredient ingredient : item.getIngredients()) {
            Inventory inv = ingredient.getInventory();
            if (inv == null) continue;

            int required = ingredient.getQuantityUsed() * newQty;
            if (!Inventory.checkInventory(inv, required)) {
                enoughStock = false;
                outOfStock = ingredient;
                break;
            }
        }

        if (!enoughStock) {
            Button b = menuButtons.get(menuId);
            if (b != null) {
                b.setDisable(true);
            }

            Alert processingAlert = new Alert(Alert.AlertType.WARNING);
            processingAlert.setTitle("Out of Stock");
            processingAlert.setHeaderText(item.getName() + " is out of stock");
            if (outOfStock != null && outOfStock.getInventory() != null) {
                processingAlert.setContentText(outOfStock.getInventory().getInvName() + " is out of stock.");
            }
            else {
                processingAlert.setContentText("Not enough ingredients.");
            }
            processingAlert.show();
            return;
        }

        activeOrder.put(menuId, newQty);
        rebuildReceipt();
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
            refreshButtonStates();
            rebuildReceipt();
        }
    }

    private synchronized void rebuildReceipt() {
        receiptLines.clear();

        for (Map.Entry<Integer, Integer> e : activeOrder.entrySet()) {
            int menuId = e.getKey();
            int qty    = e.getValue();
            MenuItem item = menuItems.get(menuId);

            receiptLines.add(qty + " x " + item.getName());
        }
    }


    @FXML
    public void espressoClick(ActionEvent event) { addItemToOrder(1); }

    @FXML
    public void latteClick(ActionEvent event) { addItemToOrder(2); }

    @FXML
    public void cappuccinoClick(ActionEvent event) { addItemToOrder(3); }

    @FXML
    public void iceAmeriClick(ActionEvent event) { addItemToOrder(4); }

    @FXML
    public void iceCoffeeClick(ActionEvent event) { addItemToOrder(17); }

    @FXML
    public void frappeClick(ActionEvent event) { addItemToOrder(16); }

    @FXML
    public void greenTeaClick(ActionEvent event) { addItemToOrder(5); }

    @FXML
    public void chaiLatteTeaClick(ActionEvent event) { addItemToOrder(6); }

    @FXML
    public void blackTeaClick(ActionEvent event) { addItemToOrder(18); }

    @FXML
    public void oolongTeaClick(ActionEvent event) { addItemToOrder(19); }

    @FXML
    public void peachTeaClick(ActionEvent event) { addItemToOrder(20); }

    @FXML
    public void starwberryTeaClick(ActionEvent event) { addItemToOrder(21); }

    @FXML
    public void croissantClick(ActionEvent event) { addItemToOrder(7); }

    @FXML
    public void muffinClick(ActionEvent event) { addItemToOrder(8); }

    @FXML
    public void cheeseBagelClick(ActionEvent event) { addItemToOrder(9); }

    @FXML
    public void btlSandwichClick(ActionEvent event) { addItemToOrder(10); }

    @FXML
    public void grilledCheeseClick(ActionEvent event) { addItemToOrder(11); }

    @FXML
    public void chickenWrapClick(ActionEvent event) { addItemToOrder(12); }

    @FXML
    public void starwberrySmoClick(ActionEvent event) { addItemToOrder(22); }

    @FXML
    public void mixedBerrySmClick(ActionEvent event) { addItemToOrder(13); }

    @FXML
    public void proteinShakenClick(ActionEvent event) { addItemToOrder(15); }

    @FXML
    public void mangoSmClick(ActionEvent event) { addItemToOrder(14); }


    @FXML
    public void proceedClick(ActionEvent event) {
        try {

            if(activeOrder.isEmpty()){
                invalidMenuOrder("Proceeding to Sales Order","You can't continue without adding an item!");
            }
            else{
                // Load the FXML file for the second scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/sales-order.fxml"));
                Parent newRoot = loader.load();
                Scene newScene = new Scene(newRoot);

                addSaleOrderInitial();

                int size = sizeSalesOrder();
                activeOrder.forEach((Integer key, Integer value) -> {
                    for (int i = 0; i < value; i++) {
                        addMenuItemsToSalesOrderItems(key, size);
                    }
                });

                ObservableList<SalesOrder> menuItems = getMenuItemsBySalesOrderID(sizeSalesOrder());
                SalesOrderController controller = loader.getController();
                controller.loadOrder(menuItems);

                // Get the current stage (e.g., from a component's scene and window)
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(newScene);
                stage.setTitle("Sales-Order Report");
            }
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
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + "\nError loading back to the Main Menu.");
        }
    }


    @FXML
    public void removeItemClick() {
        removeSelectedItem();
    }

    public void invalidMenuOrder(String title,String msg){
        Alert processingAlert = new Alert(Alert.AlertType.INFORMATION);
        processingAlert.setTitle(title);
        processingAlert.setHeaderText(null);
        processingAlert.setContentText(msg);
        processingAlert.show();
    }
}