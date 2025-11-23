package com.example.posapp.models;

import com.example.posapp.LogConfig;
import com.example.posapp.controller.SalesOrderController;
import database.ConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SalesOrder {
    private int order_id;
    private String status;
    private String created_at;
    private String finalized_at;
    private double subtotal;
    private double tax_total;
    private double discount_total;
    private double total;

    private int id;
    private String itemName;
    private int quantity;
    private double price;
    private double costPrice;

    private static final Logger LOGGER = LogConfig.getLogger(SalesOrder.class.getName());

    public SalesOrder(int id,int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public SalesOrder(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public SalesOrder(int order_id, String status, String created_at, String finalized_at, double subtotal, double tax_total, double total) {
        this.order_id = order_id;
        this.status = status;
        this.created_at = created_at;
        this.finalized_at = finalized_at;
        this.subtotal = subtotal;
        this.tax_total = tax_total;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getFinalized_at() {
        return finalized_at;
    }

    public void setFinalized_at(String finalized_at) {
        this.finalized_at = finalized_at;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax_total() {
        return tax_total;
    }

    public void setTax_total(double tax_total) {
        this.tax_total = tax_total;
    }

    public double getDiscount() {
        return discount_total;
    }

    public void setDiscount(double discount_total) {
        this.discount_total = discount_total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
            this.total = total;
        }

    //CRUD

    //Adding to SALES_ORDER when 'checkout' button is clicked
    public static int addSale(double subtotal){
        final String sql = "INSERT INTO sale_order (subtotal) VALUES(?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setDouble(1,subtotal);

            int insertRow = preparedStatement.executeUpdate();

            if(insertRow > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    return resultSet.getInt(1);//return orderID
                }
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while adding Sale to SalesOrder");
        }
        return -1;//get orderID failed
    }

    public static void finalizeSale(int order_id, String status, String finalized_at, double subtotal, double tax_total, double total){// double total
        final String sql = "UPDATE sale_order SET status = ?, finalized_at = ?, subtotal = ?, tax_total = ?, total = ? WHERE order_id = ?;";//total = ?

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, finalized_at);
            preparedStatement.setDouble(3, subtotal);
            preparedStatement.setDouble(4, tax_total);
            preparedStatement.setDouble(5, total);
            preparedStatement.setInt(6, order_id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while updating Sale in SalesOrder");
        }
    }

    public static void cancelledOrder(int order_id){
        final String sql = "DELETE FROM sale_order WHERE order_id = ?;";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, order_id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while deleting a sale in SalesOrder");
        }
    }

    public static void updateQuantityItems(int id, int quantity){
        final String sql = "UPDATE popular_items SET popular_items_quantity = ? + popular_items_quantity WHERE popular_items_id = ?;";

        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while updating the quantities in PopularItems");
        }
    }
}

