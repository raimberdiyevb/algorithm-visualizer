package com.raim.algorithmvisualizer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    public Canvas canvas;
    private int[] array;
    private boolean isAsc = true;


    @FXML
    private void initialize() {
        System.out.println("Controller initialized.");
        setArray(arrayTextField.getText().trim());
        textChangedArrayTextField();
        drawArray();
    }
    boolean isValid = true;
    @FXML
    private void validateArrayInput() {
        System.out.println("VALIDATE METHOD!");
        String input = arrayTextField.getText().trim();
        boolean isValid = isValidArray(input);
        if (isValid && !input.isEmpty()) {  // Check for non-empty input
            arrayTextField.setStyle("-fx-control-inner-background: white;");
            setArray(input);
            drawArray();
        } else {
            arrayTextField.setStyle("-fx-control-inner-background: #FFB6C1;"); // Light pink color
        }
    }

    private void setArray(String input) {
        array = Arrays.stream(input.split("\\s*,\\s*"))
                .map(String::trim)  // Trim each element to remove leading/trailing whitespaces
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(Arrays.toString(array));
    }


    @FXML
    private boolean isValidArray(String input) {
        try {
            // Use a regular expression to match valid input patterns
            String regex = "\\s*\\d+\\s*(,\\s*\\d+\\s*)*";

            return input.matches(regex);
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
        if (isValid) {
            System.out.println("VALID ARRAY!");
            System.out.println(Arrays.toString(array));
            if (play_pauseButton.getText().equals("Play") || play_pauseButton.getText().equals("Start")) {
                arrayTextField.setEditable(false);
                animateSorting();
                play_pauseButton.setText("Pause");
            } else {
                timeline.pause();
                play_pauseButton.setText("Play");
                isSortingAnimationRunning = false; // Reset the flag
            }
        } else {
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
    Timeline timeline = new Timeline();

    private boolean isSortingAnimationRunning = false;

    private void animateSorting() {
        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), event -> {
            bubbleSortStep();
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        isSortingAnimationRunning = true;
    }

//    private void bubbleSort(int[] array, boolean asc) {
//        // Your sorting logic
//        // After each step, call drawArray();
//        for (int i = 0; i < array.length; i++) {
//            for (int j = i + 1; j < array.length; j++) {
//                if (array[i] < array[j]) {
//                    swap(array, i, j);
//                }
//            }
//            drawArray();  // Move drawArray() outside the inner loop
//        }
//    }
private int i = 0;
    private int j = 0;

    private void bubbleSortStep() {
        if (i < array.length) {
            if (j < array.length - i - 1) {
                if (array[j] < array[j + 1]) {
                    swap(array, j, j + 1);
                }
                j++;
            } else {
                i++;
                j = 0;
            }
            drawArray(); // Draw the current state after each step
        } else {
            // Sorting is complete, stop the animation
            timeline.stop();
            isSortingAnimationRunning = false;
        }
    }

    public static void swap(int[] array, int from, int to){
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
    private void drawArray() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Clear previous drawings
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw the array elements
        // Adjust the size and positioning based on your preferences
        for (int i = 0; i < array.length; i++) {
            double barHeight = array[i] * 12;  // Adjust scalingFactor as needed
            gc.fillRect(i * 20, canvas.getHeight() - barHeight, 15, barHeight);
        }
    }


}
