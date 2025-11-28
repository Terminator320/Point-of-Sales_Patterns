package com.example.posapp.models;

import com.example.posapp.LogConfig;
import database.ConfigManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


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

        try (Connection connection = ConfigManager.getConnection();
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
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " /n Database error while fetching inventory");
        }
        return inventoryData;
    }

    public static Inventory getOne(int invId){

        Inventory item = null;
        for(int i = 0; i < getAllInventory().size(); i++){
            if(getAllInventory().get(i).getInvId() == invId){
                item = getAllInventory().get(i);
            }
        }

        return item;
    }



    //update (edit)
    public static void editLowStoke(int lowStokeThreshold,int invId) {
        final String sql = "UPDATE inventory set lowStokeThreshold = ? where invId = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, lowStokeThreshold);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
           LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " /nDatabase error while editing low stoke threshold");
        }
    }

    public static void editItemQTY(int qty,int invId) {
        final String sql = "UPDATE inventory set qty = ? where invId = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, qty);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch ( Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " /nDatabase error while editing item quantity");
        }
    }


    //removing at the end when order has been paid
    public static void subtractQuantity(int invId, int amount){
        final String sql = "UPDATE inventory set qty = qty - ? where invId = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, amount);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " /n Database error while ");
        }
    }

    public static void addQuantity(int invId, int amount) {
        final String sql = "UPDATE inventory set qty = qty + ? where invId = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, amount);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " \nDatabase error while adding back item quantity");
        }
    }

}
