package com.example.posapp.models;

import com.example.posapp.LogConfig;
import database.ConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Inventory {
    private int invId, qty, lowStokeThreshold;
    private String invName;

    private static final Logger LOGGER = LogConfig.getLogger(Inventory.class.getName());



    public Inventory(String invName,int qty, int lowStokeThreshold) {
        this.invName = invName;
        this.qty = qty;
        this.lowStokeThreshold = lowStokeThreshold;
    }

    public Inventory(int invId, String invName, int qty) {
        this.invId = invId;
        this.invName = invName;
        this.qty = qty;
    }

    public Inventory(int invId, String invName, int qty, int lowStokeThreshold) {
        this.invId = invId;
        this.invName = invName;
        this.qty = qty;
        this.lowStokeThreshold = lowStokeThreshold;
    }

    public int getInvId() {
        return invId;
    }

    public void setInvId(int invId) {
        this.invId = invId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getLowStokeThreshold() {
        return lowStokeThreshold;
    }

    public void setLowStokeThreshold(int lowStokeThreshold) {
        this.lowStokeThreshold = lowStokeThreshold;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    //crud

    //read
    public static ObservableList<Inventory> getAllInventory(){
        ObservableList<Inventory> inventoryData = FXCollections.observableArrayList();
        //get the statement
        final String sql = "SELECT * FROM inventory";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                inventoryData.add(new Inventory(
                        resultSet.getInt("invId"),
                        resultSet.getString("invName"),
                        resultSet.getInt("qty"),
                        resultSet.getInt("lowStokeThreshold")
                ));
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error while fetching inventory");
        }
        return inventoryData;
    }



    //update (edit)
    public static void editLowStoke(int lowStokeThreshold,int invId) {
        final String sql = "UPDATE inventory set lowStokeThreshold = ? where invId = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, lowStokeThreshold);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
           LOGGER.log(Level.SEVERE, "Database error while editing low stoke threshold");
        }
    }

    public static void editItemQTY(int qty,int invId) {
        final String sql = "UPDATE inventory set qty = ? where invId = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, qty);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
          LOGGER.log(Level.SEVERE, "Database error while editing item quantity");
        }
    }


    //removing at the end when order has been paid
    public static void subtractQuantity(int invId, int amount){
        final String sql = "UPDATE inventory set qty = qty - ? where invId = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, amount);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database error while ");
        }
    }

}
