package com.example.posapp.models;

import database.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesOrder {
    private int order_id;
    private String status;
    private String created_at;
    private String finalized_at;
    private int subtotal_cents;
    private int tax_total_cents;
    private int discount_cents;
    private int total_cents;

    private String itemName;
    private int quantity;
    private double price;

    public SalesOrder(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public SalesOrder(int order_id, String status, String created_at, String finalized_at, int subtotal_cents, int tax_total_cents, int discount_cents, int total_cents) {
        this.order_id = order_id;
        this.status = status;
        this.created_at = created_at;
        this.finalized_at = finalized_at;
        this.subtotal_cents = subtotal_cents;
        this.tax_total_cents = tax_total_cents;
        this.discount_cents = discount_cents;
        this.total_cents = total_cents;
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

    public int getSubtotal_cents() {
        return subtotal_cents;
    }

    public void setSubtotal_cents(int subtotal_cents) {
        this.subtotal_cents = subtotal_cents;
    }

    public int getTax_total_cents() {
        return tax_total_cents;
    }

    public void setTax_total_cents(int tax_total_cents) {
        this.tax_total_cents = tax_total_cents;
    }

    public int getDiscount_cents() {
        return discount_cents;
    }

    public void setDiscount_cents(int discount_cents) {
        this.discount_cents = discount_cents;
    }

    public int getTotal_cents() {
        return total_cents;
    }

    public void setTotal_cents(int total_cents) {
            this.total_cents = total_cents;
        }

    //CRUD

    //Adding to SALES_ORDER when 'checkout' button is clicked
    public static void addSale(double subtotal){
        final String sql = "INSERT INTO sale_order (status,subtotal_cents) VALUES(Open,?)";

        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setDouble(1,subtotal);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }

    }
}

