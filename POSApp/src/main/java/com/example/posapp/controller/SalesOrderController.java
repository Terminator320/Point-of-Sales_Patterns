package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.Inventory;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.posapp.models.SalesOrder.*;

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
    private HashMap<Integer, SalesOrder> popularItemsSaleMap = new HashMap<>();
    private ArrayList<SalesOrder> listOfOrders = new ArrayList<>();


    public HashMap<Integer, SalesOrder> getPopularItemsSaleMap() {
        return popularItemsSaleMap;
    }

    private static final Logger LOGGER = LogConfig.getLogger(SalesOrderController.class.getName());


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

    public void loadOrder(Map<Integer, Integer> activeOrder, Map<Integer, MenuItem> menuItems) {
        double totalPrice = 0;
        double totalCostPrice = 0;
        int i = 0;

        for (Map.Entry<Integer, Integer> entry : activeOrder.entrySet()) {
            MenuItem item = menuItems.get(entry.getKey());

            String name = item.getName();
            int quantity = entry.getValue();
            double price = item.getPrice();
            double costPrice = item.getCostPrice();
            int menuItemId = item.getMenuItemId();
            //int inventoryId = item.getInventory().getInvId();


            SalesOrder popularItemSale = new SalesOrder(menuItemId, quantity);
            popularItemsSaleMap.put(i, popularItemSale);
            i++;


            SalesOrder so = new SalesOrder(name, quantity, price, costPrice);
            listOfOrders.add(so);
            orderTableView.getItems().add(so);

            totalPrice += quantity * price;


            //Inventory.subtractQuantity(inventoryId, quantity);

        }

        items = orderTableView.getItems();


        totalPriceText.setText("$ " + String.format("%.2f", (totalPrice)));
    }

    //used for cancel and restore the qty
//    public void restoreInventoryForCurrentOrder() {
//        for (Map.Entry<Integer, Integer> entry : inventoryChanges.entrySet()) {
//            int inventoryId = entry.getKey();
//            int qty = entry.getValue();
//            Inventory.addQuantity(inventoryId, qty);
//        }
//        inventoryChanges.clear();
//    }

    
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

        orderTableView.getItems().remove(selectedItem); //removing the item from the table


        //restoreInventoryForCurrentOrder(); /restocking the inventory not used

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

        refreshSubTotal();
    }

    @FXML
    public void cancelOrder(ActionEvent event) {
        try {
          //  restoreInventoryForCurrentOrder(); //restocking the inventory not used
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
            orderTableView.setItems(items);

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

