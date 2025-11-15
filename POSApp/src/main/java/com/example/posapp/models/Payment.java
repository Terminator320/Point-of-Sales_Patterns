package com.example.posapp.models;

import database.ConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {

    public enum PaymentMethod{
        CASH, CREDIT, DEBIT
    }

    //ATTRIBUTES
    private int paymentID;
    private int orderID;
    private PaymentMethod methodPayment;//cash, credit, debit
    private BigDecimal tips;
    private LocalDateTime paymentDate;

    //CONSTRUCTORS
    public Payment(int paymentID, int orderID, PaymentMethod methodPayment, BigDecimal tips, LocalDateTime paymentDate) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.methodPayment = methodPayment;

        //2 = two digit after decimal point
        //RoundingMode.HALF_UP = round nearest point
        this.tips = tips.setScale(2, RoundingMode.HALF_UP);
        this.paymentDate = paymentDate;
    }

    public Payment(int orderID, PaymentMethod methodPayment, BigDecimal tips, LocalDateTime paymentDate) {
        this.orderID = orderID;
        this.methodPayment = methodPayment;
        this.tips = tips.setScale(2, RoundingMode.HALF_UP);;
        this.paymentDate = paymentDate;
    }

    //GETTERS AND SETTERS
    public int getPaymentID(){
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getOrderID(){
        return orderID;
    }
    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public PaymentMethod getMethodPayment(){
        return methodPayment;
    }
    public void setMethodPayment(PaymentMethod methodPayment){
        this.methodPayment = methodPayment;
    }

    public BigDecimal getTips(){
        return tips;
    }
    public void setTips(BigDecimal tips){
        this.tips = tips.setScale(2, RoundingMode.HALF_UP);;
    }

    public LocalDateTime getPaymentDate(){
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String formatTips() {
        return "$" + tips.setScale(2, RoundingMode.HALF_UP).toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentID == payment.paymentID
                && orderID == payment.orderID
                && methodPayment == payment.methodPayment
                && Objects.equals(tips, payment.tips)
                && Objects.equals(paymentDate, payment.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentID, orderID, methodPayment, tips, paymentDate);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", orderID=" + orderID +
                ", methodPayment='" + methodPayment + '\'' +
                ", tips=" + formatTips() +
                ", paymentDate=" + paymentDate +
                '}';
    }


    //CRUD OPERATIONS
    //CREATE
    public boolean insertPayment(){
        final String insertSQL = "INSERT INTO payment (order_ID, method_payment, tips, payment_date) VALUES (?, ?, ?, ?)";

        try(Connection connection = ConnectionManager.getConnection();
            //statement for returning payment_ID after insertion
            PreparedStatement pstmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)){

            pstmt.setInt(1, this.orderID);
            pstmt.setString(2, this.methodPayment.name());
            pstmt.setBigDecimal(3, this.tips);
            pstmt.setTimestamp(4, Timestamp.valueOf(this.paymentDate));


            int insertRow = pstmt.executeUpdate();
            //check if row was inserted successfully
            if(insertRow > 0){
                //Get ResultSet containing the auto-generated payment_ID from current insertion
                ResultSet resultSet = pstmt.getGeneratedKeys();
                //move to first row and check if exist
                if(resultSet.next()){
                    this.paymentID = resultSet.getInt(1);//get generated payment_ID
                    return true;
                }
            }
            return false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //READ
    public static ObservableList<Payment> getAllPayment(){
        ObservableList<Payment> paymentData = FXCollections.observableArrayList();
        final String sql = "SELECT * FROM payment";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                paymentData.add(new Payment(
                        resultSet.getInt("payment_ID"),
                        resultSet.getInt("order_ID"),
                        PaymentMethod.valueOf(resultSet.getString("method_payment")),
                        resultSet.getBigDecimal("tips"),
                        resultSet.getTimestamp("payment_date").toLocalDateTime()
                        ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentData;
    }

    //UPDATE
    public boolean updatePayment(){
        final String insertSQL = "UPDATE payment SET order_ID = ?, method_payment = ?, " +
                                    "tips = ? WHERE payment_ID = ?";

        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(insertSQL)){

            pstmt.setInt(1, this.orderID);
            pstmt.setString(2, this.methodPayment.name());
            pstmt.setBigDecimal(3, this.tips);
            pstmt.setInt(4, this.paymentID);

            return  pstmt.executeUpdate() > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //DELETE
    public boolean deletePayment(){
        final String insertSQL = "DELETE FROM payment WHERE payment_ID = ?";

        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(insertSQL)){

            pstmt.setInt(1, this.paymentID);
            return  pstmt.executeUpdate() > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
