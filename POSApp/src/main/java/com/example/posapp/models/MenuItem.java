package com.example.posapp.models;

import database.ConfigManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class MenuItem {
    private int id;
    private String name;
    private double price,costPrice;
    private Inventory inventory;

    public MenuItem(int id, String name, double price, double costPrice, Inventory inventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.costPrice = costPrice;
        this.inventory = inventory;
    }

    public MenuItem(int id, String name, double price, double costPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.costPrice = costPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    //crud

    public static ObservableList<MenuItem> getMenuItems() {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
        //get the statement
        final String sql = "SELECT * FROM menu_item";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                double costPrice = resultSet.getDouble("costPrice");
                int inventoryId = resultSet.getInt("inv_id");


                menuItems.add(new MenuItem(id,name,price,costPrice, (Inventory) Inventory.getInvbyInvId(inventoryId)));
            }
        }
        catch (Exception e) {
            //LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " /n Database error while fetching inventory");
        }
        return menuItems;
    }

}
