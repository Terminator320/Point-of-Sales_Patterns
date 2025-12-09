package com.example.posapp.models;

import com.example.posapp.LogConfig;
import database.ConfigManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Payment {

    public enum PaymentMethod {
        CASH, CREDIT, DEBIT
    }

    //LOGGING
    private static final Logger LOGGER = LogConfig.getLogger(Payment.class.getName());

    //ATTRIBUTES
    private int paymentID;
    private int orderID;
    private PaymentMethod methodPayment;//cash, credit, debit
    private double tips;
    private LocalDateTime paymentDate;

    //CONSTRUCTORS
    public Payment(int paymentID, int orderID, PaymentMethod methodPayment, double tips, LocalDateTime paymentDate) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.methodPayment = methodPayment;
        this.tips = tips;
        this.paymentDate = paymentDate;
    }

    public Payment(int orderID, PaymentMethod methodPayment, double tips, LocalDateTime paymentDate) {
        this.orderID = orderID;
        this.methodPayment = methodPayment;
        this.tips = tips;
        this.paymentDate = paymentDate;
    }

    //GETTERS AND SETTERS
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public PaymentMethod getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(PaymentMethod methodPayment) {
        this.methodPayment = methodPayment;
    }

    public double getTips() {
        return tips;
    }

    public void setTips(double tips) {
        this.tips = tips;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String formatTips() {
        return String.format("$%.2f", tips);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
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
    public boolean insertPayment(int orderID) {
        final String insertSQL = "INSERT INTO payment (order_ID, method_payment, tips, payment_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, orderID);
            pstmt.setString(2, this.methodPayment.name());
            pstmt.setDouble(3, this.tips);
            pstmt.setTimestamp(4, Timestamp.valueOf(this.paymentDate));

            int insertRow = pstmt.executeUpdate();
            if (insertRow > 0) {
                ResultSet resultSet = pstmt.getGeneratedKeys();
                if (resultSet.next()) {
                    this.paymentID = resultSet.getInt(1);
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database Error inserting payment record.");
            return false;
        }
    }

    //READ
    public static ObservableList<Payment> getAllPayment() {
        ObservableList<Payment> paymentData = FXCollections.observableArrayList();
        final String sql = "SELECT * FROM payment";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                paymentData.add(new Payment(
                        resultSet.getInt("payment_ID"),
                        resultSet.getInt("order_ID"),
                        PaymentMethod.valueOf(resultSet.getString("method_payment")),
                        resultSet.getDouble("tips"),
                        resultSet.getTimestamp("payment_date").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading data from payment.");
        }
        return paymentData;
    }

    //UPDATE
    public boolean updatePayment() {
        final String insertSQL = "UPDATE payment SET order_ID = ?, method_payment = ?, " +
                "tips = ? WHERE payment_ID = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

            pstmt.setInt(1, this.orderID);
            pstmt.setString(2, this.methodPayment.name());
            pstmt.setDouble(3, this.tips);
            pstmt.setInt(4, this.paymentID);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    //DELETE
    public boolean deletePayment() {
        final String insertSQL = "DELETE FROM payment WHERE payment_ID = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

            pstmt.setInt(1, this.paymentID);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
