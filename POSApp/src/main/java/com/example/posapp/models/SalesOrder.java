package com.example.posapp.models;

import com.example.posapp.LogConfig;
import database.ConfigManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesOrder {
    private int order_id;
    private String status;
    private String created_at;
    private String finalized_at;
    private double subtotal;
    private double tax_total;
    private double total;
    private double totalCostPrice;


    private List <SalesOrderItem> items;

    private static final Logger LOGGER = LogConfig.getLogger(SalesOrder.class.getName());


    public SalesOrder() {
       items = new ArrayList<>();
    }

    public SalesOrder(double subtotal,String created_at) {
        this.subtotal = subtotal;
        this.created_at = created_at;
        this.items = new ArrayList<>();
    }


    public SalesOrder(int order_id, String status, String created_at, String finalized_at, double subtotal, double tax_total, double total) {
        this.order_id = order_id;
        this.status = status;
        this.created_at = created_at;
        this.finalized_at = finalized_at;
        this.subtotal = subtotal;
        this.tax_total = tax_total;
        this.total = total;
        this.items = new ArrayList<>();
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getFinalized_at() {
        return finalized_at;
    }

    public void setFinalized_at(String finalized_at) {
        this.finalized_at = finalized_at;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax_total() {
        return tax_total;
    }

    public void setTax_total(double tax_total) {
        this.tax_total = tax_total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalCostPrice() {
        return totalCostPrice;
    }

    public void setTotalCostPrice(double totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }

    public List<SalesOrderItem> getItems() {
        return items;
    }

    public void setItems(List<SalesOrderItem> items) {
        this.items = items;
    }

    public void loadItems() {
        this.items = SalesOrderItem.getItemsForOrder(this.order_id);
    }

    //CRUD
    public static List<SalesOrder> getAllSales(){
        List<SalesOrder> list = new ArrayList<>();

        final String sql = "SELECT subtotal, totalCostPrice FROM sale_order";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                SalesOrder so = new SalesOrder();
                so.setSubtotal(resultSet.getDouble("subtotal"));
                so.setTotalCostPrice(resultSet.getDouble("totalCostPrice"));
                list.add(so);
            }

        }catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + "\nError while adding Sale to SalesOrder");
        }
        return list;
    }

    public static int addSaleOrderInitial(){
        final String sql = "INSERT INTO sale_order (subtotal, totalCostPrice) VALUES(?,?)";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setDouble(1,0);
            preparedStatement.setDouble(2, 0);

            int insertRow = preparedStatement.executeUpdate();

            if(insertRow > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    return resultSet.getInt(1);//return orderID
                }
            }
        }
        catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + "\nError while adding Sale to SalesOrder");
        }
        return -1;//get orderID failed
    }


    //Adding to SALES_ORDER when 'checkout' button is clicked
    public static int addSale(double subtotal, double totalCostPrice, int orderId){
        final String sql = "UPDATE sale_order SET subtotal = ?, totalCostPrice = ? WHERE order_id = ?";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setDouble(1,subtotal);
            preparedStatement.setDouble(2, totalCostPrice);
            preparedStatement.setInt(3, orderId);

            int insertRow = preparedStatement.executeUpdate();

            if(insertRow > 0){
                return orderId;
            }
        }
        catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + "\nError while adding Sale to SalesOrder");
        }
        return -1;//get orderID failed
    }


    public static void finalizeSale(int order_id, String status, String finalized_at, double subtotal, double tax_total, double total){// double total
        final String sql = "UPDATE sale_order SET status = ?, finalized_at = ?, subtotal = ?, tax_total = ?, total = ? WHERE order_id = ?;";//total = ?

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, finalized_at);
            preparedStatement.setDouble(3, subtotal);
            preparedStatement.setDouble(4, tax_total);
            preparedStatement.setDouble(5, total);
            preparedStatement.setInt(6, order_id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + "\nError while updating Sale in SalesOrder");
        }
    }

    public static void cancelledOrder(int order_id){
        final String sql = "DELETE FROM sale_order WHERE order_id = ?;";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, order_id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + "\nError while deleting a sale in SalesOrder");
        }
    }

    public static void updateQuantityItems(int id, int quantity){
        final String sql = "UPDATE popular_items SET popular_items_quantity = ? + popular_items_quantity WHERE popular_items_id = ?;";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + "\nError while updating the quantities in PopularItems");
        }
    }

    public static int sizeSalesOrder() {
        final String sql = "SELECT order_id FROM sale_order ORDER BY order_id DESC LIMIT 1;";

        try (Connection connection = ConfigManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int num  = rs.getInt("order_id");
                return num;
            }

        } catch (SQLException | ParserConfigurationException | IOException | SAXException e) {
            LOGGER.log(Level.SEVERE, "Error getting the size of sale_order", e);
        }

        return 0;
    }


}

