package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.*;
import com.example.posapp.models.MenuItem;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.posapp.models.SalesOrder.*;


public class SalesOrderController {
    @FXML
    private TextField totalPriceText;
    @FXML
    private TableView<SalesOrderItem> orderTableView;
    @FXML
    private TableColumn<SalesOrderItem, String> colItemName;
    @FXML
    private TableColumn<SalesOrderItem, Integer> colQuantity;
    @FXML
    private TableColumn<SalesOrderItem, Double> colPrice;
    @FXML
    private TextField searchText;

    // current rows in the table (each is a menu item + qty + price)
    private ObservableList<SalesOrderItem> items;

    // for popular-items table: menuId -> quantity sold
    private HashMap<Integer, Integer> popularItemsSaleMap = new HashMap<>();

    // list copy used when adjusting inventory
    private ArrayList<SalesOrderItem> listOfItems = new ArrayList<>();


    private static final Logger LOGGER = LogConfig.getLogger(SalesOrderController.class.getName());

    private boolean inventoryAdjusted = false;

    public HashMap<Integer, Integer> getPopularItemsSaleMap() {
        return popularItemsSaleMap;
    }

    public void initialize() {
        innitAndLoadInventory();
    }

    @FXML
    public void innitAndLoadInventory() {
        colItemName.setCellValueFactory(cellData -> {
            SalesOrderItem soi = cellData.getValue();
            MenuItem mi = soi.getMenuItem();
            String name = (mi != null) ? mi.getName() : "Unknown";
            return new SimpleStringProperty(name);
        });

        colQuantity.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(
                        cellData.getValue().getQuantityUsed()
                ).asObject()
        );

        // price column: derived from MenuItem price
        colPrice.setCellValueFactory(cellData -> {
            SalesOrderItem soi = cellData.getValue();
            MenuItem mi = soi.getMenuItem();
            double price = (mi != null) ? mi.getPrice() : 0.0;
            return new SimpleDoubleProperty(price).asObject();
        });

        colItemName.setReorderable(false);
        colQuantity.setReorderable(false);
        colPrice.setReorderable(false);

        colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    // called from MenuItemConntroller after user clicks "Proceed"
    public void loadOrder(ObservableList<SalesOrderItem> orderLines) {
        if (items == null) {
            items = FXCollections.observableArrayList();
        } else {
            items.clear();  // Clear old items to avoid duplicates
        }

        items.addAll(orderLines);

        listOfItems.clear();
        listOfItems.addAll(items);

        orderTableView.setItems(items);
        refreshSubTotal();
    }

    // called from PaymentController when payment is successful
    public void applyInventoryForCurrentOrder() {
        // If we already changed the inventory once, stop so we don’t do it again
        if (inventoryAdjusted) {
            return;
        }

        //getting each ingredient from the sales order needed
        for (SalesOrderItem item : listOfItems) {
            int menuId  = item.getMenuItemId(); // The menu item’s ID number
            int orderQuantity = item.getQuantityUsed(); //amount customer bought

            // Find the menu item information
            MenuItem menuItem = MenuItem.getById(menuId);
            if (menuItem == null) {
                LOGGER.log(Level.SEVERE, "Unknown menu item id: " + menuId);
                continue;
            }

            // get all ingredients for this menu item
            List<MenuIngredient> allIngredients = menuItem.getIngredients();

            //loop thought each ingredient
            for (MenuIngredient ingredient : allIngredients) {
                int inventoryId = ingredient.getInventory().getInvId(); //inventory id
                int quantityUsed = ingredient.getQuantityUsed(); //quantity per in ingredient

                int totalQty = orderQuantity * quantityUsed;

                boolean ok = Inventory.subtractQuantitySafe(inventoryId, totalQty);
                if (!ok) {
                    LOGGER.log(Level.SEVERE, "Inventory subtraction failed");
                }
            }
        }
        // Mark that we already changed the inventory so it won’t happen twice
        inventoryAdjusted = true;
    }

    public void refreshSubTotal() {
        double totalPrice = 0;
        for (SalesOrderItem item : orderTableView.getItems()) {
            MenuItem menuItem = item.getMenuItem();
            if(menuItem==null) continue;
            totalPrice += item.getQuantityUsed() * menuItem.getPrice();
        }
        totalPriceText.setText("$ " + String.format("%.2f", totalPrice));
    }

    public double getSubtotalAsDouble() {
        double total = 0;
        for (SalesOrderItem item : orderTableView.getItems()) {
            MenuItem menuItem = item.getMenuItem();
            if(menuItem==null) continue;
            total += item.getQuantityUsed() * menuItem.getPrice();
        }
        return total;
    }

    public double getTotalCostPrice() {
        double totalCostPrice = 0;
        for (SalesOrderItem item : listOfItems) {
            MenuItem menuItem = item.getMenuItem();
            if(menuItem==null) continue;

            totalCostPrice += item.getQuantityUsed() * menuItem.getPrice();
        }
        return totalCostPrice;
    }


    @FXML
    public void searchButtonClick() {
        String search = searchText.getText();

        String lowerCaseSearch = search.toLowerCase();

        ObservableList<SalesOrderItem> filteredSalesOrderList = FXCollections.observableArrayList();
        for (SalesOrderItem item : items) {
            MenuItem menuItem = item.getMenuItem();
            if(menuItem==null) continue;
            String menuItemName = menuItem.getName();
            if(menuItemName.toLowerCase().contains(lowerCaseSearch)) {
                filteredSalesOrderList.add(item);
            }
        }
        orderTableView.setItems(filteredSalesOrderList);
    }

    @FXML
    public void refreshButtonClick() {
        orderTableView.setItems(items);
        searchText.clear();
    }

    @FXML
    public void removeItem(ActionEvent event) throws IOException {
        SalesOrderItem selectedItem = orderTableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            return;
        }

        //remove from DB
        SalesOrderItem.removeItemsBySalesOrderId(selectedItem.getSalesOrderId());

        //remove for a list
        items.remove(selectedItem);
        orderTableView.refresh();
        refreshSubTotal();

        //if there are no items in the cart, go back to the menu page
        if(items.isEmpty()) {
            invalidSaleOrder("Empty Cart","List of items cannot be empty.");
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/com/example/posapp/menu-view.fxml"));
            Parent newRoot2 = loader2.load();
            Scene newScene2 = new Scene(newRoot2);

            cancelledOrder(sizeSalesOrder());

            // Get the current stage (e.g., from a component's scene and window)
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(newScene2);
            stage2.setTitle("Menu");
            return;
        }
    }

    @FXML
    public void cancelOrder(ActionEvent event) {
        try {
            //restoreInventoryForCurrentOrder(); //restocking the inventory not used
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/main-view.fxml"));
            Parent newRoot = loader.load();
            Scene newScene = new Scene(newRoot);

            cancelledOrder(sizeSalesOrder());

            // Get the current stage (e.g., from a component's scene and window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Main");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getCause()+e.getMessage()+"\nError while going back to the main");
        }
    }


    @FXML
    public void checkOutClick(ActionEvent event) {
        try {
            orderTableView.setItems(items); //debug

            listOfItems.clear(); //clearing order
            listOfItems.addAll(orderTableView.getItems()); //if change made get the new list of items

            for (SalesOrderItem item : items) {
                popularItemsSaleMap.put(item.getMenuItemId(),item.getQuantityUsed());
            }

            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/payment-view.fxml"));
            Parent newRoot = loader.load();
            Scene newScene = new Scene(newRoot);



            PaymentController paymentController = loader.getController();
            paymentController.setSalesOrderController(this);

            //Adds the sale to the database (not the completed transaction though)
            //double subtotal = Double.parseDouble(totalPriceText.getText());
            double subtotal = getSubtotalAsDouble();
            double totalCostPrice = getTotalCostPrice();

            int orderID = addSale(subtotal, totalCostPrice, sizeSalesOrder());

            paymentController.setSalesOrderTotal(this, orderID);


            // Get the current stage (e.g., from a component's scene and window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Payment");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getCause() + e.getMessage() + " /nError while trying to proceed to payment view.");
        }
    }

    public void invalidSaleOrder(String title,String msg){
        Alert processingAlert = new Alert(Alert.AlertType.INFORMATION);
        processingAlert.setTitle(title);
        processingAlert.setHeaderText(null);
        processingAlert.setContentText(msg);
        processingAlert.show();
    }
}