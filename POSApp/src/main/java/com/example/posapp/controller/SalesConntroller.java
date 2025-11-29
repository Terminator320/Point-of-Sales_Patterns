package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.PopularItems;
import com.example.posapp.models.SalesOrder;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.posapp.multithreadingprofitcalculator.ProfitCalculator;
import com.example.posapp.multithreadingprofitcalculator.ProfitCalculatorOperation;

import static com.example.posapp.models.PopularItems.popularItemsFinder;

public class SalesConntroller {

    //pir chart for overall
    @FXML private PieChart profitLossPieChart;

    @FXML private Label netProfitLbl;
    @FXML private Label profitLbl;
    @FXML private Label costLbl;

    @FXML private TableView<PopularItems> popularListView;
    @FXML private TableColumn<PopularItems, Integer> itemQuantity;
    @FXML private TableColumn<PopularItems, String> itemName;


    private static final Logger LOGGER = LogConfig.getLogger(SalesConntroller.class.getName());

    public synchronized void initialize() {
        calculateProfitAsync();
        loadPopularTable();
    }


    private void loadPopularTable() {
        HashMap<String, Integer> items = popularItemsFinder();

        ObservableList<PopularItems> popularItems = FXCollections.observableArrayList();

        items.forEach((name , quantity) -> {
            popularItems.add(new PopularItems(name,quantity));
        });


        itemName.setCellValueFactory(new PropertyValueFactory<>("popular_items_name"));
        itemQuantity.setCellValueFactory(new PropertyValueFactory<>("popular_items_quantity"));

        itemName.setReorderable(false);
        itemQuantity.setReorderable(false);

        popularListView.setItems(popularItems);
    }



    private void calculateProfitAsync() {
        Thread worker = new Thread(() -> {
            try {
                // Load all orders once
                List<SalesOrder> orders = SalesOrder.getALLSales();

                // Threads will fill ProfitCalculator.*
                ProfitCalculatorOperation.calculate(orders);

                double totalRevenue = ProfitCalculator.total;
                double totalCost    = ProfitCalculator.costTotal;
                double netProfit    = ProfitCalculator.profit;

                //double totalLossParam   = Math.abs(totalCost);  // negative for your pie
                //double totalProfitSlice = netProfit;


                //method in java where its like thread where it waits until the other tasks are done to start
                Platform.runLater(() -> {
                    loadProfitLossPie(netProfit, totalCost, totalRevenue);
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.log(Level.SEVERE, "Error calculating profit and chart data", e);
            }
        });

        //runs in the background.
        worker.setDaemon(true);
        worker.start();
    }

    public synchronized void loadProfitLossPie(double totalNetProfit, double totalCost, double totalRevenue) {
        // convert negative loss to positive for display
        //double lossAbsolute = Math.abs(totalLoss);

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Net Profit", totalNetProfit),
                new PieChart.Data("Loss", totalCost)
        );

        profitLossPieChart.setData(pieData);

        for (PieChart.Data d : profitLossPieChart.getData()) {
            if (d.getName().equals("Net Profit")) {
                d.getNode().setStyle("-fx-pie-color: #2ecc71;"); // green
            } else {
                d.getNode().setStyle("-fx-pie-color: #e74c3c;"); // red
            }
        }

        //label for the labels
        for (Node n : profitLossPieChart.lookupAll(".chart-pie-label")) {
            if (n instanceof Labeled label) {
                label.setTextFill(Color.WHITE);
            }
            if (n instanceof Text text) {
                text.setFill(Color.WHITE);
            }
        }

        //setting label
        netProfitLbl.setText("Net Profit: $" + String.format("%.2f", totalRevenue));
        costLbl.setText("Cost: $" + String.format("%.2f", totalCost));
        profitLbl.setText("Profit: $" + String.format("%.2f", totalNetProfit));
    }


    @FXML
    private void back(ActionEvent event) {
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/main-view.fxml"));
            Parent newRoot = loader.load();

            Scene newScene = new Scene(newRoot);

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Menu");
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading back to the Main Menu.");
        }
    }

}
