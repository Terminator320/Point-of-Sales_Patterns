package com.example.posapp.models;

import com.example.posapp.LogConfig;
import com.example.posapp.controller.MenuItemConntroller;
import database.ConfigManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesOrderItems {
    private int id;
    private int salesOrderId;
    private int menuItemId;


    private static final Logger LOGGER = LogConfig.getLogger(MenuItemConntroller.class.getName());

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(int salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public synchronized static void addMenuItemsToSalesOrderItems(int menuItemId, int salesOrderId) {
        final String sql = "INSERT INTO sales_order_items (menu_item_id,sales_order_id) VALUES(?,?) ";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1,menuItemId);
            pstmt.setInt(2, salesOrderId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, " error while adding Menu Items to the Sales Order Table");
        }
    }

    public synchronized static ObservableList<MenuItem> getMenuItemsBySalesOrderID(int id) {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
        //get the statement
        final String sql = "SELECT menu_item_id FROM sales_order_items WHERE sales_order_id = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {

            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, " error while fetching Menu Items with the sales order id");
        }
        return menuItems;
    }
}
