package com.raim.algorithmvisualizer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    public ToggleGroup radioToggleGroup;
    public RadioButton isAscRadioButton;
    private int[] array;
    private boolean isAsc;

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

    HashMap<Duration, Integer> stepDurationMap = new HashMap<>();
    public void onPlay_pauseButtonPressed(ActionEvent event1) {
        if (isValid) {
            System.out.println("VALID ARRAY!");
            System.out.println(Arrays.toString(array));


            int delay = 500; // milliseconds
            int totalSteps = states.size();

            KeyFrame[] keyFrames = new KeyFrame[totalSteps];
            for (int i = 0; i < totalSteps; i++) {
                int currentStep = i;
                Duration duration = Duration.millis(i * delay);
                KeyFrame keyFrame = new KeyFrame(duration, event -> {
                    drawArray(currentStep);
                });
                stepDurationMap.put(duration, currentStep);
                keyFrames[i] = keyFrame;
            }

            timeline.getKeyFrames().addAll(keyFrames);
            timeline.setCycleCount(1);
            timeline.setOnFinished(event -> {
                isSortingAnimationRunning = false;
                play_pauseButton.setText("Play");
            });
            isSortingAnimationRunning = true;




            if( play_pauseButton.getText().equals("Start")){
                arrayTextField.setEditable(false);
                isAsc = isAscRadioButton.isSelected();
                System.out.println("isASC ? " + isAsc);
                bubbleSort(array);
                timeline.play();
            }
            else if (play_pauseButton.getText().equals("Play")) {
                //choose the algorithm by which I will get the states
                // for not it is just bubble sort

                timeline.play();
                play_pauseButton.setText("Pause");
            } else {
                timeline.pause();
                play_pauseButton.setText("Play");
                isSortingAnimationRunning = false; // Reset the flag
                this.currentStep = stepDurationMap.get(timeline.getCurrentTime());
            }
        } else {
            System.out.println("INVALID ARRAY!");
        }
    }



    public void onNextStepButtonPressed(ActionEvent event) {
        System.out.println("CURRENT STEP : " + currentStep);
        if(currentStep < states.size()-1)
            currentStep++;
        drawArray(currentStep);
    }

    public void onPreviousStepButtonPressed(ActionEvent event) {
    }

    public void textChangedArrayTextField() {
        arrayTextField.textProperty().addListener((observable, oldValue, newValue) -> validateArrayInput());
    }
    Timeline timeline = new Timeline();

    private boolean isSortingAnimationRunning = false;
    int currentStep = 0;

    private void animateSorting() {
        int delay = 500; // milliseconds
        int totalSteps = states.size();

        KeyFrame[] keyFrames = new KeyFrame[totalSteps];
        for (int i = 0; i < totalSteps; i++) {
            int currentStep = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(i * delay), event -> {
                drawArray(currentStep);
            });
            keyFrames[i] = keyFrame;
        }

        timeline.getKeyFrames().addAll(keyFrames);
        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> {
            isSortingAnimationRunning = false;
            play_pauseButton.setText("Play");
        });
        timeline.play();
        isSortingAnimationRunning = true;
    }

    public static void swap(int[] array, int from, int to){
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
    private void drawArray(int currentStep) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int[] state = states.get(currentStep);

        for (int i = 0; i < state.length; i++) {
            double barHeight = state[i] * 12;
            gc.fillRect(i * 20, canvas.getHeight() - barHeight, 15, barHeight);
        }
    }
    private void drawArray() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        for (int i = 0; i < array.length; i++) {
            double barHeight = array[i] * 12;
            gc.fillRect(i * 20, canvas.getHeight() - barHeight, 15, barHeight);
        }
    }

    List<int[]> states = new ArrayList<>();

    public void bubbleSort(int[] array){
        //add the ORDER


        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if(isAsc){
                    if(array[i] > array[j]){
                        swap(array,i,j);
                    }
                }else{
                    if(array[i] < array[j]){
                        swap(array,i,j);
                    }
                }
                states.add(getState(array));
                System.out.println(Arrays.toString(array));
            }
        }
    }
    public int[] getState(int[] array){
        int[] state = new int[array.length];
        System.arraycopy(array, 0, state, 0, array.length);
        return state;
    }


}
