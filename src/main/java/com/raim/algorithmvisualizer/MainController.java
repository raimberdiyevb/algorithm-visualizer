package com.raim.algorithmvisualizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class MainController {
    public Button backButton;
    @FXML
    public ToggleButton bubbleSortButton;
    public ToggleButton selectionSortButton;
    public ToggleButton quickSortButton;
    public ToggleButton mergeSortButton;
    public Button play_pauseButton;
    public Button nextStepButton;
    public Button previousStepButton;
    public ToggleGroup toggleGroup;
    public TextField arrayTextField;
    private int[] array;

    @FXML
    private void initialize() {
        System.out.println("Controller initialized.");
        textChangedArrayTextField();
    }
    boolean isValid = true;
    @FXML
    private void validateArrayInput() {
        System.out.println("VALIDATE METHOD!");
        String input = arrayTextField.getText().substring(1,arrayTextField.getText().length()-1);
        boolean isValid = isValidArray(input);
        if (isValid) {
            arrayTextField.setStyle("-fx-control-inner-background: white;");
            setArray(input);
        } else {
            arrayTextField.setStyle("-fx-control-inner-background: #FFB6C1;"); // Light pink color
        }
    }
    private void setArray(String input){
        array = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(array));
    }

    @FXML
    private boolean isValidArray(String input) {
        // Implement your validation logic here
        try {

            String[] values = input.split(",");
            for (String value : values) {
                Integer.parseInt(value.trim());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void onBackButtonPressed(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Algorithm Visualizer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onPlay_pauseButtonPressed(ActionEvent event) {
        if(isValid) {
            System.out.println("VALID ARRAY!");
            System.out.println(Arrays.toString(array));
            if (play_pauseButton.getText().equals("Play")) {
                play_pauseButton.setText("Pause");
            } else {
                play_pauseButton.setText("Play");

            }
        }else{
            System.out.println("INVALID ARRAY!");
        }
    }

    public void onNextStepButtonPressed(ActionEvent event) {
    }

    public void onPreviousStepButtonPressed(ActionEvent event) {
    }

    public void textChangedArrayTextField() {
        arrayTextField.textProperty().addListener((observable, oldValue, newValue) -> validateArrayInput());
    }
}
