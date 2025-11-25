package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.Sales;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SalesConntroller {

    //monthly line chart
    @FXML private LineChart<String, Number> monthlyProfitLineChart;


    //pir chart for overall
    @FXML private PieChart profitLossPieChart;


    @FXML private Label netProfitLbl;
    @FXML private Label profitLbl;
    @FXML private Label costLbl;
    @FXML private Label topDayOfMonthLbl;


    //map to use in bar chart and line chart
    private Map<LocalDate, Double> profitPerDay;




    private static final Logger LOGGER = LogConfig.getLogger(SalesConntroller.class.getName());

    public synchronized void initialize() {
        //Load sales from DB
        List<Sales> sales = Sales.getAllSales();

        //change later when we figure out threads for this
        // Build profitPerDay map
        profitPerDay = new HashMap<>();


        for (Sales s : sales) {
            LocalDate date = s.getDate();

            //change this to use thread when we do that
            double profit = s.getRevenue() - s.getCost();
            profitPerDay.merge(s.getDate(), profit, Double::sum);
        }


        loadMonthlyLineChart();

        //test value
        double totalNetProfit = 3400;
        double totalLoss = -1200;
        double totalProfit = totalNetProfit + totalLoss;

        loadProfitLossPie(totalNetProfit, totalLoss ,totalProfit);
    }


    public synchronized void loadProfitLossPie(double totalNetProfit, double totalLoss,double totalProfit) {
        // convert negative loss to positive for display
        double lossAbsolute = Math.abs(totalLoss);

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Net Profit", totalProfit),
                new PieChart.Data("Loss", lossAbsolute)
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
        netProfitLbl.setText("Net Profit: $" + totalNetProfit);
        costLbl.setText("Cost: $" + totalLoss);
        profitLbl.setText("Profit: $" + totalProfit);

    }



    private void loadMonthlyLineChart() {
        if (profitPerDay.isEmpty()) {
            monthlyProfitLineChart.getData().clear();
            return;
        }
        // latest date in data
        LocalDate latest = Collections.max(profitPerDay.keySet());
        LocalDate start = latest.minusDays(30);

        List<LocalDate> sortedDays = profitPerDay.keySet().stream()
                .filter(date -> !date.isBefore(start) && !date.isAfter(latest))
                .sorted()
                .collect(Collectors.toList());

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Profit per day");

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d");
        for (LocalDate day : sortedDays) {
            double profit = profitPerDay.get(day);
            String label = day.format(fmt);
            series.getData().add(new XYChart.Data<>(label, profit));
        }

        monthlyProfitLineChart.getData().clear();
        monthlyProfitLineChart.getData().add(series);

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
