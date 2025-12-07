package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.Inventory;
import com.example.posapp.models.MenuIngredient;
import com.example.posapp.models.MenuItem;
import com.example.posapp.models.SalesOrder;
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

import javax.management.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.posapp.models.SalesOrder.*;
import static com.example.posapp.models.SalesOrderItems.getMenuItemsBySalesOrderID;
import static com.example.posapp.models.SalesOrderItems.removeItems;

public class SalesOrderController {
    @FXML
    private TextField totalPriceText;
    @FXML
    private TableView<SalesOrder> orderTableView;
    @FXML
    private TableColumn<SalesOrder, String> colItemName;
    @FXML
    private TableColumn<SalesOrder, Integer> colQuantity;
    @FXML
    private TableColumn<SalesOrder, Double> colPrice;
    @FXML
    private TextField searchText;


    private ObservableList<SalesOrder> items;
    private HashMap<Integer, Integer> popularItemsSaleMap = new HashMap<>();
    private ArrayList<SalesOrder> listOfOrders = new ArrayList<>();


    public HashMap<Integer, Integer> getPopularItemsSaleMap() {
        return popularItemsSaleMap;
    }

    private static final Logger LOGGER = LogConfig.getLogger(SalesOrderController.class.getName());

    private boolean inventoryAdjusted = false;

    public void initialize() {
        innitAndLoadInventory();
    }

    @FXML
    public void innitAndLoadInventory() {

        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colItemName.setReorderable(false);
        colQuantity.setReorderable(false);
        colPrice.setReorderable(false);

        colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    public void loadOrder(ObservableList<SalesOrder> menuItems) {
        if (items == null) {
            items = FXCollections.observableArrayList();
        } else {
            items.clear();  // Clear old items to avoid duplicates
        }

        items.addAll(menuItems);

        listOfOrders.clear();
        listOfOrders.addAll(items);

        orderTableView.setItems(items);
        refreshSubTotal();
    }

    // called from PaymentController when payment is successful
    public void applyInventoryForCurrentOrder() {
        // Optional: prevent double-apply
        if (inventoryAdjusted) {
            return;
        }


        //getting each ingredient from the sales order needed
        for (SalesOrder order : listOfOrders) {
            int menuId  = order.getMenu_id();
            int orderQuantity = order.getQuantity();

            //testing
            System.out.println("Processing order: menuId=" + menuId +  ", qty=" + orderQuantity);

            // FIXED: using getById as you asked earlier
            MenuItem menuItem = MenuItem.getById(menuId);
            if (menuItem == null) {
                System.out.println("No MenuItem found for menuId=" + menuId);
                continue;
            }

            // get all ingredients for this menu item
            List<MenuIngredient> allIngredients = menuItem.getIngredients();

            //loop thought each ingredient
            for (MenuIngredient ingredient : allIngredients) {
                int inventoryId = ingredient.getInventory().getInvId(); //inventory id
                int quantityUsed = ingredient.getQuantityUsed(); //quantity per in ingredient

                int totalQty = orderQuantity * quantityUsed;

                //these are for testing and debuging REMOVE AFTER
                System.out.println("TotalQTY: " + totalQty);

                System.out.println("   uses invId=" + inventoryId
                        + " (" + ingredient.getInventory().getInvName() + ")"
                        + ", qtyUsedPerItem=" + quantityUsed
                        + ", totalSubtract=" + totalQty);


                boolean ok = Inventory.subtractQuantitySafe(inventoryId, totalQty);
                if (!ok) {
                    System.out.println("ERROR: not enough stock for invId=" + inventoryId);
                }

            }
        }
        inventoryAdjusted = true;
    }


    public void refreshSubTotal() {
        double totalPrice = 0;
        for (int i = 0; i < orderTableView.getItems().size(); i++) {
            int quantity = orderTableView.getItems().get(i).getQuantity();
            double price = orderTableView.getItems().get(i).getPrice();
            totalPrice += quantity * price;
        }
        totalPriceText.setText("$ " + String.format("%.2f", (totalPrice)));
    }

    public double getSubtotalAsDouble() {
        double total = 0;
        for (SalesOrder order : orderTableView.getItems()) {
            total += order.getQuantity() * order.getPrice();
        }
        return total;
    }

    public double getTotalCostPrice() {
        double totalCostPrice = 0;
        for (SalesOrder order : listOfOrders) {
            totalCostPrice += order.getQuantity() * order.getTotalCostPrice();
        }
        return totalCostPrice;
    }


    @FXML
    public void searchButtonClick() {
        String search = searchText.getText();
        ObservableList<SalesOrder> salesOrdersList = items;

        ObservableList<SalesOrder> filteredSalesOrderList = FXCollections.observableArrayList();
        for (SalesOrder salesOrder : salesOrdersList) {
            if (salesOrder.getItemName().contains(search)) {
                filteredSalesOrderList.add(salesOrder);
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
        SalesOrder selectedItem = orderTableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            return;
        }
        String menuRemovedName = selectedItem.getItemName();
        removeItems(menuRemovedName, sizeSalesOrder());


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

            listOfOrders.clear(); //clearing order
            listOfOrders.addAll(orderTableView.getItems()); //if change made get the new list of items

            for (SalesOrder order : items) {
                popularItemsSaleMap.put(order.getMenu_id(), order.getQuantity());
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

