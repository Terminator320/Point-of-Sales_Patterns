package com.example.posapp.models;

import database.ConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inventory {
    private int invId,qty, lowStokeThreeshold;
    private String invName;


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

    public Inventory(int invId, String invName, int qty, int lowStokeThreeshold) {
        this.invId = invId;
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


    //crud
    //add (create)


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




    //delete


    //category cb
    public static ObservableList<String> getAllCategory(){
        ObservableList<Category> categoryData = FXCollections.observableArrayList();
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        //get the statement
        final String sql = "SELECT category_name FROM category";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                while (resultSet.next()) {
                    categoryList.add(resultSet.getString("category_name"));
                }
            }
        }
        catch (SQLException e) {
            //change to logger later
            e.printStackTrace();
        }
        return categoryList;
    }

}
