package com.raim.algorithmvisualizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    public Button sortingButton;

    public void onShortestPathButtonClicked(ActionEvent event) {
    }

    public void onBinarySearchButtonClicked(ActionEvent event) {
    }

    public void onSortingButtonClicked(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) sortingButton.getScene().getWindow();
        currentStage.close();
        startMainScene(new Stage());
    }
    public void startMainScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Algorithm Visualizer");
        stage.setScene(scene);
        stage.show();
    }
}
