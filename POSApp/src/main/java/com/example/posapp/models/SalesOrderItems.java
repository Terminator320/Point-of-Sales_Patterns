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

    public synchronized static ObservableList<SalesOrder> getMenuItemsBySalesOrderID(int id) {
        ObservableList<SalesOrder> menuItems = FXCollections.observableArrayList();
        //get the statement
        final String sql = "SELECT MI.menuItem_id ,MI.name,(COUNT(SOI.menu_item_id)) AS quantity,MI.price,MI.costPrice " +
                            "FROM menu_item MI " +
                            "JOIN sales_order_items SOI ON SOI.menu_item_id = MI.menuItem_id " +
                            "WHERE SOI.sales_order_id = ? " +
                            "GROUP BY MI.menuItem_id, MI.menuItem_id, MI.name, MI.price, MI.costPrice;";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int menuItemId = resultSet.getInt("menuItem_id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                double costPrice = resultSet.getDouble("costPrice");
                menuItems.add(new SalesOrder(menuItemId,name, quantity, price, costPrice));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, " error while fetching Menu Items with the sales order id");
        }
        return menuItems;
    }

    public synchronized static void removeItems(String name, int salesOrderId) {
        final String sql = "DELETE soi FROM sales_order_items soi " +
                "JOIN menu_item mi ON soi.menu_item_id = mi.menuItem_id " +
                "WHERE mi.name = ? AND soi.sales_order_id = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, salesOrderId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, " error while removing Menu Items from the Sales Order Table");
        }
    }
}
