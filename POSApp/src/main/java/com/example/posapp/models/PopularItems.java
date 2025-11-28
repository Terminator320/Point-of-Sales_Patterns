package com.example.posapp.models;

import com.example.posapp.LogConfig;
import com.example.posapp.controller.InventoryController;
import database.ConfigManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PopularItems {
    private int popular_items_id;
    private String popular_items_name;
    private int popular_items_quantity;

    public PopularItems(String popular_items_name, int popular_items_quantity) {
        this.popular_items_name = popular_items_name;
        this.popular_items_quantity = popular_items_quantity;
    }

    public int getPopular_items_id() {
        return popular_items_id;
    }

    public void setPopular_items_id(int popular_items_id) {
        this.popular_items_id = popular_items_id;
    }

    public String getPopular_items_name() {
        return popular_items_name;
    }

    public void setPopular_items_name(String popular_items_name) {
        this.popular_items_name = popular_items_name;
    }

    public int getPopular_items_quantity() {
        return popular_items_quantity;
    }

    public void setPopular_items_quantity(int popular_items_quantity) {
        this.popular_items_quantity = popular_items_quantity;
    }

    private static final Logger LOGGER = LogConfig.getLogger(InventoryController.class.getName());



    public static HashMap<String, Integer> popularItemsFinder(){
        final String sql = "SELECT popular_items_name, popular_items_quantity FROM popular_items ORDER BY popular_items_quantity DESC LIMIT 5;";
        HashMap<String, Integer> popularItemsMap = new HashMap<>();

        try (Connection connection = ConfigManager.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("popular_items_name");
                int id = rs.getInt("popular_items_quantity");

                popularItemsMap.put(name, id);
            }


        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " \nDatabase error while adding popular items.");
        }

        return popularItemsMap;
    }
}
