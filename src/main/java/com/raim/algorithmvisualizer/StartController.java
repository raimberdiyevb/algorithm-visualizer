package com.raim.algorithmvisualizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    public Button startButton;

    public void onStartButtonClick(ActionEvent event) throws IOException {
        System.out.println("Start Button clicked!");
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.close();
        System.out.println("Opening a new scene!");
        startMenuScene(new Stage());
    }
    public void startMenuScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AlgorithmVisualizerApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Algorithm Visualizer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}