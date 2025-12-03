package com.example.posapp.models;

import com.example.posapp.LogConfig;
import com.example.posapp.controller.MenuItemConntroller;
import database.ConfigManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuItem {
    private int menuItemId;
    private String name;
    private double price,costPrice;


    private List<MenuIngredient> ingredients = new ArrayList<>();


    //logger
    private static final Logger LOGGER = LogConfig.getLogger(MenuItem.class.getName());


    public MenuItem(int menuItemId, String name, double price, double costPrice) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.costPrice = costPrice;
        this.ingredients = MenuIngredient.getByMenuItemId(menuItemId);
    }



    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
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

    public double getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public void addIngredient(MenuIngredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<MenuIngredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return this.name;
    }

    //crud
    public static ObservableList<MenuItem> getMenuItems() {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
        //get the statement
        final String sql = "SELECT menuItem_id, name, price, costPrice FROM menu_item";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("menuItem_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                double costPrice = resultSet.getDouble("costPrice");

                // this constructor will auto-load ingredients from menu_item_ingredient
                MenuItem item = new MenuItem(id, name, price, costPrice);
                menuItems.add(item);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " \n Database error while fetching Menu Items");
        }
        return menuItems;
    }
}
