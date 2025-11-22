package com.example.posapp.models;

import com.example.posapp.LogConfig;
import database.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sales {
    LocalDate date;
    private double revenue;
    private double cost;
    private int sale_Id;

    private static final Logger LOGGER = LogConfig.getLogger(Sales.class.getName());

    public Sales(double revenue, double cost, LocalDate date) {
        this.revenue = revenue;
        this.cost = cost;
        this.date = date;
    }

    public int getSale_Id() {
        return sale_Id;
    }

    public void setSale_Id(int sale_Id) {
        this.sale_Id = sale_Id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return Double.compare(getRevenue(), sales.getRevenue()) == 0 && Double.compare(getCost(), sales.getCost()) == 0 && Objects.equals(getDate(), sales.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getRevenue(), getCost());
    }

    //read
    public static List<Sales> getAllSales() {
        List<Sales> list = new ArrayList<>();

        final String sql = "SELECT revenue, cost, sale_date FROM sales";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                double revenue = rs.getDouble("revenue");
                double cost = rs.getDouble("cost");
                LocalDate date = rs.getTimestamp("sale_date")
                        .toLocalDateTime()
                        .toLocalDate();

                list.add(new Sales(revenue, cost, date));
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while getting sales from database.");
        }
        return list;
    }

    //create
      public boolean insertSale(){
        final String insertSQL = "INSERT INTO sales (revenue, cost, sale_date) VALUES (?, ?, ?)";

        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)){

            pstmt.setDouble(1, this.revenue);
            pstmt.setDouble(2, this.cost);
            pstmt.setDate(3, Date.valueOf((this.date)));

            int insertRow = pstmt.executeUpdate();
            //check if row was inserted successfully
            if(insertRow > 0){
                //Get ResultSet containing the auto-generated payment_ID from current insertion
                ResultSet resultSet = pstmt.getGeneratedKeys();
                //move to first row and check if exist
                if(resultSet.next()){
                    this.sale_Id = resultSet.getInt(1);//get generated payment_ID
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "Database Error inserting payment record.");
            return false;
        }
    }
}



