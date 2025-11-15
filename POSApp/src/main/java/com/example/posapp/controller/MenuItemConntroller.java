package com.example.posapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuItemConntroller {

    @FXML private ListView<MenuItem> recitedView;

    public void initialize() {
    }

    @FXML
    public void espressoClick(ActionEvent event) {}

    @FXML
    public void latteClick(ActionEvent event) {}

    @FXML
    public void cappuccinoClick(ActionEvent event) {}

    @FXML
    public void frappeClick(ActionEvent event) {}

    @FXML
    public void iceAmeriClick(ActionEvent event) {}

    @FXML
    public void iceCoffeeClick(ActionEvent event) {}

    @FXML
    public void greenTeaClick(ActionEvent event) {}

    @FXML
    public void blackTeaClick(ActionEvent event) {}

    @FXML
    public void peachTeaClick(ActionEvent event) {}

    @FXML
    public void starwberryTeaClick(ActionEvent event) {}

    @FXML
    public void chaiLatteTeaClick(ActionEvent event) {}

    @FXML
    public void oolongTeaClick(ActionEvent event) {}

    @FXML
    public void starwberrySmoClick(ActionEvent event) {}

    @FXML
    public void mangoSmClick(ActionEvent event) {}

    @FXML
    public void mixedBerrySmClick(ActionEvent event) {}

    @FXML
    public void proteinShakenClick(ActionEvent event) {}

    @FXML
    public void croissantClick(ActionEvent event) {}

    @FXML
    public void muffinClick(ActionEvent event) {}

    @FXML
    public void chickenWrapClick(ActionEvent event) {}

    @FXML
    public void btlSandwichClick(ActionEvent event) {}

    @FXML
    public void grilledCheeseClick(ActionEvent event) {}

    @FXML
    public void cheeseBagelClick(ActionEvent event) {}

    @FXML
    public void proceedClick(ActionEvent event) {
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/sales-order.fxml"));
            Parent newRoot = loader.load();

            Scene newScene = new Scene(newRoot);

            // Get the current stage (e.g., from a component's scene and window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Sales-Order Report");
        }
        catch (IOException e) {
            //check top looger
            e.printStackTrace();
        }
    }

    @FXML
    public void removeItemClick(ActionEvent event) {}
}
