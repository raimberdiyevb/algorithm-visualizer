module com.raim.algorithmvisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.raim.algorithmvisualizer to javafx.fxml;
    exports com.raim.algorithmvisualizer;
}