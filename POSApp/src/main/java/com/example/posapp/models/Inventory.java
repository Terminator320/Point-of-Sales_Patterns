package com.example.posapp.models;

import database.ConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Inventory {
    private int invId,qty, lowStokeThreeshold,itemCode;
    private String invName;

    public Inventory() {

    }

    public Inventory(String invName,int qty, int lowStokeThreeshold, int itemCode) {
        this.invName = invName;
        this.qty = qty;
        this.lowStokeThreeshold = lowStokeThreeshold;
        this.itemCode = itemCode;
    }

    public Inventory(String invName, int qty, int lowStokeThreeshold) {
        this.invName = invName;
        this.qty = qty;
        this.lowStokeThreeshold = lowStokeThreeshold;
    }

    public Inventory(int invId, String invName, int qty) {
        this.invId = invId;
        this.invName = invName;
        this.qty = qty;
    }

    public Inventory(int itemCode, String invName, int qty, int lowStokeThreeshold) {
        this.itemCode = itemCode;
        this.invName = invName;
        this.qty = qty;
        this.lowStokeThreeshold = lowStokeThreeshold;
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

    public int getLowStokeThreeshold() {
        return lowStokeThreeshold;
    }

    public void setLowStokeThreeshold(int lowStokeThreeshold) {
        this.lowStokeThreeshold = lowStokeThreeshold;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public int getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    //crud
    //add (create)
    public static void addItem(String invName, int qty, int lowStokeThreeshold, int itemCode ) {
        final String sql = "INSERT INTO inventory (invName,qty,lowStokeThreeshold,itemCode) VALUES(?,?,?,?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1, invName);
            pstmt.setInt(2, qty);
            pstmt.setInt(3, lowStokeThreeshold);
            pstmt.setInt(4, itemCode);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }
    }

    //if item code exists to keep unique
    public static boolean itemCodeExists(int itemCode) {
        String sql = "SELECT COUNT(*) FROM inventory WHERE itemCode = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, itemCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // true if exists
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



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
                        resultSet.getInt("itemCode"),
                        resultSet.getString("invName"),
                        resultSet.getInt("qty"),
                        resultSet.getInt("lowStokeThreeshold")
                ));
            }
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }
        return inventoryData;
    }




    //update (edit)
    public static void editLowStoke(int lowStokeThreeshold,int invId) {
        final String sql = "UPDATE inventory set lowStokeThreeshold = lowStokeThreeshold + ? where invId = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, lowStokeThreeshold);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }
    }

    public static void editItemQTY(int qty,int invId) {
        final String sql = "UPDATE inventory set qty = qty + ? where invId = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, qty);
            pstmt.setInt(2, invId);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }
    }



    //if order more stoke update the quanty they have
    public static void addQuantity(int itemCode, int amount){
        final String sql = "UPDATE inventory set qty = qty + ? where itemCode = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, amount);
            pstmt.setInt(2, itemCode);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }
    }

    //removing that the end when order has been payed
    public static void subtractQuantity(int itemCode, int amount){
        final String sql = "UPDATE inventory set qty = qty - ? where itemCode = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, amount);
            pstmt.setInt(2, itemCode);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }
    }



    //delete
    public static void deleteItem(int itemCode){
        final String sql = "DELETE FROM inventory WHERE itemCode = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1, itemCode);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }
    }









}
