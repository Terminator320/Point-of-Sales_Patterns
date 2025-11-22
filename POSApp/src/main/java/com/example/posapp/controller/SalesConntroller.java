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
import javafx.scene.control.Tooltip;
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

    @FXML private LineChart<String, Number> monthlyProfitLineChart;

    @FXML private BarChart<String, Number> weeklyProfitBarChart;

    @FXML private PieChart profitLossPieChart;

    private Map<LocalDate, Double> profitPerDay;

    private Map<LocalDate, Double> revenuePerDay;
    private Map<LocalDate, Double> costPerDay;

    private static final Logger LOGGER = LogConfig.getLogger(SalesConntroller.class.getName());

    public void initialize() {
        //Load sales from DB
        List<Sales> sales = Sales.getAllSales();

        //change later when we figure out threads for this
        // Build profitPerDay map
        profitPerDay = new HashMap<>();
        revenuePerDay = new HashMap<>();
        costPerDay = new HashMap<>();

        for (Sales s : sales) {
            LocalDate date = s.getDate();
            double profit = s.getRevenue() - s.getCost();
            profitPerDay.merge(s.getDate(), profit, Double::sum);
            revenuePerDay.merge(date, s.getRevenue(), Double::sum);
            costPerDay.merge(date, s.getCost(), Double::sum);
        }

        // 3. Fill both charts
        loadMonthlyLineChart();
        loadLast7DaysBarChart();


        //test value
        double totalProfitMonth = 3400;
        double totalLossMonth = -1200;

        loadProfitLossPie(totalProfitMonth, totalLossMonth);

    }


    public void loadProfitLossPie(double totalProfit, double totalLoss) {
        // convert negative loss to positive for display
        double lossAbsolute = Math.abs(totalLoss);

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Profit", totalProfit),
                new PieChart.Data("Loss", lossAbsolute)
        );

        profitLossPieChart.setData(pieData);

        for (PieChart.Data d : profitLossPieChart.getData()) {
            if (d.getName().equals("Profit")) {
                d.getNode().setStyle("-fx-pie-color: #2ecc71;"); // green
            } else {
                d.getNode().setStyle("-fx-pie-color: #e74c3c;"); // red
            }
        }
    }



    private void loadMonthlyLineChart() {
        if (revenuePerDay.isEmpty()) {
            monthlyProfitLineChart.getData().clear();
            return;
        }

        // latest date in data
        LocalDate latest = Collections.max(revenuePerDay.keySet());
        LocalDate start = latest.minusDays(30);

        // Sort dates
        List<LocalDate> sortedDays = revenuePerDay.keySet().stream()
                .filter(date -> !date.isBefore(start) && !date.isAfter(latest))
                .sorted()
                .collect(Collectors.toList());

        XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
        revenueSeries.setName("Revenue");

        XYChart.Series<String, Number> costSeries = new XYChart.Series<>();
        costSeries.setName("Cost");


        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d");
        for (LocalDate day : sortedDays) {
            String label = day.format(fmt);

            double revenue = revenuePerDay.getOrDefault(day, 0.0);
            double cost = costPerDay.getOrDefault(day, 0.0);

            revenueSeries.getData().add(new XYChart.Data<>(label, revenue));
            costSeries.getData().add(new XYChart.Data<>(label, cost));
        }

        monthlyProfitLineChart.getData().clear();
        monthlyProfitLineChart.getData().addAll(revenueSeries, costSeries);
    }



    private void loadLast7DaysBarChart() {
        if (profitPerDay.isEmpty()) {
            weeklyProfitBarChart.getData().clear();
            return;
        }

        // latest date in data
        LocalDate latest = Collections.max(profitPerDay.keySet());
        LocalDate start = latest.minusDays(6);

        // Collect dates in range and sort
        List<LocalDate> last7Days = profitPerDay.keySet().stream()
                .filter(d -> !d.isBefore(start) && !d.isAfter(latest))
                .sorted()
                .collect(Collectors.toList());

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Last 7 days profit");

        for (LocalDate day : last7Days) {
            double profit = profitPerDay.get(day);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d");
            String label = day.format(fmt);
            series.getData().add(new XYChart.Data<>(label, profit));
        }

        weeklyProfitBarChart.getData().clear();
        weeklyProfitBarChart.getData().add(series);
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
