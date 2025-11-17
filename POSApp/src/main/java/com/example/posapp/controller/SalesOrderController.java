package com.example.posapp.controller;

import com.example.posapp.models.Inventory;
import com.example.posapp.models.MenuItem;
import com.example.posapp.models.SalesOrder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.util.Map;

public class SalesOrderController {
    @FXML private TextField totalPriceText;
    @FXML private TableView<SalesOrder> orderTableView;
    @FXML private TableColumn<SalesOrder, String> colItemName;
    @FXML private TableColumn<SalesOrder, Integer> colQuantity;
    @FXML private TableColumn<SalesOrder, Double> colPrice;

    public void initialize() {
        innitAndLoadInventory();
    }

    @FXML
    public void innitAndLoadInventory() {
        orderTableView.setEditable(true);

        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colQuantity.setOnEditCommit(event -> {
            SalesOrder order = event.getRowValue();
            order.setQuantity(event.getNewValue());
            refreshSubTotal();
        });
    }

    public void loadOrder(Map<Integer, Integer> activeOrder, Map<Integer, MenuItem> menuItems) {
        double totalPrice = 0;

        for (Map.Entry<Integer, Integer> entry : activeOrder.entrySet()) {
            MenuItem item = menuItems.get(entry.getKey());
            String name = item.getName();
            int quantity = entry.getValue();
            double price = item.getPrice();

            SalesOrder so = new SalesOrder(name, quantity, price);
            orderTableView.getItems().add(so);

            totalPrice += quantity * price;
        }

        totalPriceText.setText("$ " + Double.toString(totalPrice));
    }

    public void refreshSubTotal() {
        double totalPrice = 0;
        for(int i = 0 ; i < orderTableView.getItems().size() ; i++) {
            int quantity = orderTableView.getItems().get(i).getQuantity();
            double price = orderTableView.getItems().get(i).getPrice();
            totalPrice += quantity * price;
        }
        totalPriceText.setText("$ " + Double.toString(totalPrice));
    }
}

