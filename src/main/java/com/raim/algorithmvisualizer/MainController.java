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
    public ToggleGroup algorithmToggleGroup;
    public TextField arrayTextField;
    public Canvas canvas;
    public ToggleGroup radioToggleGroup;
    public RadioButton isAscRadioButton;
    public Button start_resetButton;
    public RadioButton isDescRadioButton;
    private int[] array;
    private int[] fArray;
    private boolean isAsc;
    private int currentStep = 0;
    List<int[]> states = new ArrayList<>();
    boolean isValid = true;
    HashMap<Double, Integer> stepDurationMap = new HashMap<>();
    Timeline timeline = new Timeline();
    String selectedAlgorithm;
    //private boolean isSortingAnimationRunning = false;

    @FXML
    private void initialize() {
        System.out.println("Controller initialized.");
        setArray(arrayTextField.getText().trim());
        textChangedArrayTextField();
        drawArray();
        play_pauseButton.setDisable(true);
        previousStepButton.setDisable(true);
        nextStepButton.setDisable(true);
    }
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
        fArray = getState(array);
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
        FXMLLoader fxmlLoader = new FXMLLoader(AlgorithmVisualizerApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Algorithm Visualizer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void onPlay_pauseButtonPressed(ActionEvent event1) {
        if(play_pauseButton.getText().equals("Play")){
            timeline.play();
            play_pauseButton.setText("Pause");
            previousStepButton.setDisable(true);
            nextStepButton.setDisable(true);
        }else{
            timeline.pause();
            previousStepButton.setDisable(false);
            nextStepButton.setDisable(false);
            System.out.println("Current Duration : " + timeline.getCurrentTime());
            double currentMillis = Math.round(timeline.getCurrentTime().toMillis() / 100.0) * 100.0;
            currentStep = stepDurationMap.get(currentMillis);
            play_pauseButton.setText("Play");
        }
    }

    public void onStartResetButtonClicked(ActionEvent event1) {
        if(!isValid){
            System.out.println("ERROR");
        }else {
            System.out.print("VALID ARRAY : ");
            System.out.println(Arrays.toString(array));

            if (start_resetButton.getText().equals("Start")) {
                System.out.println("Start Button Clicked!");
                // enable play buttons
                play_pauseButton.setDisable(false);
                previousStepButton.setDisable(false);
                nextStepButton.setDisable(false);
                //disable array textBox
                arrayTextField.setEditable(false);
                //disable radio button
                isAscRadioButton.setDisable(true);
                isDescRadioButton.setDisable(true);
                // set up the timelines
                isAsc = isAscRadioButton.isSelected();
                //choose the sorting algorithm
                selectedAlgorithm = ((ToggleButton) algorithmToggleGroup.getSelectedToggle()).getText();
                System.out.println("Algorithm : " + selectedAlgorithm);
                if(selectedAlgorithm.equals("Bubble Sort")){
                    bubbleSort(array);
                }else if (selectedAlgorithm.equals("Selection Sort")) {
                    selectionSort(array);
                }else if (selectedAlgorithm.equals("Quick Sort")) {
                    quickSort(array);
                }else{
                    mergeSort(array);
                }

                KeyFrame[] keyFrames = new KeyFrame[states.size()];
                for (int i = 0; i < states.size(); i++) {
                    int tempStep = i;
                    Duration duration = Duration.millis(i * 100);
                    KeyFrame keyFrame = new KeyFrame(duration, event -> drawArray(tempStep));
                    stepDurationMap.put(i * 100.0, tempStep);
                    keyFrames[i] = keyFrame;
                }
                System.out.println("MAP : " + stepDurationMap);
                timeline.getKeyFrames().addAll(keyFrames);
                timeline.setCycleCount(1);
                timeline.setOnFinished(event -> play_pauseButton.setText("Play"));
                //change name of the button
                start_resetButton.setText("Reset");

            } else {
                //disable play buttons
                play_pauseButton.setDisable(true);
                previousStepButton.setDisable(true);
                nextStepButton.setDisable(true);
                //enable arrayField
                arrayTextField.setEditable(true);
                //enable radio buttons
                isAscRadioButton.setDisable(false);
                isDescRadioButton.setDisable(false);
                //reset everything
                states.clear();
                timeline.stop();
                drawArray(fArray);
                //change the name of the button
                start_resetButton.setText("Start");
            }
        }
    }
    public void onNextStepButtonPressed(ActionEvent event) {
        System.out.print("CURRENT STEP : " + currentStep + ", ");
        if(currentStep < states.size()-1)
            currentStep++;
        drawArray(currentStep);
    }
    public void onPreviousStepButtonPressed(ActionEvent event) {
        System.out.print("CURRENT STEP : " + currentStep + ", ");
        if(currentStep > 0)
            currentStep--;
        drawArray(currentStep);
    }
    public void textChangedArrayTextField() {
        arrayTextField.textProperty().addListener((observable, oldValue, newValue) -> validateArrayInput());
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
    private void drawArray(int[] array) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < array.length; i++) {
            double barHeight = array[i] * 12;
            gc.fillRect(i * 20, canvas.getHeight() - barHeight, 15, barHeight);
        }
    }
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
            }
        }

    }
    private void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1 ; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if(isAsc) {
                    if (array[j] < array[min]) {
                        min = j;
                    }
                }else{
                    if (array[j] > array[min]) {
                        min = j;
                    }
                }
                states.add(getState(array));
            }
            swap(array,i,min);
        }
    }
    private void quickSort(int[] array) {
        quickSort(array,0,array.length-1);
    }
    private void quickSort(int[] arr, int left, int right){
        states.add(getState(array));
        if(left < right){
            int pivot = arr[right];
            int i = left - 1;
            for(int j = left; j < right; j++){
                if(arr[j] < pivot){
                    swap(arr,i+1,j);
                    i++;
                }
            }
            swap(arr,i+1,right);
            quickSort(arr,left,i);
            quickSort(arr,i+2,right);
        }
    }
    private void mergeSort(int[] array){

    }
    public int[] getState(int[] array){
        int[] state = new int[array.length];
        System.arraycopy(array, 0, state, 0, array.length);
        return state;
    }
    public static void swap(int[] array, int from, int to){
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
}
