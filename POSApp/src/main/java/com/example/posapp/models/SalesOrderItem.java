package com.example.posapp.models;

import com.example.posapp.LogConfig;
import com.example.posapp.controller.MenuItemConntroller;
import database.ConfigManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesOrderItem {
    private int salesOrderItemId; //PK
    private int salesOrderId; //FK
    private int menuItemId; //FK
    private int quantityUsed;

    private static final Logger LOGGER = LogConfig.getLogger(SalesOrderItem.class.getName());

    public SalesOrderItem(int salesOrderItemId, int salesOrderId, int menuItemId, int quantityUsed) {
        this.salesOrderItemId = salesOrderItemId;
        this.salesOrderId = salesOrderId;
        this.menuItemId = menuItemId;
        this.quantityUsed = quantityUsed;
    }

    public int getSalesOrderItemId() {
        return salesOrderItemId;
    }

    public void setSalesOrderItemId(int salesOrderItemId) {
        this.salesOrderItemId = salesOrderItemId;
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

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(int quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public MenuItem getMenuItem() {
        return MenuItem.getById(this.menuItemId); // Assuming you have a method to fetch a MenuItem by ID
    }

    // Method to get all SalesOrderItems for a given sales order ID
    public synchronized static ObservableList<SalesOrderItem> getMenuItemsBySalesOrderID(int salesOrderId) {
        ObservableList<SalesOrderItem> menuItems = FXCollections.observableArrayList();

        final String sql = "SELECT sales_order_items_id, menu_item_id, sales_order_id, quantity " +
                "FROM sales_order_items WHERE sales_order_id = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, salesOrderId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sales_order_items_id");
                int menuId = rs.getInt("menu_item_id");
                int soId = rs.getInt("sales_order_id");
                int qty = rs.getInt("quantity");

                menuItems.add(new SalesOrderItem(id, soId, menuId, qty));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while fetching menu items for the sales order ID: " + salesOrderId, e);
        }

        return menuItems;
    }

    // Method to add a menu item to an order
    public static void addMenuItemToOrder(int menuItemId, int salesOrderId, int quantityToAdd) {
        final String sql = "INSERT INTO sales_order_items (menu_item_id, sales_order_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = ConfigManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, menuItemId);
            ps.setInt(2, salesOrderId);
            ps.setInt(3, quantityToAdd);

            ps.executeUpdate();

        } catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, "Error inserting sales order item", e);
        }
    }

    // Method to remove all items for a canceled order
    public static void removeItemsBySalesOrderId(int salesOrderId) {
        final String sql = "DELETE FROM sales_order_items WHERE sales_order_id = ?";

        try (Connection conn = ConfigManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, salesOrderId);
            ps.executeUpdate();

        } catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, "Error removing items for canceled order", e);
        }
    }

    public static List<SalesOrderItem> getItemsForOrder(int salesOrderId) {
        List<SalesOrderItem> items = new ArrayList<>();
        final String sql = "SELECT sales_order_items_id, menu_item_id, sales_order_id, quantity " +
                "FROM sales_order_items WHERE sales_order_id = ?";

        try (Connection conn = ConfigManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, salesOrderId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("sales_order_items_id");
                int menuId = rs.getInt("menu_item_id");
                int soId = rs.getInt("sales_order_id");
                int qty = rs.getInt("quantity");

                items.add(new SalesOrderItem(id, soId, menuId, qty));
            }

        } catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, "Error loading items for order ID: " + salesOrderId, e);
        }

        return items;
    }



}
