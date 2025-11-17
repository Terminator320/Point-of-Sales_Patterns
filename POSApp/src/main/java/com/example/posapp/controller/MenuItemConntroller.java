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

    //meunId , MenuItem
    private  Map<Integer, MenuItem> menuItems = new HashMap<>();

    private Map<Integer, Inventory> inventoryMap = new HashMap<>();

    // menuId , quantity
    private final Map<Integer, Integer> activeOrder = new HashMap<>();

    private final ObservableList<String> receiptLines = FXCollections.observableArrayList();

    public ListView<String> getRecitedView() {
        return recitedView;
    }

    public void setRecitedView(ListView<String> recitedView) {
        this.recitedView = recitedView;
    }

    public Map<Integer, MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Map<Integer, MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Map<Integer, Inventory> getInventoryMap() {
        return inventoryMap;
    }

    public void setInventoryMap(Map<Integer, Inventory> inventoryMap) {
        this.inventoryMap = inventoryMap;
    }

    public Map<Integer, Integer> getActiveOrder() {
        return activeOrder;
    }

    public ObservableList<String> getReceiptLines() {
        return receiptLines;
    }

    public void initialize() {
        recitedView.setItems(receiptLines);
        //setting the map for the menu items with its inventory
        for (Inventory inv : Inventory.getAllInventory()) {
            inventoryMap.put(inv.getItemCode(), inv);
        }

        //coffees
        menuItems.put(1, new MenuItem(1, "Espresso",3, inventoryMap.get(1)));
        menuItems.put(2, new MenuItem(2, "Latte",4.5, inventoryMap.get(2)));
        menuItems.put(3, new MenuItem(3, "Cappuccino",4.25, inventoryMap.get(3)));
        menuItems.put(4, new MenuItem(4, "Iced Americano",3.5, inventoryMap.get(4)));
        menuItems.put(5, new MenuItem(17, "Iced Coffee",2.99, inventoryMap.get(16)));
        menuItems.put(6, new MenuItem(16, "Greek Frappe",4, inventoryMap.get(17)));

        //teas
        menuItems.put(7, new MenuItem(5, "Green Tea",3, inventoryMap.get(5)));
        menuItems.put(8, new MenuItem(6, "Chai Latte",4, inventoryMap.get(6)));
        menuItems.put(9,new MenuItem(18, "Black Tea",1.5, inventoryMap.get(18)));
        menuItems.put(10,new MenuItem(19,"Oolong Tea",3,inventoryMap.get(19)));
        menuItems.put(11,new MenuItem(20,"Peach Tea",2.5,inventoryMap.get(20)));
        menuItems.put(12,new MenuItem(21,"Strawberry Tea",2.5,inventoryMap.get(21)));

        //food
        menuItems.put(20, new MenuItem(7, "Croissant",2.75, inventoryMap.get(7)));
        menuItems.put(21, new MenuItem(8, "Muffin",2.50, inventoryMap.get(8)));
        menuItems.put(22, new MenuItem(9, "Cheese Bagel",3.25, inventoryMap.get(9)));
        menuItems.put(23, new MenuItem(10, "BLT Sandwich",6.50, inventoryMap.get(10)));
        menuItems.put(24, new MenuItem(11, "Grilled Cheese",5.50, inventoryMap.get(11)));
        menuItems.put(25, new MenuItem(12, "Chicken Wrap",7.00, inventoryMap.get(12)));


        //smoothies
        menuItems.put(30, new MenuItem(22,"Strawberry Smoothie",5.75, inventoryMap.get(22)));
        menuItems.put(31, new MenuItem(13,"Berry Smoothie",5.75, inventoryMap.get(13)));
        menuItems.put(32, new MenuItem(15,"Protein Shake",6, inventoryMap.get(15)));
        menuItems.put(33,new MenuItem(14,"Mango Smoothie",5.75, inventoryMap.get(14)));

    }


    //addMethod
    private void addItemToOrder(int menuId) {
        MenuItem item = menuItems.get(menuId);
        if (item == null) return;

        // update quantity in order
        int newQty = activeOrder.getOrDefault(menuId, 0) + 1;

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
        addItemToOrder(6);
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
        addItemToOrder(7);
    }

    @FXML
    public void blackTeaClick(ActionEvent event) {
        addItemToOrder(9);
    }

    @FXML
    public void peachTeaClick(ActionEvent event) {
        addItemToOrder(11);
    }

    @FXML
    public void starwberryTeaClick(ActionEvent event) {
        addItemToOrder(12);
    }

    @FXML
    public void chaiLatteTeaClick(ActionEvent event) {
        addItemToOrder(8);
    }

    @FXML
    public void oolongTeaClick(ActionEvent event) {
        addItemToOrder(10);
    }

    @FXML
    public void starwberrySmoClick(ActionEvent event) {
        addItemToOrder(30);
    }

    @FXML
    public void mangoSmClick(ActionEvent event) {
        addItemToOrder(33);
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

            SalesOrderController controller = loader.getController();
            //controller.loadOrder(activeOrder, menuItems);

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
