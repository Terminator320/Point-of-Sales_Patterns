package com.example.posapp.models;

import com.example.posapp.LogConfig;
import database.ConfigManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Inventory {
    private int invId, qty, lowStockThreshold;
    private String invName;

    private static final Logger LOGGER = LogConfig.getLogger(Inventory.class.getName());



    public Inventory(String invName,int qty, int lowStockThreshold) {
        this.invName = invName;
        this.qty = qty;
        this.lowStockThreshold = lowStockThreshold;
    }

    public Inventory(int invId, String invName, int qty) {
        this.invId = invId;
        this.invName = invName;
        this.qty = qty;
    }

    public Inventory(int invId, String invName, int qty, int lowStockThreshold) {
        this.invId = invId;
        this.invName = invName;
        this.qty = qty;
        this.lowStockThreshold = lowStockThreshold;
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

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return getInvId() == inventory.getInvId() && getQty() == inventory.getQty() && getLowStockThreshold() == inventory.getLowStockThreshold() && Objects.equals(getInvName(), inventory.getInvName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvId(), getQty(), getLowStockThreshold(), getInvName());
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
                        resultSet.getInt("lowStockThreshold")
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
                System.out.println("Name: "+  item.getInvName() + ", Quantity"+ item.qty);
            }
        }
        return item;
    }



    //update (edit)
    public static void editLowStoke(int lowStockThreshold,int invId) {
        final String sql = "UPDATE inventory set lowStockThreshold = ? where invId = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, lowStockThreshold);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
           LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " \nDatabase error while editing low stoke threshold");
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
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " \nDatabase error while editing item quantity");
        }
    }


    //removing at the end when order has been paid
    public static boolean subtractQuantitySafe(int invId, int amount){
        final String selectSql = "SELECT qty FROM inventory WHERE invId = ?";
        final String updateSql = "UPDATE inventory SET qty = qty - ? WHERE invId = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectSql);
             PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {

            selectStmt.setInt(1, invId);
            ResultSet rs = selectStmt.executeQuery();
            if (!rs.next()) return false;

            int currentQty = rs.getInt("qty");
            if (currentQty < amount) {
                LOGGER.log(Level.SEVERE, currentQty + " is less than " + amount);
                return false; // not enough stock, do not update
            }

            updateStmt.setInt(1, amount);
            updateStmt.setInt(2, invId);
            updateStmt.executeUpdate();
            return true;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause()
                    + " \n Database error while removing quantity");
            return false;
        }
    }


}
